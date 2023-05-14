package myProject.services;

import myProject.model.Request;

public interface VacationPayCalculationService {
    Double AVERAGE_NUMBER_DAYS = 29.3;

    Double calculateAmountVacationPay(Request request);
}
