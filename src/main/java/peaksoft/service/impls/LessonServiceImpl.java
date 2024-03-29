package peaksoft.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.LessonRequest;
import peaksoft.dto.requests.UpdateLessonRequest;
import peaksoft.dto.responses.LessonResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionLessonResponse;
import peaksoft.entities.Course;
import peaksoft.entities.Lesson;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.LessonRepository;
import peaksoft.service.LessonService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public SimpleResponse save(LessonRequest lessonRequest) {
        try {
            Course course = courseRepository.findById(lessonRequest.courseId()).orElseThrow(NoSuchElementException::new);
            Lesson lesson = lessonRequest.build();
            lesson.setCourse(course);
            lessonRepository.save(lesson);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully saved!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
        }
    }

    @Override
    public UnionLessonResponse findById(Long lessonId) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(NoSuchElementException::new);
            return UnionLessonResponse.builder()
                    .data(LessonResponse.builder()
                            .id(lesson.getId())
                            .lessonName(lesson.getLessonName())
                            .tasks(lesson.getTasks())
                            .build())
                    .status(SimpleResponse.builder()
                            .message("Successfully returned!")
                            .httpStatus(HttpStatus.OK)
                            .build())
                    .build();
        } catch (NoSuchElementException e) {
            return UnionLessonResponse.builder()
                    .data(null)
                    .status(SimpleResponse.builder()
                            .message("Lesson with id " + lessonId + " is not found!")
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build())
                    .build();
        }
    }

    @Override
    @Transactional
    public SimpleResponse update(Long lessonId, UpdateLessonRequest lessonRequest) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId)
                    .orElseThrow(() -> new NoSuchElementException("Lesson with id " + lessonId + " is not found!"));
            lesson.setLessonName(lessonRequest.lessonName());
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully updated!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long lessonId) {
        try {
            lessonRepository.findById(lessonId).orElseThrow(() -> new NoSuchElementException("Lesson with id " + lessonId + " is not found!"));
            lessonRepository.deleteById(lessonId);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully deleted!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }
}
