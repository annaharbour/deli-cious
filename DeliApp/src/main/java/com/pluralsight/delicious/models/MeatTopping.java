package com.pluralsight.delicious.models;

public class MeatTopping extends PremiumTopping {
    public MeatTopping(MeatType meatType) {
        this.name = meatType.name();
        this.basePrice = 1.0;
    }

    public enum MeatType {
        STEAK,
        HAM,
        SALAMI,
        ROAST_BEEF,
        CHICKEN,
        BACON
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
