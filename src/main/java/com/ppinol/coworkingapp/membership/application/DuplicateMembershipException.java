package com.ppinol.coworkingapp.membership.application;

import com.ppinol.coworkingapp.membership.exceptions.ConflictException;

public class DuplicateMembershipException extends ConflictException {
    public DuplicateMembershipException(String message) {
        super(message);
    }
}
