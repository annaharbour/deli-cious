package com.pluralsight.delicious.models;

public class Side {
    //    can either be au jus or sauce on the side
    public enum SideType {
        AU_JUS("Au Jus"),
        SAUCE("Sauce");

        private final String sideName;

        SideType(String sideName) {
            this.sideName = sideName;
        }

        public String getValue() {
            return sideName;
        }
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

    public static SideType[] getAllSideOptions() {
        return SideType.values();
    }

    @Override
    public String toString() {
        return (type == SideType.SAUCE && sauce != null)
                ? sauce.toString()
                : "Au Jus";
    }
}

