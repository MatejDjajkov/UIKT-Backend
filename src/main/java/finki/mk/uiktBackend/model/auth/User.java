package finki.mk.uiktBackend.model.auth;

import finki.mk.uiktBackend.model.Subject;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users", schema = "auth_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String username;

    private String name;

    private String surname;

    private LocalDateTime date_created;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoles> roles;

    @ManyToMany
    private List<Subject> favoriteSubjects;

    public User(String email, String password, String username, String name, String surname, LocalDateTime date_created) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.date_created = date_created;
    }
    public User(){
        this.roles=new ArrayList<>();
        this.favoriteSubjects=new ArrayList<>();
    }
}
