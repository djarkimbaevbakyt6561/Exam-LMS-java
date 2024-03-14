package peaksoft.dto.responses.unions;

import lombok.Builder;
import peaksoft.dto.responses.GroupResponse;
import peaksoft.dto.responses.SimpleResponse;

@Builder
public record UnionGroupResponse(
        GroupResponse data,
        SimpleResponse status
) {
}
