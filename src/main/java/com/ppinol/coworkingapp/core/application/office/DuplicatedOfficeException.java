package com.ppinol.coworkingapp.core.application.office;

import com.ppinol.coworkingapp.core.exceptions.DuplicatedException;

public class DuplicatedOfficeException extends DuplicatedException {
    public DuplicatedOfficeException(String message) {
        super(message);
    }
}
