package finki.mk.uiktBackend.service;

import finki.mk.uiktBackend.model.Module;
import finki.mk.uiktBackend.model.Professor;
import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SubjectService {

    //READ Operations
    List<Subject> getAllSubjects();

    Subject getById(Long id);

    List<Subject> findAllSubjectsByYear(Year year);

    List<Subject> findAllSubjectsBySemester(SemesterType semesterType);

    List<Subject> findAllSubjectsByModule(String moduleName);

    List<Subject> findAllSubjectsByProfessorName(String professorName);

    List<Subject> findAllSubjectsByYearAndSemesterType(Year year,SemesterType semesterType);
    List<Subject> filterSubjects(Integer semesterType, Integer year, Long moduleId, Long professorId);

    List<Subject> findAllSubjectsByName(String name);

    Page<Subject> findPaginatedSubjects(int pageNo, int pageSize);

    //DELETE OPERATION
    void deleteSubjectByID(Long id);

    //CREATE OPERATION
    public void createSubject(String subjectName, SemesterType semesterType, Year year, List<Professor> professors, List<Module> modules);

    //UPDATE OPERATION

    public void editSubject(Long id,String subjectName,SemesterType semesterType,Year year);


}
