package finki.mk.uiktBackend.web;

import finki.mk.uiktBackend.model.enums.SemesterType;
import finki.mk.uiktBackend.model.enums.Year;
import finki.mk.uiktBackend.model.responses.EnumResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/semester/type")
public class SemesterTypeController {


    @GetMapping("/all")
    public List<EnumResponse> getAllSemesterTypes(){

        List<EnumResponse> types =  new ArrayList<>();
        AtomicInteger uniqueId = new AtomicInteger(0);
        Arrays.stream(SemesterType.values()).toList().forEach(x -> types.add(new EnumResponse(uniqueId.incrementAndGet(),x.toString())));

        return types;
    }

    @GetMapping("/{index}")
    public EnumResponse getSemesterType(@PathVariable int index){
        SemesterType semester = SemesterType.getEnumByIndex(index);

        return new EnumResponse(index, semester.toString());
    }
}

