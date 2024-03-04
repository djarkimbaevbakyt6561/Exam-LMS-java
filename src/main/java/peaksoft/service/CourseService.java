package peaksoft.service;

import peaksoft.dto.requests.CourseRequest;
import peaksoft.dto.requests.UpdateCourseRequest;
import peaksoft.dto.responses.SortedCoursesResponse;
import peaksoft.dto.responses.CourseResponse;
import peaksoft.dto.responses.SimpleResponse;

public interface CourseService {
    SimpleResponse save(CourseRequest courseRequest);

    CourseResponse findById(Long courseId);

    SimpleResponse update(Long courseId, UpdateCourseRequest updateCourseRequest);

    SimpleResponse deleteById(Long courseId);

    SortedCoursesResponse getAllSortedCourses();
}
