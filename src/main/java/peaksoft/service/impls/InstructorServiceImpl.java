package peaksoft.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.InstructorRequest;
import peaksoft.dto.responses.CountOfStudentsResponse;
import peaksoft.dto.responses.InstructorResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionCountOfStudentsResponse;
import peaksoft.dto.responses.unions.UnionInstructorResponse;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Instructor;
import peaksoft.enums.Specialization;
import peaksoft.repositories.CompanyRepository;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SimpleResponse save(InstructorRequest instructorRequest) {
        try {
            if (!instructorRequest.phoneNumber().contains("+996")) {
                throw new RuntimeException("Phone number does not contain +996!");
            }
            Instructor buildedInstructor = instructorRequest.build();
            buildedInstructor.getLoginDetails().setPassword(passwordEncoder.encode(buildedInstructor.getLoginDetails().getPassword()));
            instructorRepository.save(buildedInstructor);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully saved!").build();
        } catch (RuntimeException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
        }
    }

    @Override
    public UnionInstructorResponse findById(Long instructorId) {
        try {
            Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(NoSuchElementException::new);
            return UnionInstructorResponse.builder()
                    .data(InstructorResponse.builder()
                            .firstName(instructor.getFirstName())
                            .id(instructor.getId())
                            .lastName(instructor.getLastName())
                            .phoneNumber(instructor.getPhoneNumber())
                            .specialization(instructor.getSpecialization())
                            .build())
                    .status(SimpleResponse.builder()
                            .httpStatus(HttpStatus.OK)
                            .message("Successfully returned!")
                            .build())
                    .build();
        } catch (NoSuchElementException e) {
            return UnionInstructorResponse.builder()
                    .data(null)
                    .status(SimpleResponse.builder()
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .message("Instructor with id " + instructorId + " is not found!")
                            .build())
                    .build();
        }
    }

    @Override
    @Transactional
    public SimpleResponse update(Long instructorId, InstructorRequest instructorRequest) {
        try {
            Instructor instructor = instructorRepository.findById(instructorId)
                    .orElseThrow(() -> new NoSuchElementException("Instructor with id " + instructorId + " is not found!"));
            instructor.setFirstName(instructorRequest.firstName());
            instructor.setLastName(instructorRequest.lastName());
            instructor.setPhoneNumber(instructorRequest.phoneNumber());
            instructor.setSpecialization(Specialization.valueOf(instructorRequest.specialization().toUpperCase()));
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully updated!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long instructorId) {
        try {
            instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor with id " + instructorId + " is not found!"));
            instructorRepository.deleteById(instructorId);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully deleted!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public SimpleResponse assignInstructorToCompany(Long instructorId, Long companyId) {
        try {
            Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor with id " + instructorId + " is not found!"));
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id " + companyId + " is not found!"));

            instructor.getCompanies().add(company);
            company.getInstructors().add(instructor);
            return SimpleResponse.builder().message("Successfully assigned!").httpStatus(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().message("Group or Course with given id is not found!").httpStatus(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @Transactional
    public SimpleResponse assignInstructorToCourse(Long instructorId, Long courseId) {
        try {
            Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor with id " + instructorId + " is not found!"));
            Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course with id " + courseId + " is not found!"));

            instructor.setCourse(course);
            course.getInstructors().add(instructor);
            return SimpleResponse.builder().message("Successfully assigned!").httpStatus(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().message("Group or Course with given id is not found!").httpStatus(HttpStatus.NOT_FOUND).build();
        }
    }


    @Override
    public UnionCountOfStudentsResponse getCountOfStudentsFromThatInstructorTeach(Long instructorId) {
        try {
            Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor with id " + instructorId + " is not found!"));
            int totalCount = instructor.getCourse().getGroups().stream()
                    .mapToInt(group -> group.getStudents().size())
                    .sum();

            return UnionCountOfStudentsResponse.builder()
                    .data(CountOfStudentsResponse.builder().count(totalCount).build())
                    .status(SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully returned!").build())
                    .build();
        } catch (NoSuchElementException e) {
            return UnionCountOfStudentsResponse.builder()
                    .data(null)
                    .status(SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message("Instructor with id " + instructorId + " is not found!").build())
                    .build();
        }
    }


}