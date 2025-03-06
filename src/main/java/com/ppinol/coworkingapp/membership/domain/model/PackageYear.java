package com.ppinol.coworkingapp.membership.domain.model;

public record PackageYear(int value) {
    public PackageYear {
        if (value <= 0) {
            throw new InvalidYearInputException("Year must be greater than 0");
        }
        if (value < 1000 || value > 9999) {
            throw new InvalidYearInputException("Year must be a four-digit number");
        }
    }
}
