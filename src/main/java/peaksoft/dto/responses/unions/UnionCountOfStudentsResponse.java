package peaksoft.dto.responses.unions;

import lombok.Builder;
import peaksoft.dto.responses.CountOfStudentsResponse;
import peaksoft.dto.responses.SimpleResponse;

@Builder
public record UnionCountOfStudentsResponse(
        CountOfStudentsResponse data,
        SimpleResponse status
) {

}
