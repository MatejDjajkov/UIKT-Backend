package finki.mk.uiktBackend.service;

import finki.mk.uiktBackend.model.Professor;
import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.Module;
import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;

import java.util.List;

public interface ModuleService {
    Module get(Long id);
    List<Module> getAllModules();
    List<Module> getByName(String name);
    Module create(String name, List<Long> subjectsId);
    Module update(Long id, String name, List<Long> subjectsId);
    Module delete(Long id);
    List<Subject> getAllSubjects(Long id);
}
