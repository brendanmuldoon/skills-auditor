package com.example.employeebc.employee.domain.staff;

import com.example.employeebc.employee.domain.common.ValueObject;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.example.employeebc.ApplicationConstants.*;

@Getter
@Setter
public class ExpirationDate extends ValueObject {
    
    private int month;
    private int year;

    public ExpirationDate(int month, int year) {
        checkIfSkillHasAlreadyExpired(month, year);
        setExpiryMonth(month);
        setExpiryYear(year);
    }

    private void setExpiryYear(int year) {
        if(year<LocalDate.now().getYear()) {
            throw new IllegalArgumentException(String.format(SKILL_EXPIRY_YEAR_ERROR_MSG, year));
        }

        this.year = year;
    }

    private void setExpiryMonth(int month) {
        if(month<1 || month >12){
            throw new IllegalArgumentException(String.format(SKILL_EXPIRY_MONTH_ERROR_MSG, month));
        }

        this.month = month;
    }


    private void checkIfSkillHasAlreadyExpired(int month, int year) {
        if(year<= LocalDate.now().getYear()
                && month < LocalDate.now().getMonthValue()) {
            throw new IllegalArgumentException(SKILL_EXPIRY_ERROR_MSG);
        }
    }

    public boolean equals(Object o){
        if (o == null && o.getClass() != this.getClass()){
            return false;
        }
        ExpirationDate expirationDate = (ExpirationDate) o;

        return expirationDate.month==this.month &&
                expirationDate.year==this.year;
    }

    public String toString() {
        return String.format("Expires %2d/%4d", month, year);
    }
    
}
