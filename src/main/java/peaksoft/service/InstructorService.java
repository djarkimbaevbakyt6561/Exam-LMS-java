package peaksoft.service;

import peaksoft.dto.requests.InstructorRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionCountOfStudentsResponse;
import peaksoft.dto.responses.unions.UnionInstructorResponse;

public interface InstructorService {
    SimpleResponse save(InstructorRequest instructorRequest);

    UnionInstructorResponse findById(Long instructorId);

    SimpleResponse update(Long instructorId, InstructorRequest instructorRequest);

    SimpleResponse deleteById(Long instructorId);

    SimpleResponse assignInstructorToCompany(Long instructorId, Long companyId);
    SimpleResponse assignInstructorToCourse(Long instructorId, Long courseId);

    UnionCountOfStudentsResponse getCountOfStudentsFromThatInstructorTeach(Long instructorId);

}
