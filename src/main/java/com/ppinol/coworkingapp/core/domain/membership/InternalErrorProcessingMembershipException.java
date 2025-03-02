package com.ppinol.coworkingapp.core.domain.membership;

import com.ppinol.coworkingapp.core.InternalErrorException;

public class InternalErrorProcessingMembershipException extends InternalErrorException {
    public InternalErrorProcessingMembershipException(String message) {
        super(message);
    }
}
