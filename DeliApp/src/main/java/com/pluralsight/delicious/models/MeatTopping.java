package com.pluralsight.delicious.models;

public class MeatTopping extends PremiumTopping {
    public MeatTopping(MeatType meatType, boolean extra) {
        this.name = meatType.name();
        this.basePrice = 1.0;
    }

    public enum MeatType {
        STEAK("Steak"),
        HAM("Ham"),
        SALAMI("Salami"),
        ROAST_BEEF("Roast Beef"),
        CHICKEN("Chicken"),
        BACON("Bacon");

        private final String meat;

        MeatType(String meat) {
            this.meat = meat;
        }

        public String getMeat() {
            return meat;
        }
    }

    @Override
    protected double getExtraCost(int sandwichSize) {
        return switch (sandwichSize) {
            case 4 -> 0.50;
            case 8 -> 1.00;
            case 12 -> 1.50;
            default -> 0;
        };
    }
}
