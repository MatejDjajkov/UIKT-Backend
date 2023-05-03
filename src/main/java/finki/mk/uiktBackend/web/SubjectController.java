package finki.mk.uiktBackend.web;

import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;
import finki.mk.uiktBackend.model.exceptions.SubjectAlreadyExistsException;
import finki.mk.uiktBackend.model.helpers.SubjectHelperAdd;
import finki.mk.uiktBackend.model.helpers.SubjectHelperEdit;
import finki.mk.uiktBackend.service.SubjectService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable Long id) {
        return subjectService.getById(id);
    }

    // Long yearId -> String year
    // Long semesterId -> String semester
    @GetMapping("/filter/semester")
    public List<Subject> getAllSubjectsByYearAndSemester(@RequestParam(required = false) SemesterType semester, @RequestParam Year year) {
        if (semester == null) {
            return subjectService.findAllSubjectsByYear(year);
        } else {
            return subjectService.findAllSubjectsByYearAndSemesterType(year, semester);
        }
    }

    @GetMapping("/search")
    public List<Subject> searchByName(@RequestParam String value) {
        return subjectService.findAllSubjectsByName(value);
    }

    @GetMapping("/page/{pageNo}/{pageSize}")
    public List<Subject> findPaginated(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        Page<Subject> page = subjectService.findPaginatedSubjects(pageNo, pageSize);
        return page.getContent();
    }

    @GetMapping("/totalSubjects")
    public int findNumberOfTotalSubjects() {
        return subjectService.getAllSubjects().size();
    }

    // SemesterType and Year da se prakaat kako Stringovi
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addSubject(@RequestBody SubjectHelperAdd request) {
        if (subjectService.findAllSubjectsByName(request.getName()).size() != 0) {
            throw new SubjectAlreadyExistsException();
        }
        subjectService.createSubject(request.getName(),request.getSemesterType(),request.getYear(),new ArrayList<>(),new ArrayList<>());
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void editSubject(@RequestBody SubjectHelperEdit request) {
        subjectService.editSubject(request.getId(), request.getName(),request.getSemesterType(), request.getYear());
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubjectByID(id);
    }

    //endpoints za editing Professors i Modules na Subject
}
