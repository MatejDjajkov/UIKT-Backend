package finki.mk.uiktBackend.model.requests;

import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectAddRequest {

    private String name;
    private int year;
    private int semesterType;
}
