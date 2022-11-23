package survey.backend.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("api/trainee")
public class TraineeController {

    @Autowired // DI (Dependency Injection)
    private TraineeService traineeService;

    @GetMapping
    public Set<TraineeDto> list() {
        return traineeService.findAll();
//        return Set.of(
//                TraineeDto.builder()
//                        .id(1)
//                        .lastName("Tabail")
//                        .firstName("Zelda")
//                        .birthDate(LocalDate.of(2010, 10, 10))
//                        .build(),
//                TraineeDto.builder()
//                        .id(2)
//                        .lastName("Tabail")
//                        .firstName("Léon")
//                        .birthDate(LocalDate.of(2010, 8, 11))
//                        .build(),
//                TraineeDto.builder()
//                        .id(3)
//                        .lastName("Tabail")
//                        .firstName("Hermione")
//                        .birthDate(LocalDate.of(2011, 10, 10))
//                        .build(),
//                TraineeDto.builder()
//                        .id(4)
//                        .lastName("Des Bois")
//                        .firstName("Flirt")
//                        .birthDate(LocalDate.of(1992, 3, 8))
//                        .build(),
//                TraineeDto.builder()
//                        .id(5)
//                        .lastName("Gargaroc")
//                        .firstName("Graou")
//                        .birthDate(LocalDate.of(1942, 6, 8))
//                        .build(),
//                TraineeDto.builder()
//                        .id(6)
//                        .lastName("Lavidala")
//                        .firstName("Pétunia")
//                        .birthDate(LocalDate.of(1938, 6, 8))
//                        .build()
//        );
    }

    @GetMapping("{id}")
    public TraineeDto one(@PathVariable("id") int id) {
        Optional<TraineeDto> optTraineeDto = traineeService.findById(id);
        if (optTraineeDto.isPresent()){
            return optTraineeDto.get();
        } else {
            throw new IllegalArgumentException(
                    "Trainee with id " + " ot found");
        }

//        return Optional.of(TraineeDto.builder()
//                .id(id)
//                .lastName("Snow")
//                .firstName("John")
//                .birthDate(LocalDate.of(1900, 7, 1))
//                .build());
    }

    @GetMapping("search")
    public Set<TraineeDto> search(
            @RequestParam(name="fn", required = false) String firstName,
            @RequestParam(name="ln", required = false) String lastName
    ) {
        return Set.of(
                TraineeDto.builder()
                        .id(1)
                        .lastName("Found")
                        .firstName("Zelda")
                        .build(),
                TraineeDto.builder()
                        .id(2)
                        .lastName("Found")
                        .firstName("Léon")
                        .build(),
                TraineeDto.builder()
                        .id(3)
                        .lastName("Found")
                        .firstName("Hermione")
                        .build()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDto add(@RequestBody TraineeDto traineeDto){
        return traineeService.add(traineeDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        // TODO : delete this object if exist
    }
}
