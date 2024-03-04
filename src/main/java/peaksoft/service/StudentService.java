package peaksoft.service;

import peaksoft.dto.requests.StudentRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.SortedByStudyFormatStudentsResponse;
import peaksoft.dto.responses.StudentResponse;
import peaksoft.entites.Student;
import peaksoft.enums.StudyFormat;

import java.util.List;

public interface StudentService {
    SimpleResponse save(StudentRequest studentRequest);

    StudentResponse findById(Long studentId);

    SimpleResponse update(Long studentId, StudentRequest studentRequest);

    SimpleResponse deleteById(Long studentId);

    SimpleResponse assignStudentToGroup(Long studentId, Long groupId);
    SortedByStudyFormatStudentsResponse getAllSortedByStudyFormatStudentsFromCompany(Long companyId, String studyFormat);
}
