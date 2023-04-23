package finki.mk.uiktBackend.service;

import finki.mk.uiktBackend.model.Professor;

import java.util.List;

public interface ProfessorService {
    List<Professor> getAll();
    Professor get(Long id);
}
