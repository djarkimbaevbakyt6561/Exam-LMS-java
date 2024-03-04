package peaksoft.dto.responses;

import lombok.Builder;
import peaksoft.entites.Student;

import java.util.List;

@Builder
public record GroupResponse(
        Long id,
        String groupName,
        String imageLink,
        String description,
        List<Student> students
) {
}
