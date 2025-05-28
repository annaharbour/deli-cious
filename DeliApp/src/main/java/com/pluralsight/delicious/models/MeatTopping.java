package com.pluralsight.delicious.models;

public class MeatTopping extends PremiumTopping {
    public enum MeatType {
        STEAK("Steak"),
        HAM("Ham"),
        SALAMI("Salami"),
        ROAST_BEEF("Roast Beef"),
        CHICKEN("Chicken"),
        BACON("Bacon");

        private final String meatName;

        MeatType(String meatName) {
            this.meatName = meatName;
        }

        public String getValue() {
            return meatName;
        }
    }

    private boolean extra;
    private MeatType meatType;
    public MeatTopping(MeatType meatType, boolean extra) {
        this.meatType = meatType;
        this.basePrice = 1.0;
        this.extra = extra;
    }

    @Override
    public double getPrice(Sandwich.SandwichSize sandwichSize) {
        double base = switch (sandwichSize) {
            case FOUR_INCH -> 1.00;
            case EIGHT_INCH -> 2.00;
            case TWELVE_INCH -> 3.00;
        };
        double extraCost = extra ? getExtraCost(sandwichSize) : 0;
        return base + extraCost;
    }

    @Override
    protected double getExtraCost(Sandwich.SandwichSize sandwichSize) {
        return switch (sandwichSize) {
            case FOUR_INCH -> 0.50;
            case EIGHT_INCH -> 1.00;
            case TWELVE_INCH -> 1.50;
        };
    }

    public static MeatType[] getAllMeatOptions() {
        return MeatType.values();
    }

    @Override
    public String toString() {
        return meatType.getValue() + (extra ? "(++extra)" : " ");
    }
}
