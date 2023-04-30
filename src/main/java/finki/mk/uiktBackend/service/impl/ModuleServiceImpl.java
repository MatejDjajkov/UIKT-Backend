package finki.mk.uiktBackend.service.impl;

import finki.mk.uiktBackend.repository.ModuleRepository;
import finki.mk.uiktBackend.repository.SubjectRepository;
import finki.mk.uiktBackend.model.Module;
import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.service.ModuleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModuleServiceImpl implements ModuleService {
    private final ModuleRepository moduleRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    @Override
    public Module get(Long id) {
        return moduleRepository.getById(id);
    }

    @Override
    public List<Module> getByName(String name) {
        return moduleRepository.findAllByName(name);
    }

    @Override
    public Module create(String name, List<Long> subjectsId) {
        List<Subject> subjects = this.subjectRepository.findAllById(subjectsId);
        Module module = new Module(name);
        module.setSubjects(subjects);
        return this.moduleRepository.save(module);
    }

    @Override
    public Module update(Long id, String name, List<Long> subjectsId) {
        List<Subject> subjects = this.subjectRepository.findAllById(subjectsId);
        Module module = this.get(id);
        module.setName(name);
        module.setSubjects(subjects);
        return this.moduleRepository.save(module);
    }

    @Override
    public Module delete(Long id) {
        Module module = this.get(id);
        this.moduleRepository.delete(module);
        return module;
    }

    @Override
    public List<Subject> getAllSubjects(Long id) {
        Module module = this.get(id);
        List<Subject> subjects = module.getSubjects();
        return subjects;
    }

}
