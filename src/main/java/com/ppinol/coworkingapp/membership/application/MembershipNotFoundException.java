package com.ppinol.coworkingapp.membership.application;

import com.ppinol.coworkingapp.membership.exceptions.BadRequestException;

public class MembershipNotFoundException extends BadRequestException {
    public MembershipNotFoundException(String message) {
        super(message);
    }
}
