package com.ppinol.coworkingapp.core.domain.membership;

import com.ppinol.coworkingapp.core.InternalErrorException;

public class InvalidContractWithPaymentRequestException extends InternalErrorException {
    public InvalidContractWithPaymentRequestException(String message) {
        super(message);
    }
}
