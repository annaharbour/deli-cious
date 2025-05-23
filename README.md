# deli-cious
This project is the point of sales java application for DELI-cious, a custom sandwich shop.
```mermaid
classDiagram
    %% Interfaces
    class Topping {
        <<interface>>
        +String getName()
        +double getPrice(String size)
        +boolean isExtra()
    }

    class MenuItem {
        <<interface>>
        +double getPrice()
        +String getReceiptLine()
    }

    class Screen {
        <<interface>>
        +void run()
    }

    class ReceiptWriter {
        <<interface>>
        +void write(Order order)
    }

    %% Concrete Classes
    class Sandwich {
        -Size size
        -BreadType bread
        -boolean toasted
        -List~Topping~ toppings
        -List~SauceType~ sauces
        -List~Sides~ sides
        +addTopping(Topping)
        +addSauce(SauceType)
        +double getPrice()
        +String getReceiptLine()
    }

    class SignatureSandwich{
        -String name
    }

    class SignatureSandwichLoader {
        +List~SignatureSandwich~ loadFromCSV(String path)
    }

    class PremiumTopping {
        -ToppingType type
        -boolean extra
        +double getPrice()
    }

    class RegularTopping {
        -ToppingType type
    }

    class Chips {
        -ChipType type
        +double getPrice()
        +String getReceiptLine()
    }

    class Drink {
        -String size
        -String flavor
        +double getPrice()
        +String getReceiptLine()
    }

    class Order {
        -List~MenuItem~ orderItems
        +addItem(OrderableItem)
        +double getTotalPrice()
        +String generateReceipt()
    }

    class FileReceiptWriter {
        +void write(Order order)
    }

    %% Screen Classes
    class HomeScreen
    class OrderScreen
    class AddSandwichScreen
    class SignatureSandwichesScreen
    class AddChipsScreen
    class AddDrinkScreen
    class CheckoutScreen

    %% Relationships
    Topping <|.. PremiumTopping
    Topping <|.. IncludedTopping

    OrderableItem <|.. Sandwich
    OrderableItem <|.. Chip
    OrderableItem <|.. Drink

    Sandwich <|.. SignatureSandwich
    SignatureSandwichLoader --> SignatureSandwich : creates
    Sandwich o-- Topping
    Order o-- OrderableItem

    Screen <|.. HomeScreen
    Screen <|.. OrderScreen
    Screen <|.. AddSandwichScreen
    Screen <|.. AddDrinkScreen
    Screen <|.. CheckoutScreen

    ReceiptWriter <|.. FileReceiptWriter
    FileReceiptWriter --> Order
```