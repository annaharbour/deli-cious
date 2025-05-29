package com.pluralsight.delicious.ui.utils;

public class PrintColored {
    public static void printColored(String msg, String colorName) {
        String colorCode = switch (colorName.toLowerCase()) {
            case "black" -> "\u001b[30m";
            case "red" -> "\u001b[31m";
            case "green" -> "\u001b[32m";
            case "yellow" -> "\u001b[33m";
            case "blue" -> "\u001b[34m";
            case "magenta" -> "\u001b[35m";
            case "cyan" -> "\u001b[36m";
            case "white" -> "\u001b[37m";
            default -> "\u001b[0m"; // Reset to default if color is invalid
        };
        System.out.println(colorCode + msg + "\u001b[0m");
    }

}
