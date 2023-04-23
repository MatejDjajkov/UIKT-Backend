package finki.mk.uiktBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SemesterType semesterType;

    @Enumerated(EnumType.STRING)
    private Year year;

    @JsonIgnore
    @OneToMany
    private List<File> files;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "favoriteSubjects", fetch = FetchType.EAGER)
//    private List<User> usersSubject;

    @ManyToMany(mappedBy = "subjects")
    private List<Professor> professors;

    @ManyToMany(mappedBy = "subjects")
    private List<Module> modules;

    public Subject(){
        this.files=new ArrayList<>();
        this.professors=new ArrayList<>();
        this.modules=new ArrayList<>();
    }

    public Subject(String name, SemesterType semesterType, Year year) {
        this.name = name;
        this.semesterType = semesterType;
        this.year = year;
        this.files=new ArrayList<>();
        this.professors=new ArrayList<>();
        this.modules=new ArrayList<>();
    }

}
