package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entites.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> getCoursesByCompanyId(Long companyId);

    @Query("SELECT c FROM Course c ORDER BY c.dateOfStart")
    List<Course> getSortedCoursesByDateStart();
}
