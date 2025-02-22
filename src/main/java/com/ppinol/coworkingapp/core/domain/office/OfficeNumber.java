package com.ppinol.coworkingapp.core.domain.office;

public class OfficeNumber {
    private final int number;

    public OfficeNumber(String number) {
        try {
            this.number = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new InvalidOfficeNumberException("Office number must be an integer");
        }

        if (this.number < 0) {
            throw new InvalidOfficeNumberException("Office number must be positive");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfficeNumber that)) return false;
        return number == that.number;
    }
}