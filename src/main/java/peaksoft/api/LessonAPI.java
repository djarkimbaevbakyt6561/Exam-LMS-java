package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.LessonRequest;
import peaksoft.dto.requests.UpdateLessonRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionLessonResponse;
import peaksoft.service.LessonService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonAPI {
    private final LessonService lessonService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping("/save")
    SimpleResponse save(@RequestBody LessonRequest lessonRequest) {
        return lessonService.save(lessonRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    @GetMapping("/{lessonId}")
    public UnionLessonResponse getById(@PathVariable Long lessonId) {
        return lessonService.findById(lessonId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{lessonId}")
    public SimpleResponse update(@PathVariable Long lessonId, @RequestBody UpdateLessonRequest lessonRequest) {
        return lessonService.update(lessonId, lessonRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @DeleteMapping("/{lessonId}")
    public SimpleResponse delete(@PathVariable Long lessonId) {
        return lessonService.deleteById(lessonId);
    }
}
