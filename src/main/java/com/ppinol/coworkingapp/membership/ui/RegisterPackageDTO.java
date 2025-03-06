package com.ppinol.coworkingapp.membership.ui;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterPackageDTO(String membershipId, Integer credits, Integer year, Integer month) {

    @JsonCreator
    public RegisterPackageDTO(@JsonProperty("membershipId") String membershipId,
                              @JsonProperty("credits") Integer credits,
                              @JsonProperty("year") Integer year,
                              @JsonProperty("month") Integer month) {
        if (membershipId == null || credits == null || year == null || month == null) {
            throw new InvalidCreateMembershipInputException("All fields should not be null");
        }

        if (membershipId.trim().isEmpty()) {
            throw new InvalidCreateMembershipInputException("membershipId should not be empty");
        }

        this.membershipId = membershipId;
        this.credits = credits;
        this.year = year;
        this.month = month;
    }
}