# deli-cious
This project is the point of sales java application for DELI-cious, a custom sandwich shop.
```mermaid
classDiagram
    %% Interfaces
    class Topping {
        <<abstract>>
        +String getName()
        +double getPrice(String size)
        +boolean isExtra()
    }

    class MenuItem {
        <<interface>>
        +double getPrice()
        +String getReceiptLine()
    }

    class ScreenState {
        <<interface>>
        +void run()
    }

    class ReceiptWriter {
        -Order currentOrder
        +void writeLines()
    }

    %% Concrete Classes
    class Sandwich {
        -SandwichSize size
        -BreadType bread
        -boolean toasted
        -List~Topping~ toppings
        -List~Sauce~ sauces
        -List~Sides~ sides
        +addTopping(Topping)
        +addSauce(SauceType)
        +removeTopping(topping)
        +removeSauce(sauce)
        +removeSide(side)
        +addTopping(Topping)
        +addSauce(SauceType)
        +SandwichSize[] getAllSizeOptions()
    }

    class SignatureSandwich{
        -String name
        -SignatureSandwichType signatureSandwichType;
        -String signatureSandwichName
    }

    class PremiumTopping {
        -double basePrice
        +double getExtraCost()
    }

    class RegularTopping {
        -FreeTopping freeTopping
        -boolean extra
        +FreeTopping[] getAllRegularToppings()
    }

    class MeatTopping {
        -MeatType meatType
        -boolean extra
        +MeatType[] getAllMeatToppings()
    }

    class CheeseTopping {
        -MeatType meatType
        -boolean extra
        +CheeseType[] getAllCheeseToppings()
    }

    class Chips {
        -Flavor flavor
        -double price
        +Chips.Flavor[] getAllChipFlavors()
    }

    class Drink {
        -Size size
        -Flavor flavor
        +Drink.Size[] getAllDrinkSizeOptions() 
        +double getPrice()
    }

    class Order {
        -Customer customer
        -LocalDateTime timeStamp
        -List~MenuItem~ orderItems
        +addItem(MenuItem)
        +double getTotalPrice()
        +clear()
    }

    class Customer {
        -String customerName
    }

    %% Screen Classes
    class HomeScreen
    class OrderScreen
    class AddSandwichScreen
    class SignatureSandwichScreen
    class AddChipsScreen
    class AddDrinkScreen
    class CheckoutScreen

    %% Relationships
    Topping <|.. PremiumTopping
    PremiumTopping <|.. CheeseTopping
    PremiumTopping <|.. MeatTopping
    Topping <|.. RegularTopping

    MenuItem <|.. Sandwich
    MenuItem <|.. Chips
    MenuItem <|.. Drink

    Sandwich <|.. SignatureSandwich
    Sandwich o-- Topping
    Order o-- MenuItem

    ScreenState <|.. HomeScreen
    ScreenState <|.. OrderScreen
    ScreenState <|.. AddSandwichScreen
    ScreenState <|.. AddDrinkScreen
    ScreenState <|.. CheckoutScreen
    ScreenState <|.. SignatureSandwichScreenScreen
    ScreenState <|.. AddChipsScreen


    ReceiptWriter --> Order
```
