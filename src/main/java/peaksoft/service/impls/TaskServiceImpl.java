package peaksoft.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.TaskRequest;
import peaksoft.dto.requests.UpdateTaskRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.TaskResponse;
import peaksoft.dto.responses.unions.UnionTaskResponse;
import peaksoft.entities.Task;
import peaksoft.entities.Lesson;
import peaksoft.repositories.LessonRepository;
import peaksoft.repositories.TaskRepository;
import peaksoft.service.TaskService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Override
    public SimpleResponse save(TaskRequest taskRequest) {
        try {
            Lesson lesson = lessonRepository.findById(taskRequest.lessonId()).orElseThrow(NoSuchElementException::new);
            Task task = taskRequest.build();
            task.setTaskText(taskRequest.taskText());
            task.setTaskName(taskRequest.taskName());
            task.setDeadLine(taskRequest.deadLine());
            task.setLesson(lesson);
            taskRepository.save(task);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully saved!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
        }
    }

    @Override
    public UnionTaskResponse findById(Long taskId) {
        try {
            Task task = taskRepository.findById(taskId).orElseThrow(NoSuchElementException::new);
            return UnionTaskResponse.builder()
                    .data(TaskResponse.builder()
                            .id(task.getId())
                            .taskText(task.getTaskText())
                            .taskName(task.getTaskName())
                            .deadLine(task.getDeadLine())
                            .build())
                    .status(SimpleResponse.builder()
                            .message("Successfully returned!")
                            .httpStatus(HttpStatus.OK)
                            .build())
                    .build();
        } catch (NoSuchElementException e) {
            return UnionTaskResponse.builder()
                    .data(null)
                    .status(SimpleResponse.builder()
                            .message("Task with id " + taskId + " is not found!")
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build())
                    .build();
        }
    }

    @Override
    @Transactional
    public SimpleResponse update(Long taskId, UpdateTaskRequest taskRequest) {
        try {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new NoSuchElementException("Task with id " + taskId + " is not found!"));
            task.setTaskName(taskRequest.taskName());
            task.setTaskText(taskRequest.taskText());
            task.setDeadLine(taskRequest.deadLine());
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully updated!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long taskId) {
        try {
            taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task with id " + taskId + " is not found!"));
            taskRepository.deleteById(taskId);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully deleted!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }
}
