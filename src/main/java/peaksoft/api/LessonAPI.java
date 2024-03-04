package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.LessonRequest;
import peaksoft.dto.requests.UpdateLessonRequest;
import peaksoft.dto.responses.LessonResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.service.LessonService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonAPI {
    private final LessonService lessonService;

    @PostMapping("/save")
    SimpleResponse save(@RequestBody LessonRequest lessonRequest) {
        return lessonService.save(lessonRequest);
    }

    @GetMapping("/{lessonId}")
    public LessonResponse getById(@PathVariable Long lessonId) {
        return lessonService.findById(lessonId);
    }

    @PutMapping("/{lessonId}")
    public SimpleResponse update(@PathVariable Long lessonId, @RequestBody UpdateLessonRequest lessonRequest) {
        return lessonService.update(lessonId, lessonRequest);
    }

    @DeleteMapping("/{lessonId}")
    public SimpleResponse delete(@PathVariable Long lessonId) {
        return lessonService.deleteById(lessonId);
    }
}
