package finki.mk.uiktBackend.repository;

import finki.mk.uiktBackend.model.Professor;
import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByYear(Year year);
    List<Subject> findAllBySemesterType(SemesterType semesterType);

    @Query("SELECT s FROM Subject s JOIN s.modules m WHERE m.name = :moduleName")
    List<Subject> findAllByModuleName(@Param("moduleName") String moduleName);

    @Query("SELECT s FROM Subject s JOIN s.professors p WHERE p.name = :professorName")
    List<Subject> findByProfessorName(@Param("professorName") String professorName);
}
