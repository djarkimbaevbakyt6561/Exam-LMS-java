package peaksoft.service.impls;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.InstructorRequest;
import peaksoft.dto.responses.CountOfStudentsResponse;
import peaksoft.dto.responses.InstructorResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.entites.Company;
import peaksoft.entites.Instructor;
import peaksoft.enums.Specialization;
import peaksoft.repositories.CompanyRepository;
import peaksoft.repositories.InstructorRepository;
import peaksoft.service.InstructorService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;

    @Override
    public SimpleResponse save(InstructorRequest instructorRequest) {
            try {
                instructorRepository.save(instructorRequest.build());
                return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully saved!").build();
            } catch (NoSuchElementException e) {
                return SimpleResponse.builder().httpStatus(HttpStatus.OK).message(e.getMessage()).build();
            }
    }

    @Override
    public InstructorResponse findById(Long instructorId) {
        try {
            Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(NoSuchElementException::new);
            return InstructorResponse.builder()
                    .firstName(instructor.getFirstName())
                    .id(instructor.getId())
                    .lastName(instructor.getLastName())
                    .phoneNumber(instructor.getPhoneNumber())
                    .specialization(instructor.getSpecialization())
                    .build();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Instructor with id " + instructorId + " is not found!");
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
    public CountOfStudentsResponse getCountOfStudentsFromThatInstructorTeach(Long instructorId) {
        try {
            Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Instructor with id " + instructorId + " is not found!"));
            int totalCount = instructor.getCourse().getGroups().stream()
                    .mapToInt(group -> group.getStudents().size())
                    .sum();

            return CountOfStudentsResponse.builder().count(totalCount).build();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Instructor with id " + instructorId + " is not found!");
        }
    }


}