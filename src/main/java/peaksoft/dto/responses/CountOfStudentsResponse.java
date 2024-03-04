package peaksoft.dto.responses;

import lombok.Builder;

@Builder
public record CountOfStudentsResponse(
        int count
) {
}
