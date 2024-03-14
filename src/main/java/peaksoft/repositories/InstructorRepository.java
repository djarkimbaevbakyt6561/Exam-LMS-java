package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Instructor;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    @Query("SELECT i FROM Instructor i JOIN i.companies c WHERE c.id = :companyId")
    List<Instructor> getInstructorsByCompanyId(@Param("companyId") Long companyId);

    Optional<Instructor> findInstructorByLoginDetailsId(Long loginDetailsId);

}
