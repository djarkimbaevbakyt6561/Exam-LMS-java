package peaksoft.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.CompanyRequest;
import peaksoft.dto.responses.CompanyResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionCompanyResponse;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.entities.Instructor;
import peaksoft.exceptions.DuplicateNameCompanyException;
import peaksoft.repositories.*;
import peaksoft.service.CompanyService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;

    @Override
    public SimpleResponse save(CompanyRequest companyRequest) {
        try {
            if (!companyRequest.phoneNumber().contains("+996")) {
                return SimpleResponse.builder().message("Phone number does not contain +996!").httpStatus(HttpStatus.BAD_REQUEST).build();
            }
            Company buildedCompany = companyRequest.build();
            companyRepository.save(buildedCompany);
            return SimpleResponse.builder().message("Successfully saved!").httpStatus(HttpStatus.OK).build();
        } catch (DuplicateNameCompanyException e) {
            return SimpleResponse.builder().message(e.getMessage()).httpStatus(HttpStatus.BAD_REQUEST).build();
        }

    }

    @Override
    public UnionCompanyResponse findById(Long companyId) {
        try {
            Long countOfStudentsByCompanyId = studentRepository.getCountOfStudentsByCompanyId(companyId);
            List<Instructor> instructorsByCompanyId = instructorRepository.getInstructorsByCompanyId(companyId);
            List<Course> courses = courseRepository.getCoursesByCompanyId(companyId);
            List<Group> groups = groupRepository.getGroupsByCompanyId(companyId);
            Company company = companyRepository.findById(companyId).orElseThrow(NoSuchElementException::new);
            return UnionCompanyResponse.builder()
                    .data(
                            CompanyResponse
                                    .builder()
                                    .id(company.getId())
                                    .name(company.getName())
                                    .address(company.getAddress())
                                    .country(company.getCountry())
                                    .phoneNumber(company.getPhoneNumber())
                                    .coursesNamesOfCompany(courses.stream().map(Course::getCourseName).toList())
                                    .countOfStudents(countOfStudentsByCompanyId)
                                    .groupsNamesOfCompany(groups.stream().map(Group::getGroupName).toList())
                                    .instructorsNamesOfCompany(instructorsByCompanyId.stream().map(Instructor::getFirstName).toList())
                                    .build())
                    .status(SimpleResponse.builder().message("Successfully returned!").httpStatus(HttpStatus.OK).build())
                    .build();
        } catch (NoSuchElementException e) {
            return UnionCompanyResponse.builder().data(null).status(SimpleResponse.builder().message("Company with id " + companyId + " is not found!").httpStatus(HttpStatus.NOT_FOUND).build()).build();
        }
    }

    @Override
    @Transactional
    public SimpleResponse update(Long companyId, CompanyRequest companyRequest) {
        try {
            Company foundCompany = companyRepository.findById(companyId).orElseThrow(NoSuchElementException::new);
            foundCompany.setPhoneNumber(companyRequest.phoneNumber());
            foundCompany.setCountry(companyRequest.country());
            foundCompany.setName(companyRequest.name());
            foundCompany.setAddress(companyRequest.address());
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully updated!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long companyId) {
        try {
            companyRepository.findById(companyId).orElseThrow(NoSuchElementException::new);
            companyRepository.deleteById(companyId);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully deleted!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }
}
