package finki.mk.uiktBackend.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDetailsDto {
    private String username;
    private String email;
    private List<String> roles;
    private Long id;
}
