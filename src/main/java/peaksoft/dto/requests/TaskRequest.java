package peaksoft.dto.requests;

import peaksoft.entites.Task;

import java.time.LocalDate;

public record TaskRequest(
        String taskName,
        String taskText,
        LocalDate deadLine,
        Long lessonId) {
    public Task build() {
        Task newTask = new Task();
        newTask.setTaskName(this.taskName);
        newTask.setTaskText(this.taskName);
        newTask.setDeadLine(this.deadLine);
        return  newTask;
    }
}
