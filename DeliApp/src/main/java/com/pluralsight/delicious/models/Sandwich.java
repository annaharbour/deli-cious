package com.pluralsight.delicious.models;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements MenuItem {
    public enum Size {
        FOUR_INCH(4),
        EIGHT_INCH(8),
        TWELVE_INCH(12);

        private final int value;

        Size(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum BreadType {
        WHITE,
        WHOLE_GRAIN,
        MULTIGRAIN,
        RYE
    }

    private Size size;
    private BreadType breadType;
    private List<Topping> toppings = new ArrayList<>();
    private List<Sauce> sauces;
    private List<Side> sides;
    boolean toasted;

    public Sandwich() {
    }

    public Sandwich(Size size, BreadType breadType, List<Topping> toppings, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toppings = (toppings != null) ? toppings : new ArrayList<>();
        this.toasted = toasted;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }

    public void addSauce(Sauce sauce) {
        this.sauces.add(sauce);
    }

    public void removeSauce(Sauce sauce) {
        this.toppings.remove(sauce);
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getReceiptLine() {
        return "Sandwich{" +
                "size=" + size +
                ", breadType=" + breadType +
                ", toppings=" + toppings +
                ", toasted=" + toasted +
                '}';
    }

    @Override
    public String getOrderLine() {
        return "Sandwich{" +
                "size=" + size +
                ", breadType=" + breadType +
                ", toppings=" + toppings +
                ", toasted=" + toasted +
                '}';
    }

}
