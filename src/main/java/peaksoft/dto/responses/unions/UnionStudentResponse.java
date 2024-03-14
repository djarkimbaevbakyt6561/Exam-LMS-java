package peaksoft.dto.responses.unions;

import lombok.Builder;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.StudentResponse;

@Builder
public record UnionStudentResponse(
        StudentResponse data,
        SimpleResponse status
) {
}
