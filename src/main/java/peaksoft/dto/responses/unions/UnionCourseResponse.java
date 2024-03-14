package peaksoft.dto.responses.unions;

import lombok.Builder;
import peaksoft.dto.responses.CourseResponse;
import peaksoft.dto.responses.SimpleResponse;

@Builder
public record UnionCourseResponse(
        CourseResponse data,
        SimpleResponse status
) {
}
