package finki.mk.uiktBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany()
    @JsonIgnore
    @JoinTable(
            name = "professor_subject",
            joinColumns = {@JoinColumn(name = "professor_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )
    private List<Subject> subjects;

    public Professor(){
        this.subjects=new ArrayList<>();
    }
    public Professor(String name){
        this.name=name;
        this.subjects=new ArrayList<>();
    }
}
