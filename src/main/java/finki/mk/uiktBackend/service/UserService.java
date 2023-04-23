package finki.mk.uiktBackend.service;


import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.auth.User;
import finki.mk.uiktBackend.model.dto.UserDetailsDto;
import finki.mk.uiktBackend.model.helpers.UserRegisterHelper;

public interface UserService {
    User findUserByEmail(String email);

    void register(String email, String password, UserRegisterHelper helper);

    //    void resetPassword(String password);
    boolean passwordMatches(User user, String password);

    UserDetailsDto getUserDetails();

    User findById(Long id);

    void takeSubject(User user, Subject subject);

    void removeSubject(User user, Subject subject);

}
