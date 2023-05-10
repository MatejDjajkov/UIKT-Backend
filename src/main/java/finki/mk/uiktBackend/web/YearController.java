package finki.mk.uiktBackend.web;

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
@RequestMapping("/year")
public class YearController {

    @GetMapping("/all")
    public List<EnumResponse> getAllYears(){
        List<EnumResponse> years = new ArrayList<>();
        AtomicInteger uniqueId = new AtomicInteger(0);
        Arrays.stream(Year.values()).toList().forEach(x -> years.add(new EnumResponse(uniqueId.incrementAndGet(), x.name())));

        return years;
    }

    @GetMapping("/{index}")
    public EnumResponse getYear(@PathVariable int index){
        Year year = Year.getEnumByIndex(index);

        return new EnumResponse(index, year.toString());
    }

}