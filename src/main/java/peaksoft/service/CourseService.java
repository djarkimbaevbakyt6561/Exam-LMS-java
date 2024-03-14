package peaksoft.service;

import peaksoft.dto.requests.CourseRequest;
import peaksoft.dto.requests.UpdateCourseRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionCourseResponse;
import peaksoft.dto.responses.unions.UnionSortedCoursesResponse;

public interface CourseService {
    SimpleResponse save(CourseRequest courseRequest);

    UnionCourseResponse findById(Long courseId);

    SimpleResponse update(Long courseId, UpdateCourseRequest updateCourseRequest);

    SimpleResponse deleteById(Long courseId);

    UnionSortedCoursesResponse getAllSortedCourses();
}
