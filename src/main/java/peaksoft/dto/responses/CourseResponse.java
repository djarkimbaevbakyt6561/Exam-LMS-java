package peaksoft.dto.responses;

import lombok.Builder;

@Builder
public record CourseResponse (
        Long id,
        String courseName,
        String description
){
}
