package common.solutions.utils;

import java.util.Optional;

public enum Months {
    JAN("January"),
    FEB("February"),
    MAR("March"),
    APR("April"),
    MAY("May"),
    JUN("June"),
    JUL("July"),
    AUG("August"),
    SEP("September"),
    OCT("October"),
    NOV("November"),
    DEC("December");

    private String nameOfMonth;

    Months(String nameOfMonth) {
        this.nameOfMonth = nameOfMonth;
    }

    public String getNameOfMonth() {
        return nameOfMonth;
    }

    public static Optional<Months> getOptionalCountryType(String name) {
        return Optional.of(Months.valueOf(name));
    }
}
