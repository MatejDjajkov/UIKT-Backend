package finki.mk.uiktBackend.web;

import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.auth.UserInApp;
import finki.mk.uiktBackend.model.responses.UserDetailsResponse;
import finki.mk.uiktBackend.model.exceptions.EmailAlreadyExistsException;
import finki.mk.uiktBackend.model.requests.UserRegisterRequest;
import finki.mk.uiktBackend.service.SubjectService;
import finki.mk.uiktBackend.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final SubjectService subjectService;

    public UserController(UserService userService, SubjectService subjectService) {
        this.userService = userService;
        this.subjectService = subjectService;
    }


    @PostMapping("/register")
    public void register(@RequestHeader String email,
                         @RequestHeader String password,
                         @RequestBody UserRegisterRequest request) {

        UserInApp user = userService.findUserByEmail(email);
        if (user != null) {
            throw new EmailAlreadyExistsException();
        }
        userService.register(email, password, request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        request.getSession().invalidate();
    }

    @GetMapping("/details")
    @PreAuthorize("isAuthenticated()")
    public UserDetailsResponse userDetails(){
        return userService.getUserDetails();
    }

    @GetMapping("/{id}/subjects")
    public List<Subject> getUserSubjects(@PathVariable Long id){
        UserInApp user=userService.findById(id);
        return user.getFavoriteSubjects();
    }

    @GetMapping("/takeSubject")
    public void subjectTakenByUser(@RequestParam Long userId, @RequestParam Long subjectId){
        UserInApp user=userService.findById(userId);
        Subject subject=subjectService.getById(subjectId);
        userService.takeSubject(user,subject);

    }

    @GetMapping("/removeSubject")
    public void removeSubjectForUser(@RequestParam Long userId, @RequestParam Long subjectId){
        UserInApp user=userService.findById(userId);
        Subject subject=subjectService.getById(subjectId);
        userService.removeSubject(user,subject);
    }
}