package peaksoft.dto.requests;

import java.time.LocalDate;

public record UpdateTaskRequest(
        String taskName,
        String taskText,
        LocalDate deadLine
) {
}
