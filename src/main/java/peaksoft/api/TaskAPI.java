package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.TaskRequest;
import peaksoft.dto.requests.UpdateTaskRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionTaskResponse;
import peaksoft.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskAPI {
    private final TaskService taskService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping("/save")
    SimpleResponse save(@RequestBody TaskRequest taskRequest) {
        return taskService.save(taskRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'STUDENT')")
    @GetMapping("/{taskId}")
    public UnionTaskResponse getById(@PathVariable Long taskId) {
        return taskService.findById(taskId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{taskId}")
    public SimpleResponse update(@PathVariable Long taskId, @RequestBody UpdateTaskRequest taskRequest) {
        return taskService.update(taskId, taskRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @DeleteMapping("/{taskId}")
    public SimpleResponse delete(@PathVariable Long taskId) {
        return taskService.deleteById(taskId);
    }
}
