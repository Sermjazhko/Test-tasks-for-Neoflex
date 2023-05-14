package myProject.controller;


import myProject.model.Request;
import myProject.services.VacationPayCalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class RequestController {
    private final VacationPayCalculationService vacationPayCalculationService;

    public RequestController(VacationPayCalculationService vacationPayCalculationService) {
        this.vacationPayCalculationService = vacationPayCalculationService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Double> getAllBuyers(@RequestParam("averageSalary") Double averageSalary,
                                               @RequestParam("vacationDays") Integer vacationDays) {
        Request request = new Request(vacationDays, averageSalary);
        try {
            Double answer = vacationPayCalculationService.calculateAmountVacationPay(request);

            return new ResponseEntity<>(answer, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
