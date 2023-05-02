package finki.mk.uiktBackend.service.impl;

import finki.mk.uiktBackend.model.Module;
import finki.mk.uiktBackend.model.Professor;
import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;
import finki.mk.uiktBackend.model.exceptions.SubjectNotFoundException;
import finki.mk.uiktBackend.repository.SubjectRepository;
import finki.mk.uiktBackend.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Subject> findPaginatedSubjects(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return subjectRepository.findAll(pageable);
    }

    //DELETE Opearation
    @Override
    public void deleteSubjectByID(Long id) {
        this.subjectRepository.deleteById(id);
    }

    //CREATE Operation

    public void createSubject(String subjectName, SemesterType semesterType, Year year, List<Professor> professors, List<Module> modules) {
        Subject subject=new Subject(subjectName,semesterType,year);
        subject.setProfessors(professors);
        subject.setModules(modules);
        this.subjectRepository.save(subject);

    }

    //UPDATE Operation
    @Override
    public void editSubject(Long id, String subjectName, SemesterType semesterType, Year year, List<Professor> professors, List<Module> modules) {
        Subject subject=this.subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);
        subject.setName(subjectName);
        subject.setSemesterType(semesterType);
        subject.setYear(year);
        subject.setProfessors(professors);
        subject.setModules(modules);
    }


}
