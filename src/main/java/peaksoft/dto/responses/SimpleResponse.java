package peaksoft.dto.responses;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
public record SimpleResponse(
        HttpStatus httpStatus,
        String message
) {

}
