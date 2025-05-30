# 🥪 DELI-cious

This project is the point of sales java application for DELI-cious, a custom terminal sandwich shop.

## 🚀 Features

- 🌟 **Signature & Custom Sandwiches** — Craft your own or choose from ready-made combos.
- 🧠 **Object-Oriented Design** — Polymorphism, inheritance, and composition done right.
- 🔁 **Reusable UI Components** — Helper classes streamline input flow and state management.
- 🧪 **Testable Architecture** — Core logic decoupled from UI for unit testing.

## 🧜‍♀️ UML Architecture Overview
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

    class Sauce {
        -SauceType type
    }
    
    class Side {
        -SideType type
        -Sauce sauce
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
    Sandwich o-- Sauce
    Sandwich o-- Side

    Order o-- MenuItem

    ScreenState <|.. HomeScreen
    ScreenState <|.. OrderScreen
    ScreenState <|.. AddSandwichScreen
    ScreenState <|.. AddDrinkScreen
    ScreenState <|.. CheckoutScreen
    ScreenState <|.. SignatureSandwichScreen
    ScreenState <|.. AddChipsScreen


    ReceiptWriter --> Order
```
------------------------------
## 💡 Code Highlights
------------------------------
### 🖥️ ScreenState Interface: Navigating the UI Flow

The ScreenState interface is a central part of the application's UI navigation architecture. It provides a consistent contract for all interactive screens in the DELI-cious point-of-sale system. Every screen that the user interacts with (like selecting a sandwich, checking out, or adding a drink) implements this interface. The run() method encapsulates the logic for rendering that screen and handling user input, which promotes a clean separation of concerns and ensures consistent control flow across the application.

```
public interface ScreenState {
    void display();
    ScreenState handleInput(Scanner scanner, Order currentOrder);
}
```

* Benefits *

- Modular Design: Each screen is self-contained and easy to maintain or extend.

- Scalable Navigation: Adding a new screen is as simple as implementing the interface.

- Testability: Individual screens can be tested independently by invoking their run() method with mocked input.

- Decoupling: UI flow management is separated from the business logic, making the codebase more manageable.

```
public class OrderScreen implements ScreenState {
    @Override
    public void display() {
        ClearScreen.clearScreen();
        PrintColored.printColored("😋========= Place an Order ========😋", "magenta");
    }

    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        String[] options = {"1) Add Sandwich", "2) Add Drink", "3) Add Chips", "4) Checkout", "0) Cancel " +
                "Order"};
        for (String option : options) {
            System.out.println("\t" + option);
        }
        int input = scanner.nextInt();
        return switch (input) {
            case 0 -> {
                currentOrder.clear();
                yield new OrderScreen();
            }
            case 1 -> new AddSandwichScreen();
            case 2 -> new AddDrinkScreen();
            case 3 -> new AddChipsScreen();
            case 4 -> new CheckoutScreen();
            default -> {
                PrintColored.printColored("Invalid input. Try again", "red");
                yield this;
            }
        };
    }
}
```

* Screens That Implement ScreenState *

- HomeScreen

- OrderScreen

- AddSandwichScreen

- SignatureSandwichScreen

- AddChipsScreen

- AddDrinkScreen

- CheckoutScreen

➡️ Highlight: The ScreenState interface enables a plug-and-play architecture for user interactions, which is both intuitive for developers and robust under real-world use.

----------------

### 🥪 SignatureSandwich Inheritance from Sandwich

In the DELI-cious point-of-sale system, not all sandwiches are built from scratch. Some are pre-designed and popular choices—these are our Signature Sandwiches. To represent these, we introduce the SignatureSandwich class, which extends the more general Sandwich superclass.

The SignatureSandwich inherits all the core capabilities of a Sandwich, including:
- size, bread, toasted status
- Lists of Topping, Sauce, and Side
- Methods like addTopping(), addSauce(), removeTopping(), etc.

This inheritance ensures that a signature sandwich can participate in all system flows and behave like any other Sandwich object—with the added benefit of having predefined settings.
- signatureSandwichType: Enum identifying the preset sandwich category (e.g., CLUB, BLT, VEGGIE).
- signatureSandwichName: Human-readable name for the sandwich.
- Optional override behavior or constraints—for example, preventing users from changing ingredients unless explicitly allowed.

🧠 Why This Matters
Using inheritance allows the system to:
- Reuse behavior: No need to re-implement topping/sauce/side logic.
- Differentiate meaningfully: Signature sandwiches are conceptually different from custom builds, and this distinction is cleanly represented in the class hierarchy.
- Extend easily: New signature sandwiches can be introduced with minimal changes to core logic.

➡️ Highlight: SignatureSandwich elegantly balances customization and standardization, offering users speed and familiarity while keeping the backend design robust and DRY.

----------------------------------

### 🥑 Toppings 

```
public abstract class Topping {
    protected String name;
    public String getName() { return name; }
    public abstract double getPrice(Sandwich.SandwichSize sizeInInches);
}

public abstract class PremiumTopping extends Topping {
    protected double basePrice;

    public abstract double getPrice(Sandwich.SandwichSize sandwichSize);

    protected abstract double getExtraCost(Sandwich.SandwichSize sandwichSize);
}


