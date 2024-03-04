package peaksoft.dto.responses;

import lombok.Builder;
import peaksoft.enums.StudyFormat;

@Builder
public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        StudyFormat studyFormat
) {
}
