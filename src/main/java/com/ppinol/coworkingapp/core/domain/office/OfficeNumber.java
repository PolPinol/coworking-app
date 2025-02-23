package com.ppinol.coworkingapp.core.domain.office;

import com.ppinol.coworkingapp.core.domain.Number;

public class OfficeNumber extends Number {

    public OfficeNumber(int number) {
        super(number);
    }

    @Override
    public void validate(int number) {
        if (number < 0) {
            throw new InvalidOfficeNumberException("Office number must be positive");
        }
    }
}