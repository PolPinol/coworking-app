package com.ppinol.coworkingapp.membership.domain.model;

import com.ppinol.coworkingapp.membership.domain.Id;

public class PackageId extends Id {

    PackageId(String id) {
        super(id);
    }

    public static PackageId from(String value) {
        return new PackageId(value);
    }

    public static PackageId generate() {
        return new PackageId(generateId());
    }

    public static PackageId empty() {
        return new PackageId("");
    }
}