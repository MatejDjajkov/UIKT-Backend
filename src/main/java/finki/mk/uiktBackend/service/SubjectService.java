package finki.mk.uiktBackend.service;

import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();

    List<Subject> findAllByYear(Year year);

    List<Subject> findAllBySemester(SemesterType semesterType);

    List<Subject> findAllByModule(String moduleName);

    List<Subject> findAllByProfessorName(String professorName);
}
