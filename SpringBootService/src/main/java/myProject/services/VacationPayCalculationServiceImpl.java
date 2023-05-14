package myProject.services;

import myProject.model.Request;
import org.springframework.stereotype.Service;

@Service
public class VacationPayCalculationServiceImpl implements VacationPayCalculationService{
    @Override
    public Double calculateAmountVacationPay(Request request) {

        Double amountVacationPay = 0.0;

//мб, стоит воспользоваться BigDecimal, но пусть так
        if (request.getAverageSalary() > 1e-5 && request.getNumberVacationDays() > 0 &&
                request.getNumberVacationDays() < 365) {
            amountVacationPay = (request.getAverageSalary() / AVERAGE_NUMBER_DAYS) *
                        request.getNumberVacationDays();
            amountVacationPay = Math.ceil(amountVacationPay * 100) / 100;

        }
        else { //можно, конечно, создать отдельную html с подробной ошибкой, если важно оповестить и в браузере
            throw new IllegalArgumentException("Incorrectly entered values. " +
                        "The salary must be greater than 0, and the number of vacation days is acceptable.");
        }
        return amountVacationPay;
    }
}
