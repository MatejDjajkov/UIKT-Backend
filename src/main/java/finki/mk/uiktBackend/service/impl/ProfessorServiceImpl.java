package finki.mk.uiktBackend.service.impl;

import finki.mk.uiktBackend.model.Professor;
import finki.mk.uiktBackend.repository.ProfessorRepository;
import finki.mk.uiktBackend.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;
    @Override
    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    @Override
    public Professor get(Long id) {
        return professorRepository.findById(id).orElse(null);
    }
}
