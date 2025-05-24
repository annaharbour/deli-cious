package com.pluralsight.delicious.models;

public class Side {
//    can either be au jus or sauce on the side
    public enum SideType {
        AU_JUS,
        SAUCE
    }

    private final SideType type;
    private final Sauce sauce;

    //Overloading constructor
    public Side(SideType type) {
        this(type, null);
    }

    // Only set sauce type if type == SAUCE
    public Side(SideType type, Sauce sauce) {
        this.type = type;
        this.sauce = sauce;
    }

    @Override
    public String toString() {
        return (type == SideType.SAUCE && sauce != null)
                ? sauce.getSauceName()
                : "Au Jus";
    }
}

