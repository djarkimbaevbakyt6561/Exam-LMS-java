package peaksoft.dto.responses;

import lombok.Builder;
import peaksoft.entities.Task;

import java.util.List;

@Builder
public record LessonResponse(
        Long id,
        List<Task> tasks,
        String lessonName) {

}
