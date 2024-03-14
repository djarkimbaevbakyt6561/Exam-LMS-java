package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Student;
import peaksoft.enums.StudyFormat;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT COUNT(s) FROM Student s JOIN s.group g JOIN g.courses c WHERE c.company.id = :companyId")
    Long getCountOfStudentsByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT s FROM Student s JOIN s.group g JOIN g.courses c WHERE c.company.id = :companyId AND s.studyFormat = :studyFormat")
    List<Student> getAllCompaniesStudentsFilteredByStudyFormat(@Param("studyFormat") StudyFormat studyFormat, @Param("companyId") Long companyId);


    Optional<Student> findStudentByLoginDetailsId(Long loginDetailsId);
}
