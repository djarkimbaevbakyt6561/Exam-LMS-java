package peaksoft.service;

import peaksoft.dto.requests.TaskRequest;
import peaksoft.dto.requests.UpdateTaskRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionTaskResponse;

public interface TaskService {
    SimpleResponse save(TaskRequest taskRequest);

    UnionTaskResponse findById(Long taskId);

    SimpleResponse update(Long taskId, UpdateTaskRequest taskRequest);

    SimpleResponse deleteById(Long taskId);
}
