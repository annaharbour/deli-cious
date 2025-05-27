package com.pluralsight.delicious.models;

public class CheeseTopping extends PremiumTopping {
    public enum CheeseType {
        AMERICAN("American"),
        PROVOLONE("Provolone"),
        CHEDDAR("Cheddar"),
        SWISS("Swiss");

        private final String cheeseName;

        CheeseType(String cheeseName) {
            this.cheeseName = cheeseName;
        }

        public String getValue() {
            return cheeseName;
        }
    }

    public CheeseTopping(CheeseType cheeseType, boolean extra) {
        this.name = cheeseType.cheeseName;
        this.basePrice = 0.75;
    }

    public static CheeseTopping.CheeseType[] getAllCheeseOptions() {
        return CheeseTopping.CheeseType.values();
    }

    @Override
    public String toString() {
        return this.name;
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

