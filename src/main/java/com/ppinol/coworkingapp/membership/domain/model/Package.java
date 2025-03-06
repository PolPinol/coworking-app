package com.ppinol.coworkingapp.membership.domain.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Package {
    private final PackageId packageId;
    private final Credits credits;
    private final Date startDate;
    private final Date endDate;

    Package(PackageId packageId, Credits credits, Date startDate, Date endDate) {
        this.packageId = packageId;
        this.credits = credits;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Package create(int credits, int year, int month) {
        PackageYear packageYear = new PackageYear(year);
        PackageMonth packageMonth = new PackageMonth(month);

        Date startDate = new Date();
        LocalDate endLocal = LocalDate.of(packageYear.value(), packageMonth.value(), 1)
                .withDayOfMonth(LocalDate.of(packageYear.value(), packageMonth.value(), 1).lengthOfMonth());
        Date endDate = Date.from(endLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return new Package(PackageId.generate(), new Credits(credits), startDate, endDate);
    }

    public static Package from(String id, int credits, Date startDate, Date endDate) {
        return new Package(PackageId.from(id), new Credits(credits), startDate, endDate);
    }

    public PackageId getPackageId() {
        return packageId;
    }

    public Credits getCredits() {
        return credits;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
