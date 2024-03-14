package peaksoft.dto.responses.unions;

import lombok.Builder;
import peaksoft.dto.responses.CompanyResponse;
import peaksoft.dto.responses.SimpleResponse;
@Builder
public record UnionCompanyResponse(
        CompanyResponse data,
        SimpleResponse status
) {
}
