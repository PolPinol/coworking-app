package com.ppinol.coworkingapp.core.ui.office;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record OfficeDTO(String number, String leasePeriod, String status) {

    @JsonCreator
    public OfficeDTO(@JsonProperty("number") String number,
                     @JsonProperty("leasePeriod") String leasePeriod,
                     @JsonProperty("status") String status) {
        if (number == null) {
            throw new InvalidRegisterOfficeInputException("name should not be null");
        }

        if (number.trim().isEmpty()) {
            throw new InvalidRegisterOfficeInputException("number should not be empty");
        }

        this.number = number;
        this.leasePeriod = leasePeriod;
        this.status = status;
    }
}
