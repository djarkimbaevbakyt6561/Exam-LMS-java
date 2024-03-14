package peaksoft.service;

import peaksoft.dto.requests.StudentRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionAllStudentsResponse;
import peaksoft.dto.responses.unions.UnionStudentResponse;

public interface StudentService {
    SimpleResponse save(StudentRequest studentRequest);

    UnionStudentResponse findById(Long studentId);

    SimpleResponse update(Long studentId, StudentRequest studentRequest);

    SimpleResponse deleteById(Long studentId);

    SimpleResponse assignStudentToGroup(Long studentId, Long groupId);
    UnionAllStudentsResponse getAllSortedByStudyFormatStudentsFromCompany(Long companyId, String studyFormat);
}
