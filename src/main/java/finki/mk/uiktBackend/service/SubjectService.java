package finki.mk.uiktBackend.service;

import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();

    List<Subject> findAllSubjectsByYear(Year year);

    List<Subject> findAllSubjectsBySemester(SemesterType semesterType);

    List<Subject> findAllSubjectsByModule(String moduleName);

    List<Subject> findAllSubjectsByProfessorName(String professorName);

    List<Subject> findAllSubjectsByYearAndSemesterType(Year year,SemesterType semesterType);

    List<Subject> findAllSubjectsByName(String name);
}
