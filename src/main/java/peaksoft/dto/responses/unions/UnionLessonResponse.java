package peaksoft.dto.responses.unions;

import lombok.Builder;
import peaksoft.dto.responses.LessonResponse;
import peaksoft.dto.responses.SimpleResponse;

@Builder
public record UnionLessonResponse(
        LessonResponse data,
        SimpleResponse status
) {
}
