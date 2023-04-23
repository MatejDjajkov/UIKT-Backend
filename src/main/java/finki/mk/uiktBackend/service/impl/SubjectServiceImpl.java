package finki.mk.uiktBackend.service.impl;

import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.repository.SubjectRepository;
import finki.mk.uiktBackend.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
