package finki.mk.uiktBackend.service.impl;

import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;
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

    @Override
    public List<Subject> findAllSubjectsByYear(Year year) {
        return subjectRepository.findAllByYear(year);
    }

    @Override
    public List<Subject> findAllSubjectsBySemester(SemesterType semesterType) {
        return subjectRepository.findAllBySemesterType(semesterType);
    }

    @Override
    public List<Subject> findAllSubjectsByModule(String moduleName) {
        return subjectRepository.findAllByModuleName(moduleName);
    }

    @Override
    public List<Subject> findAllSubjectsByProfessorName(String professorName) {
        return subjectRepository.findByProfessorName(professorName);
    }

    @Override
    public List<Subject> findAllSubjectsByYearAndSemesterType(Year year, SemesterType semesterType) {
        return subjectRepository.findAllByYearAndSemesterType(year,semesterType);
    }

    @Override
    public List<Subject> findAllSubjectsByName(String name) {
        return subjectRepository.findAllByNameContainingIgnoreCase(name);
    }


}
