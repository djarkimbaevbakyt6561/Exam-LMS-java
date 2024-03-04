package peaksoft.service;

import peaksoft.dto.requests.LessonRequest;
import peaksoft.dto.requests.UpdateLessonRequest;
import peaksoft.dto.responses.LessonResponse;
import peaksoft.dto.responses.SimpleResponse;

public interface LessonService {
    SimpleResponse save(LessonRequest lessonRequest);

    LessonResponse findById(Long lessonId);

    SimpleResponse update(Long lessonId, UpdateLessonRequest lessonRequest);

    SimpleResponse deleteById(Long lessonId);
}
