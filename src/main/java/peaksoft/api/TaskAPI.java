package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.TaskRequest;
import peaksoft.dto.requests.UpdateTaskRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.TaskResponse;
import peaksoft.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskAPI {
    private final TaskService taskService;

    @PostMapping("/save")
    SimpleResponse save(@RequestBody TaskRequest taskRequest) {
        return taskService.save(taskRequest);
    }

    @GetMapping("/{taskId}")
    public TaskResponse getById(@PathVariable Long taskId) {
        return taskService.findById(taskId);
    }

    @PutMapping("/{taskId}")
    public SimpleResponse update(@PathVariable Long taskId, @RequestBody UpdateTaskRequest taskRequest) {
        return taskService.update(taskId, taskRequest);
    }

    @DeleteMapping("/{taskId}")
    public SimpleResponse delete(@PathVariable Long taskId) {
        return taskService.deleteById(taskId);
    }
}
