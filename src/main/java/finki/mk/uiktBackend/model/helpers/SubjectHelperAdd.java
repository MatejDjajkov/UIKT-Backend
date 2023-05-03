package finki.mk.uiktBackend.model.helpers;

import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectHelperAdd {

    private String name;
    private Year year;
    private SemesterType semesterType;
}
