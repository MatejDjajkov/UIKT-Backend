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
    public List<EnumResponse> getAllYears() {
        List<EnumResponse> years = new ArrayList<>();
        AtomicInteger uniqueId = new AtomicInteger(0);
        Arrays.stream(Year.values()).toList()
                .forEach(x -> years.add(new EnumResponse(uniqueId.incrementAndGet(), x.name())));

        return years;
    }

    @GetMapping("/{index}")
    public EnumResponse getYear(@PathVariable int index) {
        Year year = Year.getEnumByIndex(index);

        return new EnumResponse(index, year.toString());
    }

}

/*
 * The YearController class defines two API endpoints for retrieving information
 * about the Year enum. The first endpoint, getAllYears(), returns a list of all
 * the academic years. The second endpoint, getYear(index), returns the Year
 * enumeration value at the specified index.
 * 
 * The getAllYears() method creates a new list of EnumResponse objects, which
 * contains a unique ID and the name of each Year value. It uses the
 * AtomicInteger class to generate unique IDs for each value. The
 * Arrays.stream(Year.values()) method is used to convert the Year enum values
 * to a stream, which is then converted to a list using the toList() method.
 * 
 * The getYear(index) method retrieves the Year enumeration value at the
 * specified index using the getEnumByIndex(index) static method of the Year
 * enum. It then creates a new EnumResponse object with the index and string
 * representation of the Year value.
 */