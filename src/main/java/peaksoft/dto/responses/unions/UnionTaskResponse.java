package peaksoft.dto.responses.unions;

import lombok.Builder;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.TaskResponse;

@Builder
public record UnionTaskResponse (
        TaskResponse data,
        SimpleResponse status
){
}
