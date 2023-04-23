package finki.mk.uiktBackend;

import finki.mk.uiktBackend.model.Professor;
import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.service.ProfessorService;
import finki.mk.uiktBackend.service.SubjectService;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SubjectTest {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ProfessorService professorService;

    @Test
    public void testSubjects(){
        List<Subject> subjects= subjectService.getAllSubjects();
        System.out.println(subjects.size());
        assert true;
    }

    @Test
    public void testProfessors(){
        List<Professor> professors= professorService.getAll();
        System.out.println(professors.size());
        assert true;
    }

    @Test
    public void testProfessorsOne(){
        Professor p=professorService.get(1L);
        System.out.println(p.getName());
        p.getSubjects().forEach(x-> System.out.println(x.getName()));
        assert true;
    }
}
