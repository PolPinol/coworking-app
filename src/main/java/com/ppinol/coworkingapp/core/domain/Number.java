package com.ppinol.coworkingapp.core.domain;

public abstract class Number {
    protected final int number;

    protected Number(int number) {
        validate(number);
        this.number = number;
    }

    protected abstract void validate(int number);

    public int value() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number that = (Number) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "number=" + number +
                '}';
    }
}