```
➡️ Highlight: Decouples business logic from concrete implementations like MeatTopping/CheeseTopping in the case of the abstract class PremiumTopping, or RegularTopping in the case of Topping.

-----------------------------

### 🧀🍅🍞 SandwichBuilderHelper

```
    public static void chooseMeat(Scanner scanner, Sandwich sandwich) {
        ClearScreen.clearScreen();
        MeatTopping.MeatType[] meatOptions = MeatTopping.getAllMeatOptions();
        PrintColored.printColored("Select meat:", "yellow");
        for (int i = 0; i <= meatOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, meatOptions[i].getValue());
        }
        int choice = handleInput(scanner, meatOptions.length);
        System.out.printf("Extra %s?\t1) Yes\t2) No\n", meatOptions[choice - 1].getValue());
        int extra;
        do {
            extra = scanner.nextInt();
            if (extra == 1) {
                sandwich.addTopping(new MeatTopping(meatOptions[choice - 1], true));
            } else if (extra == 2) {
                sandwich.addTopping(new MeatTopping(meatOptions[choice - 1], false));
            } else {
                PrintColored.printColored("Invalid choice, please try again.", "red");
            }
        } while (extra != 1 && extra != 2);
    }
```

➡️ Highlight: Clean iteration, dynamic choice handling, and extensibility (e.g., supports any future topping types).

---------------------

### 🥬🫑🥒 Enums
Enums are a key part of the design, ensuring that only valid, expected options are allowed for ingredients, sides, and configurations. This avoids the need for fragile string comparisons and provides type-safe access to UI display values and logic branching.

```
public enum FreeTopping {
        LETTUCE("Lettuce"),
        PEPPERS("Peppers"),
        ONIONS("Onions"),
        TOMATOES("Tomatoes"),
        JALAPENOS("Jalapeños"),
        CUCUMBERS("Cucumbers"),
        PICKLES("Pickles"),
        GUACAMOLE("Guacamole"),
        MUSHROOMS("Mushrooms");

        private final String toppingName;

        FreeTopping(String toppingName) {
            this.toppingName = toppingName;
        }

        public String getValue() {
            return toppingName;
        }
    }
```

* 🧀 Other Enums *

CheeseType — All available cheese options (e.g., Cheddar, Swiss)

- MeatType — All available cheese options (e.g., Cheddar, Swiss)

- SauceType — Condiments and sauces (e.g., Mayo, Mustard, Ketchup)

- SideType - Au Jus or Sauce

- SignatureSandwichType — Maps names to default sandwich builds

- BreadType, SandwichSize — Core configuration choices

- Chips.Flavor, Drink.Size, Drink.Flavor — Side item variations

➡️ Highlight: The use of enums enhances user input validation, simplifies menu rendering, and keeps the codebase maintainable.

----------------------------

### 🧪 Testing Support

The business logic (e.g., ingredient management, sandwich configuration) is fully testable with JUnit. 

![Screenshot 2025-05-30 01 14 11](https://github.com/user-attachments/assets/ff17503b-1991-4482-8bd2-471158a917c4)

A large portion of the app has been protected from crashing with invalid inputs (i.e. a letter instead of an int)

-------------------------

## User Story Screenshots

### Creating an Order

![Screenshot 2025-05-30 01 15 30](https://github.com/user-attachments/assets/80d33ebd-1a01-4f22-9de1-9a4e2ac23993)

![Screenshot 2025-05-30 01 16 03](https://github.com/user-attachments/assets/4f499ff4-50c0-4ad0-8391-0b325aa767d4)

### Ordering Sandwiches

![Screenshot 2025-05-30 01 16 38](https://github.com/user-attachments/assets/f229efce-990e-433c-8582-e99bc62ed9f6)

![Screenshot 2025-05-30 01 17 09](https://github.com/user-attachments/assets/5e3854c9-933a-4176-80d9-0dac733b130f)

![Screenshot 2025-05-30 01 17 44](https://github.com/user-attachments/assets/50e3b9d6-61de-4f4d-8230-8697c4252c69)

![Screenshot 2025-05-30 01 18 38](https://github.com/user-attachments/assets/2cf40589-beb5-40fb-9361-570061c3d0d4)

![Screenshot 2025-05-30 01 19 50](https://github.com/user-attachments/assets/d2faa22e-fc19-4ea2-bc90-45b0d2321d73)

* Signature Sandwich *

![Screenshot 2025-05-30 01 23 05](https://github.com/user-attachments/assets/94d01b61-4e5f-4919-938d-ce2a1c9e2ec1)

![Screenshot 2025-05-30 01 23 36](https://github.com/user-attachments/assets/7159c074-5f1b-4f46-bbbe-3a1438396408)

![Screenshot 2025-05-30 01 24 04](https://github.com/user-attachments/assets/deb60bc6-5df0-445a-80c2-3aefeeae985f)

### Ordering a Drink and Chips

![Screenshot 2025-05-30 01 20 24](https://github.com/user-attachments/assets/1b31237e-463f-418e-9391-1e9cc63f83e5)

![Screenshot 2025-05-30 01 20 54](https://github.com/user-attachments/assets/d81079e5-3fc8-40be-a8d7-8bbf1dbe422e)

![Screenshot 2025-05-30 01 21 24](https://github.com/user-attachments/assets/7db45aa1-f4b3-4fa2-94ca-2f0fd9a57f52)

![Screenshot 2025-05-30 01 21 57](https://github.com/user-attachments/assets/37ca10e3-4ae1-402b-91ef-6a2c35ed44a9)

![Screenshot 2025-05-30 01 22 32](https://github.com/user-attachments/assets/3a2d5c68-2595-4504-94d7-cabfa5e136cc)

### Checking Out and Printing Receipt to TXT File

![Screenshot 2025-05-30 01 25 16](https://github.com/user-attachments/assets/3eaf5018-07ac-4edd-8a98-ebaa357a2df5)

![Screenshot 2025-05-30 01 26 14](https://github.com/user-attachments/assets/a1b3abb5-5ff3-4196-8083-39cb7ae0624e)





