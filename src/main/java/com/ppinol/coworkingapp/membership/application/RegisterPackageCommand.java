package com.ppinol.coworkingapp.membership.application;

public record RegisterPackageCommand(String membershipId, int credits, int year, int month) { }
