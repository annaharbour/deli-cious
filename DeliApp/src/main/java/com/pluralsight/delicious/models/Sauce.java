package com.pluralsight.delicious.models;

public class Sauce {
    private String sauceName;

    public enum SauceType {
        MAYO,
        MUSTARD,
        KETCHUP,
        RANCH,
        THOUSAND_ISLAND,
        VINAIGRETTE
    }

    public Sauce(SauceType sauceType) {
        this.sauceName = sauceType.name();
    }

    public String getSauceName() {
        return sauceName;
    }
}
