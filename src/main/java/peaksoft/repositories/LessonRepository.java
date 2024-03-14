package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Lesson;
@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}