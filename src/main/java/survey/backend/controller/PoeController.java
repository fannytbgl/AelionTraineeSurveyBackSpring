package survey.backend.controller;

import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.dto.TraineeDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/poe")
public class PoeController {


    @GetMapping
    public Set<PoeDto> list() {
        return Set.of(
                PoeDto.builder()
                        .id(1)
                        .title("Java Fullstack")
                        .beginDate(LocalDate.of(2022, 10, 24))
                        .endDate(LocalDate.of(2023, 1, 27))
                        .build(),
                PoeDto.builder()
                        .id(2)
                        .title("Data Scientist")
                        .beginDate(LocalDate.of(2022, 9, 24))
                        .endDate(LocalDate.of(2023, 3, 27))
                        .build()

        );
    }


    @GetMapping("{id}")
    public Optional<PoeDto> one(@PathVariable("id") int id) {
        return Optional.of(PoeDto.builder()
                .id(3)
                .title("Advanced Claquette")
                .beginDate(LocalDate.of(2020, 9, 24))
                .endDate(LocalDate.of(2021, 3, 27))
                .build());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PoeDto add(@RequestBody PoeDto poeDto){
        //TODO : add in under layer
        poeDto.setId(54);
        return poeDto;
    }
}

