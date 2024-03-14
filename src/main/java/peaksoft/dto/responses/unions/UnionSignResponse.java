package peaksoft.dto.responses.unions;

import lombok.Builder;
import peaksoft.dto.responses.SignResponse;
import peaksoft.dto.responses.SimpleResponse;

@Builder
public record UnionSignResponse(
        SignResponse data,
        SimpleResponse status
) {
}
