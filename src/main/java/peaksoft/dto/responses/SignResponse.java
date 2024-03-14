package peaksoft.dto.responses;

import lombok.Builder;
import peaksoft.enums.Role;

@Builder
public record SignResponse(Long userId,
                           Role role,
                           String email,
                           String token) {
}
