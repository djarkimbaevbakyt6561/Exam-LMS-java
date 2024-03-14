package peaksoft.dto.requests;

import peaksoft.entities.Lesson;

public record LessonRequest(
        String lessonName,
        Long courseId
) {
    public Lesson build() {
        Lesson newLesson = new Lesson();
        newLesson.setLessonName(this.lessonName);
        return newLesson;
    }
}
