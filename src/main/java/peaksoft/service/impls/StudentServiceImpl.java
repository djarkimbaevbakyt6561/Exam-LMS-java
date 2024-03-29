package peaksoft.service.impls;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.StudentRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.AllStudentsResponse;
import peaksoft.dto.responses.StudentResponse;
import peaksoft.dto.responses.unions.UnionAllStudentsResponse;
import peaksoft.dto.responses.unions.UnionStudentResponse;
import peaksoft.entities.Group;
import peaksoft.entities.Student;
import peaksoft.enums.StudyFormat;
import peaksoft.repositories.GroupRepository;
import peaksoft.repositories.StudentRepository;
import peaksoft.service.StudentService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public SimpleResponse save(StudentRequest studentRequest) {
        try {
            if (!studentRequest.phoneNumber().contains("+996")) {
                throw new RuntimeException("Phone number does not contain +996!");
            }
            Student student = studentRequest.build();
            student.setGroup(groupRepository.findById(studentRequest.groupId()).orElseThrow(() -> new NoSuchElementException("Group with id " + studentRequest.groupId() + " is not found")));
            studentRepository.save(student);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully saved!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
        }
    }

    @Override
    public UnionStudentResponse findById(Long studentId) {
        try {
            Student student = studentRepository.findById(studentId).orElseThrow(NoSuchElementException::new);
            return UnionStudentResponse.builder()
                    .data(StudentResponse.builder()
                            .firstName(student.getFirstName())
                            .lastName(student.getLastName())
                            .id(student.getId())
                            .phoneNumber(student.getPhoneNumber())
                            .studyFormat(student.getStudyFormat())
                            .build())
                    .status(SimpleResponse.builder()
                            .message("Successfully returned")
                            .httpStatus(HttpStatus.OK)
                            .build())
                    .build();
        } catch (NoSuchElementException e) {
            return UnionStudentResponse.builder()
                    .data(null)
                    .status(SimpleResponse.builder()
                            .message("Student with id " + studentId + " is not found!")
                            .httpStatus(HttpStatus.OK)
                            .build())
                    .build();
        }
    }

    @Override
    @Transactional
    public SimpleResponse update(Long studentId, StudentRequest studentRequest) {
        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new NoSuchElementException("Student with id " + studentId + " is not found!"));
            student.setFirstName(studentRequest.firstName());
            student.setLastName(studentRequest.lastName());
            student.setPhoneNumber(studentRequest.phoneNumber());
            student.setStudyFormat(StudyFormat.valueOf(studentRequest.studyFormat().toUpperCase()));
            student.setGroup(groupRepository.findById(studentRequest.groupId()).orElseThrow(NoSuchElementException::new));

            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully updated!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long studentId) {
        try {
            studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Student with id " + studentId + " is not found!"));
            studentRepository.deleteById(studentId);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully deleted!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public SimpleResponse assignStudentToGroup(Long studentId, Long groupId) {
        try {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new EntityNotFoundException("Student with id " + studentId + " is not found!"));

            Group group = groupRepository.findById(groupId)
                    .orElseThrow(() -> new EntityNotFoundException("Group with id " + groupId + " is not found!"));
            student.setGroup(group);
            group.getStudents().add(student);
            return SimpleResponse.builder().message("Successfully assigned!").httpStatus(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return SimpleResponse.builder().message("Group or Course with given id is not found!").httpStatus(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public UnionAllStudentsResponse getAllSortedByStudyFormatStudentsFromCompany(Long companyId, String studyFormat) {
        try {
            return UnionAllStudentsResponse.builder()
                    .data(AllStudentsResponse
                            .builder()
                            .students(studentRepository.getAllCompaniesStudentsFilteredByStudyFormat(StudyFormat.valueOf(studyFormat.toUpperCase()), companyId))
                            .build())
                    .status(SimpleResponse.builder()
                            .message("Successfully returned!")
                            .httpStatus(HttpStatus.OK)
                            .build())
                    .build();
        } catch (RuntimeException e) {
            return UnionAllStudentsResponse.builder()
                    .data(AllStudentsResponse
                            .builder()
                            .students(studentRepository.getAllCompaniesStudentsFilteredByStudyFormat(StudyFormat.valueOf(studyFormat.toUpperCase()), companyId))
                            .build())
                    .status(SimpleResponse.builder()
                            .message("Wrong Study Format!")
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build())
                    .build();
        }
    }
}
