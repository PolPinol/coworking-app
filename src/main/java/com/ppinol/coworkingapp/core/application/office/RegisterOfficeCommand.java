package com.ppinol.coworkingapp.core.application.office;

import java.util.Optional;
import java.util.OptionalInt;

public record RegisterOfficeCommand(int number, OptionalInt leasePeriod, Optional<String> status) {}
