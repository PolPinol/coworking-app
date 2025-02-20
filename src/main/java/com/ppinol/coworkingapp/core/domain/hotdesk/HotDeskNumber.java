package com.ppinol.coworkingapp.core.domain.hotdesk;

public class HotDeskNumber {
    private final int number;

    public HotDeskNumber(String number) {
        try {
            this.number = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new InvalidHotDeskNumberException("Desk number must be a number");
        }

        if (this.number < 0) {
            throw new InvalidHotDeskNumberException("Desk number must be positive");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotDeskNumber that)) return false;
        return number == that.number;
    }

    @Override
    public String toString() {
        return "DeskNumber{" +
                "number=" + number +
                '}';
    }
}
