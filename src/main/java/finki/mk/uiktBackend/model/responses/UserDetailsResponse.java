package finki.mk.uiktBackend.model.responses;

import lombok.Data;

import java.util.List;

@Data
public class UserDetailsResponse {
    private String username;
    private String email;
    private List<String> roles;
    private Long id;
}
