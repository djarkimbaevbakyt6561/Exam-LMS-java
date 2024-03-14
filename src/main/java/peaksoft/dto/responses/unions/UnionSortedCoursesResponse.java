package peaksoft.dto.responses.unions;

import lombok.Builder;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.SortedCoursesResponse;

@Builder
public record UnionSortedCoursesResponse(
        SortedCoursesResponse data,
        SimpleResponse status
) {
}
