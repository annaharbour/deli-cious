//package com.pluralsight.delicious.models;
//
//public class CheeseTopping extends PremiumTopping {
//    public enum CheeseType {
//        AMERICAN("American"),
//        PROVOLONE("Provolone"),
//        CHEDDAR("Cheddar"),
//        SWISS("Swiss");
//
//        private final String cheeseName;
//
//        CheeseType(String cheeseName) {
//            this.cheeseName = cheeseName;
//        }
//
//        public String getValue() {
//            return cheeseName;
//        }
//    }
//
//    private CheeseType cheeseType;
//    private boolean extra;
//
//    public CheeseTopping(CheeseType cheeseType, boolean extra) {
//        this.name = cheeseType.cheeseName;
//        this.basePrice = 0.75;
//        this.extra = extra;
//    }
//
//    public static CheeseTopping.CheeseType[] getAllCheeseOptions() {
//        return CheeseTopping.CheeseType.values();
//    }
//
//    @Override
//    public String toString() {
//        return this.name;
//    }
//
//    @Override
//    protected double getExtraCost(int sandwichSize) {
//        return switch (sandwichSize) {
//            case 4 -> 0.30;
//            case 8 -> 0.60;
//            case 12 -> 0.90;
//            default -> 0;
//        };
//    }
//}

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

    private final CheeseType cheeseType;
    private boolean extra;

    public CheeseTopping(CheeseType cheeseType, boolean extra) {
        this.cheeseType = cheeseType;
        this.name = cheeseType.getValue();
        this.extra = extra;
    }

    public static CheeseType[] getAllCheeseOptions() {
        return CheeseType.values();
    }

    @Override
    public String toString() {
        return name + (extra ? " Cheese (++extra)" : "Cheese");
    }

    @Override
    public double getPrice(Sandwich.SandwichSize sandwichSize) {
        double base = switch (sandwichSize) {
            case FOUR_INCH -> 0.75;
            case EIGHT_INCH -> 1.50;
            case TWELVE_INCH -> 2.25;
        };
        double extraCost = extra ? getExtraCost(sandwichSize) : 0;
        return base + extraCost;
    }

    @Override
    protected double getExtraCost(Sandwich.SandwichSize sandwichSize) {
        return switch (sandwichSize) {
            case FOUR_INCH -> 0.30;
            case EIGHT_INCH -> 0.60;
            case TWELVE_INCH -> 0.90;
        };
    }
}
