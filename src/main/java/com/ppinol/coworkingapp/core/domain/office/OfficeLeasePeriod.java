package com.ppinol.coworkingapp.core.domain.office;

public class OfficeLeasePeriod {
    private static final String DEFAULT_LEASE_PERIOD = "12";
    private final int number;

    public OfficeLeasePeriod(String number) {
        if (number == null || number.isEmpty()) {
            number = DEFAULT_LEASE_PERIOD;
        }

        try {
            this.number = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new InvalidOfficeLeasePeriodException("Office lease period must be a number");
        }

        if (this.number < 0) {
            throw new InvalidOfficeLeasePeriodException("Office lease period must be positive");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfficeLeasePeriod that)) return false;
        return number == that.number;
    }
}
