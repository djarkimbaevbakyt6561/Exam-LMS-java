package peaksoft.dto.responses;

import lombok.Builder;
import peaksoft.entities.Student;

import java.util.List;

@Builder
public record AllStudentsResponse(
        List<Student> students
) {
}
