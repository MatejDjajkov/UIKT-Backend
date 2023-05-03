package finki.mk.uiktBackend.model;

import finki.mk.uiktBackend.model.enums.ExamType;
import finki.mk.uiktBackend.model.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Subject subject;

    @Enumerated(EnumType.STRING)
    private ExamType examType;

    private String mimeType;

    private byte[] content;

    @Enumerated(EnumType.STRING)
    private Status status;

}
