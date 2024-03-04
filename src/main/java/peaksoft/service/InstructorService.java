package peaksoft.service;

import peaksoft.dto.requests.InstructorRequest;
import peaksoft.dto.responses.CountOfStudentsResponse;
import peaksoft.dto.responses.InstructorResponse;
import peaksoft.dto.responses.SimpleResponse;

public interface InstructorService {
    SimpleResponse save(InstructorRequest instructorRequest);

    InstructorResponse findById(Long instructorId);

    SimpleResponse update(Long instructorId, InstructorRequest instructorRequest);

    SimpleResponse deleteById(Long instructorId);

    SimpleResponse assignInstructorToCompany(Long instructorId, Long companyId);

    CountOfStudentsResponse getCountOfStudentsFromThatInstructorTeach(Long instructorId);

}
