package finki.mk.uiktBackend.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ExamTypeNotFoundException  extends RuntimeException {
    public ExamTypeNotFoundException(){
        super("Exam type not found!");
    }
}
