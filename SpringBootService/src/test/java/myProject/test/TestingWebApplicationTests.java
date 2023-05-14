package myProject.test;

import myProject.controller.RequestController;
import myProject.services.VacationPayCalculationService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebApplicationTests {
    @Autowired
    private MockMvc mvc;
    private VacationPayCalculationService vacationPayCalculationService;

    @Test
    public void gettingResult_whenCorrectValueIsEntered_one() throws Exception {
        RequestController requestController = new RequestController(vacationPayCalculationService);
        mvc.perform(MockMvcRequestBuilders
                        .get("/calculate")
                        .param("averageSalary", String.valueOf(29300.0))
                        .param("vacationDays", String.valueOf(30))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(30000.0))
                .andReturn();
    }

    @Test
    public void gettingResult_whenCorrectValueIsEntered_two() throws Exception {
        RequestController requestController = new RequestController(vacationPayCalculationService);
        mvc.perform(MockMvcRequestBuilders
                        .get("/calculate")
                        .param("averageSalary", String.valueOf(14650.0))
                        .param("vacationDays", String.valueOf(10))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(5000.0))
                .andReturn();
    }

    @Test
    public void gettingResult_whenCorrectValueIsEntered_three() throws Exception {
        RequestController requestController = new RequestController(vacationPayCalculationService);
        mvc.perform(MockMvcRequestBuilders
                        .get("/calculate")
                        .param("averageSalary", String.valueOf(57176.02))
                        .param("vacationDays", String.valueOf(8))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(15611.20))
                .andReturn();
    }



    @Test
    public void gettingAnError_whenNegativeSalaryIsEntered() throws Exception {
        RequestController requestController = new RequestController(vacationPayCalculationService);
        mvc.perform(MockMvcRequestBuilders
                        .get("/calculate")
                        .param("averageSalary", String.valueOf(-14650.0))
                        .param("vacationDays", String.valueOf(10))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    @Test
    public void gettingAnError_whenEnteringNegativeDays() throws Exception {
        RequestController requestController = new RequestController(vacationPayCalculationService);
        mvc.perform(MockMvcRequestBuilders
                        .get("/calculate")
                        .param("averageSalary", String.valueOf(14650.0))
                        .param("vacationDays", String.valueOf(-10))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    @Test
    public void gettingAnError_whenEnteringTooManyDays() throws Exception {
        RequestController requestController = new RequestController(vacationPayCalculationService);
        mvc.perform(MockMvcRequestBuilders
                        .get("/calculate")
                        .param("averageSalary", String.valueOf(14650.0))
                        .param("vacationDays", String.valueOf(366))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();
    }
}