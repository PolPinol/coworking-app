package com.ppinol.coworkingapp.core.domain.office;

import com.ppinol.coworkingapp.core.domain.Number;

import java.util.OptionalInt;

public class OfficeLeasePeriod extends Number {
    public static final int DEFAULT_LEASE_PERIOD = 12;

    public OfficeLeasePeriod(OptionalInt number) {
        super(number.orElse(DEFAULT_LEASE_PERIOD));
    }

    @Override
    public void validate(int number) {
        if (number <= 0) {
            throw new InvalidOfficeLeasePeriodException("Office lease period must be positive");
        }
    }
}
