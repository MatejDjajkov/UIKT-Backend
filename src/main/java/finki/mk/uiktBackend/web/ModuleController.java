package finki.mk.uiktBackend.web;

import finki.mk.uiktBackend.model.Module;
import finki.mk.uiktBackend.service.ModuleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/module")
public class ModuleController {
    private final ModuleService moduleService;
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    // Get all modules
    @GetMapping("/all")
    public List<Module> getAllModules(){
        return moduleService.getAllModules();
    }

    // Get a module by its ID
    @GetMapping("/{id}")
    public Module getModuleById(@PathVariable Long id){
        return moduleService.get(id);
    }

    // Delete a module by its ID (only accessible to admins)
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteModule(@PathVariable Long id) {
        moduleService.delete(id);
    }

    //TODO CREATE endpoint and EDIT endpoint
}
