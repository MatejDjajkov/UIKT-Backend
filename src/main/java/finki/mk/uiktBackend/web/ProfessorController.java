package finki.mk.uiktBackend.web;

import finki.mk.uiktBackend.model.Professor;
import finki.mk.uiktBackend.service.ProfessorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;
    public ProfessorController(ProfessorService professorService){
        this.professorService=professorService;
    }

    // Get all professors
    @GetMapping("/all")
    public List<Professor> getAllProfessors(){
        return professorService.getAll();
    }

    // Get a professor by their ID
    @GetMapping("/{id}")
    public Professor getProfessorById(@PathVariable Long id){
        return professorService.get(id);
    }

    // Delete a professor by their ID (only accessible to admins)
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProfessor(@PathVariable Long id) {
        professorService.delete(id);
    }
    //TODO CREATE endpoint and EDIT endpoint
}
