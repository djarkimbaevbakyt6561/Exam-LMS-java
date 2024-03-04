package peaksoft.dto.responses;

import lombok.Builder;
import peaksoft.entites.Student;

import java.util.List;

@Builder
public record SortedByStudyFormatStudentsResponse(
        List<Student> students
) {
}
