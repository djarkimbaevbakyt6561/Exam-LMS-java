package peaksoft.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.CourseRequest;
import peaksoft.dto.requests.UpdateCourseRequest;
import peaksoft.dto.responses.SortedCoursesResponse;
import peaksoft.dto.responses.CourseResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.entites.Company;
import peaksoft.entites.Course;
import peaksoft.repositories.CompanyRepository;
import peaksoft.repositories.CourseRepository;
import peaksoft.service.CourseService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    @Override
    public SimpleResponse save(CourseRequest courseRequest) {
        try {
            Company company = companyRepository.findById(courseRequest.companyId())
                    .orElseThrow(() -> new NoSuchElementException("Company with id " + courseRequest.companyId() + " is not found!"));
            Course buildedCourse = courseRequest.build();
            buildedCourse.setCompany(company);
            courseRepository.save(buildedCourse);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully saved!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
        }
    }

    @Override
    public CourseResponse findById(Long courseId) {
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(NoSuchElementException::new);
            return CourseResponse
                    .builder()
                    .id(course.getId())
                    .courseName(course.getCourseName())
                    .description(course.getDescription())
                    .build();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Course with id " + courseId + " is not found!");
        }
    }

    @Override
    @Transactional
    public SimpleResponse update(Long courseId, UpdateCourseRequest updateCourseRequest) {
        try {
            Course foundCourse = courseRepository.findById(courseId).orElseThrow(NoSuchElementException::new);
            foundCourse.setCourseName(updateCourseRequest.courseName());
            foundCourse.setDescription(updateCourseRequest.description());
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully updated!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long courseId) {
        try {
            courseRepository.findById(courseId).orElseThrow(NoSuchElementException::new);
            courseRepository.deleteById(courseId);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully deleted!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }

    @Override
    public SortedCoursesResponse getAllSortedCourses() {
        List<Course> courses = courseRepository.getSortedCoursesByDateStart();
        return SortedCoursesResponse.builder().courses(courses).build();
    }
}
