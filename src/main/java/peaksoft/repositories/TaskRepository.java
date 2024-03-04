package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entites.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}