package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.CourseRequest;
import peaksoft.dto.requests.UpdateCourseRequest;
import peaksoft.dto.responses.CourseResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.SortedCoursesResponse;
import peaksoft.service.CourseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseAPI {
    private final CourseService courseService;

    @PostMapping("/save")
    SimpleResponse save(@RequestBody CourseRequest courseRequest) {
        return courseService.save(courseRequest);
    }

    @GetMapping("/{courseId}")
    public CourseResponse getById(@PathVariable Long courseId) {
        return courseService.findById(courseId);
    }

    @PutMapping("/{courseId}")
    public SimpleResponse update(@PathVariable Long courseId, @RequestBody UpdateCourseRequest updateCourseRequest) {
        return courseService.update(courseId, updateCourseRequest);
    }

    @DeleteMapping("/{courseId}")
    public SimpleResponse delete(@PathVariable Long courseId) {
        return courseService.deleteById(courseId);
    }

    @GetMapping("/all")
    SortedCoursesResponse getAllSortedCourses() {
        return courseService.getAllSortedCourses();
    }

}
