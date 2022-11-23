package survey.backend.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TraineeDtoTest {

    @Test
    void testDefaulConstructor(){
        // when
        var traineeDto = new TraineeDto();
        //then
        assertAll(
                () -> assertNull(traineeDto.getId() , "id"),
                () -> assertNull(traineeDto.getFirstName() , "firstName"),
                () -> assertNull(traineeDto.getLastName() ,"lastName"),
                () -> assertNull(traineeDto.getEmail() , "email"),
                () -> assertNull(traineeDto.getPhoneNumber() , "phoneNumber"),
                () -> assertNull(traineeDto.getBirthDate() , "birthDate")
        );
    }

    // TODO : Builder
    @Test
    void testBuilder(){
        //given
        int id = 666;
        String lastName = "Eternum";
        String firstName = "Belzebuth";
        String email = "beelzeboute@gmail.com";
        String phoneNumber = "0645249807";
        LocalDate birthDate = LocalDate.of(1666, 6, 6);
        //when
        var traineeDto = TraineeDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        //then
        assertAll(
                () -> assertEquals(lastName, traineeDto.getLastName(), "lastName"),
                () -> assertEquals(firstName, traineeDto.getFirstName(), "firstName")
        );


    }

    // TODO : all args constructor
    @Test
    void testAllArgsConstructor(){
        //given
        int id = 666;
        String lastName = "Eternum";
        String firstName = "Belzebuth";
        String email = "beelzeboute@gmail.com";
        String phoneNumber = "0645249807";
        LocalDate birthDate = LocalDate.of(1666, 6, 6);
        //when
        var traineeDto = new TraineeDto(id, lastName, firstName, email, phoneNumber, birthDate);
        //then
        assertAll(
                () -> assertEquals(id, traineeDto.getId(), "id"),
                () -> assertEquals(lastName, traineeDto.getLastName(), "lastName"),
                () -> assertEquals(firstName, traineeDto.getFirstName(), "firstName"),
                () -> assertEquals(email, traineeDto.getEmail(), "email"),
                () -> assertEquals(phoneNumber, traineeDto.getPhoneNumber(), "phoneNumber"),
                () -> assertEquals(birthDate, traineeDto.getBirthDate(), "birthDate")
        );
    }

}