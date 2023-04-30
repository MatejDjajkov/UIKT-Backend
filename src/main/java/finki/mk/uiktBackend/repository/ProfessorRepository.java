package finki.mk.uiktBackend.repository;

import finki.mk.uiktBackend.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    public List<Professor> findAllByName(String name);
}