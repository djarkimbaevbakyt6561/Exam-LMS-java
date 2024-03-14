package peaksoft.dto.responses.unions;

import lombok.Builder;
import peaksoft.dto.responses.AllStudentsResponse;
import peaksoft.dto.responses.SimpleResponse;

@Builder
public record UnionAllStudentsResponse (
        AllStudentsResponse data,
        SimpleResponse status
){
}
