package com.ppinol.coworkingapp.membership.domain.model;

public class Active {
    private boolean value;

    Active(boolean value) {
        this.value = value;
    }

    public static Active create() {
        return new Active(true);
    }

    public static Active empty() {
        return new Active(true);
    }

    public boolean value() {
        return value;
    }
}


