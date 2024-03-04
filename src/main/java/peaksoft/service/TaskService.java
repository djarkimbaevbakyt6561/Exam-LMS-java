package peaksoft.service;

import peaksoft.dto.requests.TaskRequest;
import peaksoft.dto.requests.UpdateTaskRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.TaskResponse;

public interface TaskService {
    SimpleResponse save(TaskRequest taskRequest);

    TaskResponse findById(Long taskId);

    SimpleResponse update(Long taskId, UpdateTaskRequest taskRequest);

    SimpleResponse deleteById(Long taskId);
}
