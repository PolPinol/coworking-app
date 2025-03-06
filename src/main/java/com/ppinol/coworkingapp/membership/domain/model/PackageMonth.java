package com.ppinol.coworkingapp.membership.domain.model;

public record PackageMonth(int value) {
    public PackageMonth {
        if (value < 1 || value > 12) {
            throw new InvalidMonthInputException("Month must be between 1 and 12");
        }
    }
}
