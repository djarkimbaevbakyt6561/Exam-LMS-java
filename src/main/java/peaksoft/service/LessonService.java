package peaksoft.service;

import peaksoft.dto.requests.LessonRequest;
import peaksoft.dto.requests.UpdateLessonRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionLessonResponse;

public interface LessonService {
    SimpleResponse save(LessonRequest lessonRequest);

    UnionLessonResponse findById(Long lessonId);

    SimpleResponse update(Long lessonId, UpdateLessonRequest lessonRequest);

    SimpleResponse deleteById(Long lessonId);
}
