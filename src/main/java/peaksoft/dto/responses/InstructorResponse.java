package peaksoft.dto.responses;

import lombok.Builder;
import peaksoft.enums.Specialization;

@Builder
public record InstructorResponse(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        Specialization specialization
) {
}
