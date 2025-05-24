package com.pluralsight.delicious.models;

public class CheeseTopping extends PremiumTopping {
    public CheeseTopping(CheeseType cheeseType, boolean extra) {
        this.name = cheeseType.name();
        this.basePrice = 0.75;
    }

    public enum CheeseType {
        AMERICAN,
        PROVOLONE,
        CHEDDAR,
        SWISS
    }

    @Override
    protected double getExtraCost(int sandwichSize) {
        return switch (sandwichSize) {
            case 4 -> 0.30;
            case 8 -> 0.60;
            case 12 -> 0.90;
            default -> 0;
        };
    }
}

