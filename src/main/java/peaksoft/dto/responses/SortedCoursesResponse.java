package peaksoft.dto.responses;

import lombok.Builder;
import peaksoft.entities.Course;

import java.util.List;

@Builder
public record SortedCoursesResponse(
        List<Course> courses
) {
}
