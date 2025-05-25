package com.pluralsight.delicious.models;

public class CheeseTopping extends PremiumTopping {
    public CheeseTopping(CheeseType cheeseType, boolean extra) {
        this.basePrice = 0.75;
    }

    public enum CheeseType {
        AMERICAN("American"),
        PROVOLONE("Provolone"),
        CHEDDAR("Cheddar"),
        SWISS("Swiss");

        private final String cheeseName;

        CheeseType(String cheeseName) {
            this.cheeseName = cheeseName;
        }

        public String getCheeseName() {
            return cheeseName;
        }
    }

    public static CheeseTopping.CheeseType[] getAllCheeseOptions() {
        return CheeseTopping.CheeseType.values();
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

