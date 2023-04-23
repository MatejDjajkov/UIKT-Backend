package finki.mk.uiktBackend.web;

import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
@AllArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    @GetMapping("/all")
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }
}
