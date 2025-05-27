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
    protected double getExtraCost(int sandwichSize) {
        return switch (sandwichSize) {
            case 4 -> 0.50;
            case 8 -> 1.00;
            case 12 -> 1.50;
            default -> 0;
        };
    }

    public static MeatType[] getAllMeatOptions() {
        return MeatType.values();
    }

    @Override
    public String toString() {
        return meatType.getValue() + (extra ? "/t ++ extra " : "");
    }
}
