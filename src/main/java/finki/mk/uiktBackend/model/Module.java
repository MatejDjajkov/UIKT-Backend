package finki.mk.uiktBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany()
    @JsonIgnore
    @JoinTable(
            name = "module_subject",
            joinColumns = {@JoinColumn(name = "module_id")},
            inverseJoinColumns = {@JoinColumn(name = "subject_id")}
    )
    private List<Subject> subjects;
    public Module(){
        this.subjects=new ArrayList<>();
    }
    public Module(String name){
        this.name=name;
        this.subjects=new ArrayList<>();
    }
}
