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

    class OrderableItem {
        <<interface>>
        +double getPrice()
        +String getReceiptLine()
    }

    class Menu {
        <<interface>>
        +void run()
    }

    class ReceiptWriter {
        <<interface>>
        +void write(Order order)
    }

    %% Concrete Classes
    class Sandwich {
        -SelectedSize size
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

    class IncludedTopping {
        -ToppingType type
    }

    class Chip {
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
        -List~OrderableItem~ items
        +addItem(OrderableItem)
        +double getTotalPrice()
        +String generateReceipt()
    }

    class FileReceiptWriter {
        +void write(Order order)
    }

    %% Menu Classes
    class HomeMenu
    class OrderMenu
    class AddSandwichMenu
    class AddDrinkMenu
    class CheckoutMenu

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

    Menu <|.. HomeMenu
    Menu <|.. OrderMenu
    Menu <|.. AddSandwichMenu
    Menu <|.. AddDrinkMenu
    Menu <|.. CheckoutMenu

    ReceiptWriter <|.. FileReceiptWriter
    FileReceiptWriter --> Order
```