package peaksoft.dto.responses.unions;

import lombok.Builder;
import peaksoft.dto.responses.InstructorResponse;
import peaksoft.dto.responses.SimpleResponse;

@Builder
public record UnionInstructorResponse(
        InstructorResponse data,
        SimpleResponse status
) {
}
