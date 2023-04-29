package finki.mk.uiktBackend.service;

import finki.mk.uiktBackend.model.Professor;
import finki.mk.uiktBackend.model.Subject;
import java.util.List;

public interface ProfessorService {
    List<Professor> getAll();
    Professor get(Long id);
    List<Professor> getByName(String name);
    Professor create(String name, List<Long> subjectsId);
    Professor update(Long id, String name, List<Long> subjectsId);
    Professor delete(Long id);
    List<Subject> getAllSubjects(Long id);
}
