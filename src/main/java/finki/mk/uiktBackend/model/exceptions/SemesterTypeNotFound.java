package finki.mk.uiktBackend.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.NOT_FOUND)
public class SemesterTypeNotFound extends RuntimeException{
    public SemesterTypeNotFound(){
        super("Semester type not found");
    }
}

