package finki.mk.uiktBackend.model.requests;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String username;
    private String name;
    private String surname;
}
