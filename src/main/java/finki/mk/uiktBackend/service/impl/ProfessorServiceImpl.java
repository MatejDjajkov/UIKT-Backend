package finki.mk.uiktBackend.service.impl;

import finki.mk.uiktBackend.model.Professor;
import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.repository.ProfessorRepository;
import finki.mk.uiktBackend.repository.SubjectRepository;
import finki.mk.uiktBackend.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    @Override
    public List<Professor> getAll() {
        return professorRepository.findAll();
    }
    @Override
    public Professor get(Long id) {
        return professorRepository.findById(id).orElse(null);
    }
    @Override
    public List<Professor> getByName(String name) {
        return professorRepository.findAllByName(name);
    }
    @Override
    public Professor create(String name, List<Long> subjectsId) {
        List<Subject> subjects = this.subjectRepository.findAllById(subjectsId);
        Professor professor = new Professor(name);
        professor.setSubjects(subjects);
        return this.professorRepository.save(professor);
    }
    @Override
    public Professor update(Long id, String name, List<Long> subjectsId) {
        List<Subject> subjects = this.subjectRepository.findAllById(subjectsId);
        Professor professor = this.get(id);
        professor.setName(name);
        professor.setSubjects(subjects);
        return this.professorRepository.save(professor);
    }
    @Override
    public Professor delete(Long id) {
        Professor professor = this.get(id);
        this.professorRepository.delete(professor);
        return professor;
    }

    @Override
    public List<Subject> getAllSubjects(Long id) {
        Professor professor = this.get(id);
        List<Subject> subjects = professor.getSubjects();
        return subjects;
    }


}
