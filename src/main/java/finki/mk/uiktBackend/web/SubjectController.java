package finki.mk.uiktBackend.web;

import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;
import finki.mk.uiktBackend.model.exceptions.SubjectAlreadyExistsException;
import finki.mk.uiktBackend.model.requests.SubjectAddRequest;
import finki.mk.uiktBackend.model.requests.SubjecEditRequest;
import finki.mk.uiktBackend.service.SubjectService;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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

    @GetMapping("/filter")
    public List<Subject> getAllSubjectsByYearAndSemester(
            @RequestParam(required = false) @Nullable Integer semesterTypeId,
            @RequestParam(required = false) @Nullable Integer yearId,
            @RequestParam(required = false) @Nullable Long moduleId,
            @RequestParam(required = false) @Nullable Long professorId) {

        return subjectService.filterSubjects(semesterTypeId, yearId, moduleId, professorId);
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

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addSubject(@RequestBody SubjectAddRequest request) {
        if (subjectService.findAllSubjectsByName(request.getName()).size() != 0) {
            throw new SubjectAlreadyExistsException();
        }

        SemesterType semester = SemesterType.getEnumByIndex(request.getSemesterType());
        Year year = Year.getEnumByIndex(request.getYear());
        subjectService.createSubject(request.getName(), semester, year, new ArrayList<>(), new ArrayList<>());
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void editSubject(@RequestBody SubjecEditRequest request) {

        SemesterType semester = SemesterType.getEnumByIndex(request.getSemesterType());
        Year year = Year.getEnumByIndex(request.getYear());
        subjectService.editSubject(request.getId(), request.getName(), semester, year);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubjectByID(id);
    }

    // endpoints za editing Professors i Modules na Subject
}

/*
 * 
 * This is a controller that handles requests related to subjects. The
 * controller has multiple endpoints that perform different CRUD operations on
 * the Subject entity.
 * The controller provides endpoints to:
 * /all - returns a list of all the subjects.
 * /{id} - returns the subject with the specified ID.
 * /filter - returns a list of subjects filtered by semester type, year, module,
 * and professor.
 * /search - returns a list of subjects matching the provided search query.
 * /page/{pageNo}/{pageSize} - returns a list of subjects with pagination.
 * /totalSubjects - returns the total number of subjects.
 * /add - adds a new subject to the database.
 * /edit - edits an existing subject in the database.
 * /delete/{id} - deletes the subject with the specified ID.
 * The controller uses the SubjectService to perform database operations and the
 * SubjectAddRequest and SubjecEditRequest classes to handle request bodies.
 */