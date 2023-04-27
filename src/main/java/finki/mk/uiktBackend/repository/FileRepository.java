package finki.mk.uiktBackend.repository;

import finki.mk.uiktBackend.model.File;
import finki.mk.uiktBackend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAllBySubject(Subject subject);
}