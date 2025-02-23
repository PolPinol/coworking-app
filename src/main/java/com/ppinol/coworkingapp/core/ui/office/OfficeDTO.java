package com.ppinol.coworkingapp.core.ui.office;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;
import java.util.OptionalInt;

public class OfficeDTO {

    public final Integer number;
    public final OptionalInt leasePeriod;
    public final Optional<String> status;

    @JsonCreator
    public OfficeDTO(@JsonProperty("number") Integer number,
                     @JsonProperty("leasePeriod") Integer leasePeriod,
                     @JsonProperty("status") String status) {
        if (number == null) {
            throw new InvalidRegisterOfficeInputException("name should not be null");
        }

        this.number = number;

        if (leasePeriod == null) {
            this.leasePeriod = OptionalInt.empty();
        } else {
            this.leasePeriod = OptionalInt.of(leasePeriod);
        }

        if (status == null) {
            this.status = Optional.empty();
        } else {
            this.status = Optional.of(status);
        }
    }

    public Integer number() {
        return number;
    }

    public OptionalInt leasePeriod() {
        return leasePeriod;
    }

    public Optional<String> status() {
        return status;
    }
}
