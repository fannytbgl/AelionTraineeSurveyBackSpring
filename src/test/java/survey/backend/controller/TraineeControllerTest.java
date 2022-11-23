package survey.backend.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(TraineeController.class)
class TraineeControllerTest {

    final static String BASE_URL = "/api/trainee";


    //component to call TraineeController with HTTP requests
    @Autowired
    MockMvc mockMvc;


    @MockBean
    TraineeService traineeService;


    @Test
    void testGetByIdOk() throws Exception {

        //prepare
        int id = 123;
        var traineeDto = TraineeDto.builder()
                .id(id)
                .lastName("Doe")
                .firstName("John")
                .build();

        BDDMockito.given(traineeService.findById(id))
                .willReturn(Optional.of(traineeDto));

        //when
        mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL+"/"+ id)
                .accept(MediaType.APPLICATION_JSON)
                )

        //then / verify HTTP communication
                .andDo(MockMvcResultHandlers.print()) //log request/Reponse
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));

        //then / verify if mock service has been called
        BDDMockito.then(traineeService)
                .should()
                .findById(id);

    }

    @Test
    void testGetByIdKoNotFound() throws Exception {

        //given
        int id = 0;

        BDDMockito.given(traineeService.findById(id))
                .willReturn(Optional.empty());

        //when
        mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL+"/"+ id)
                        .accept(MediaType.APPLICATION_JSON)
        )
                //then / verify HTTP communication
                .andDo(MockMvcResultHandlers.print()) //log request/Reponse
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Trainee with id" + id + " not found"));

                //then / verify if mock service has been called
                BDDMockito.then(traineeService)
                .should()
                .findById(id);
    }
}