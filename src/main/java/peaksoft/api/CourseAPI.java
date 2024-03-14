package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.CourseRequest;
import peaksoft.dto.requests.UpdateCourseRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionCourseResponse;
import peaksoft.dto.responses.unions.UnionSortedCoursesResponse;
import peaksoft.service.CourseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseAPI {
    private final CourseService courseService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    SimpleResponse save(@RequestBody CourseRequest courseRequest) {
        return courseService.save(courseRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    @GetMapping("/{courseId}")
    public UnionCourseResponse getById(@PathVariable Long courseId) {
        return courseService.findById(courseId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{courseId}")
    public SimpleResponse update(@PathVariable Long courseId, @RequestBody UpdateCourseRequest updateCourseRequest) {
        return courseService.update(courseId, updateCourseRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{courseId}")
    public SimpleResponse delete(@PathVariable Long courseId) {
        return courseService.deleteById(courseId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    @GetMapping("/all")
    UnionSortedCoursesResponse getAllSortedCourses() {
        return courseService.getAllSortedCourses();
    }

}
