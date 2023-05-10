package finki.mk.uiktBackend.service;


import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.auth.UserInApp;
import finki.mk.uiktBackend.model.responses.UserDetailsResponse;
import finki.mk.uiktBackend.model.requests.UserRegisterRequest;

public interface UserService {
    UserInApp findUserByEmail(String email);

    void register(String email, String password, UserRegisterRequest request);

    //    void resetPassword(String password);
    boolean passwordMatches(UserInApp user, String password);

    UserDetailsResponse getUserDetails();

    UserInApp findById(Long id);

    void takeSubject(UserInApp user, Subject subject);

    void removeSubject(UserInApp user, Subject subject);

}
