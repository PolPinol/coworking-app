package com.ppinol.coworkingapp.core.domain.hotdesk;

import com.ppinol.coworkingapp.core.domain.Number;

public class HotDeskNumber extends Number {

    public HotDeskNumber(int number) {
        super(number);
    }

    @Override
    public void validate(int number) {
        if (number <= 0) {
            throw new InvalidHotDeskNumberException("Desk number must be positive");
        }
    }
}
