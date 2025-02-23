package com.ppinol.coworkingapp.core.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Date {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected final LocalDate date;

    protected Date(String dateString) {
        try {
            this.date = LocalDate.parse(dateString, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format: " + dateString, e);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isSameDay(Date other) {
        return this.date.equals(other.getDate());
    }

    public boolean isToday() {
        return this.date.equals(LocalDate.now());
    }

    @Override
    public String toString() {
        return date.format(FORMATTER);
    }
}
