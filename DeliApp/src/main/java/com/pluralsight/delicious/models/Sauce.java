package com.pluralsight.delicious.models;

public class Sauce {
    private String sauceName;

    public enum SauceType {
        MAYO("Mayo"),
        MUSTARD("Mustard"),
        KETCHUP("Ketchup"),
        RANCH("Ranch"),
        THOUSAND_ISLAND("Thousand Island"),
        VINAIGRETTE("Vinaigrette");

        private final String sauceName;

        SauceType(String sauceName) {
            this.sauceName = sauceName;
        }

        public String getValue() {
            return sauceName;
        }
    }

    public Sauce(SauceType sauceType) {
        this.sauceName = sauceType.sauceName;
    }

    @Override
    public String toString() {
        return this.sauceName;
    }

    public static Sauce.SauceType[] getAllSauceOptions() {
        return Sauce.SauceType.values();
    }

}
