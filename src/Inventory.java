// ============================================================
// INVENTORY.JAVA
// ============================================================
// TOPICS COVERED:
//
// OOP:
// 1. Interface
// 2. Polymorphism
// 3. Upcasting
// 4. Downcasting
// 5. instanceof
//
// COLLECTIONS:
// 6. List
// 7. Set
// 8. Map
//
// STREAMS:
// 9. map()
// 10. filter()
// 11. reduce()
// 12. sorted()
//
// OTHER:
// 13. Optional
// 14. Method References
// 15. Lambda Expressions
// ============================================================

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

// ============================================================
// CONCEPT: Interface Implementation
// ============================================================

public class Inventory
        implements Searchable<Product> {

    // ========================================================
    // CONCEPT: Collection - List
    // ========================================================
    // List allows us to store multiple Product objects.

    private List<Product> products;

    // ========================================================
    // CONCEPT: Collection - Map
    // ========================================================
    // Map stores:
    //
    // Product ID -> Product object

    private Map<Integer, Product> productMap;

    // ========================================================
    // Constructor
    // ========================================================

    public Inventory() {

        products = new ArrayList<>();

        productMap = new HashMap<>();
    }

    // ========================================================
    // Add product
    // ========================================================

    public void addProduct(Product product) {

        products.add(product);

        productMap.put(
                product.getProductId(),
                product
        );
    }

    // ========================================================
    // CONCEPT: Method Overloading
    // ========================================================
    // Same method name.
    // Different parameter list.

    public void addProduct(int id,
                           String name,
                           double price,
                           int quantity,
                           String expiryDate) {

        Product product =
                new GroceryProduct(
                        id,
                        name,
                        price,
                        quantity,
                        expiryDate
                );

        addProduct(product);
    }

    // ========================================================
    // View products
    // ========================================================

    public void viewProducts() {

        System.out.println(
                "\n===== ALL PRODUCTS ====="
        );

        // ====================================================
        // CONCEPT: Lambda Expression
        // ====================================================
        // product -> ...
        //
        // This is a lambda expression.

        products.forEach(product -> {

            // =================================================
            // CONCEPT: Polymorphism / Dynamic Method Dispatch
            // =================================================
            //
            // product is Product reference.
            //
            // Actual object may be:
            // Laptop
            // Phone
            // GroceryProduct
            //
            // Java calls the correct overridden method.

            product.displayDetails();

            System.out.println(
                    "Final Price: ₹"
                            + product.calculateFinalPrice()
            );

            System.out.println(
                    "------------------------"
            );
        });
    }

    // ========================================================
    // CONCEPT: Interface method
    // ========================================================

    @Override
    public Product search(int id) {

        return productMap.get(id);
    }

    // ========================================================
    // CONCEPT: Optional
    // ========================================================
    // Optional helps us represent:
    //
    // "A product may or may not exist."

    public Optional<Product> findProduct(int id) {

        return Optional.ofNullable(
                productMap.get(id)
        );
    }

    // ========================================================
    // CONCEPT: Stream filter()
    // ========================================================
    //
    // Select only products whose quantity <= 5.

    public void showLowStockProducts() {

        System.out.println(
                "\n===== LOW STOCK PRODUCTS ====="
        );

        products.stream()

                .filter(product ->
                        product.getQuantity() <= 5)

                // =================================================
                // CONCEPT: Method Reference
                // =================================================
                // Same as:
                //
                // product -> product.displayDetails()

                .forEach(
                        Product::displayDetails
                );
    }

    // ========================================================
    // CONCEPT: Stream map()
    // ========================================================
    //
    // Convert Product objects into product names.

    public void showProductNames() {

        System.out.println(
                "\n===== PRODUCT NAMES ====="
        );

        products.stream()

                .map(Product::getProductName)

                .forEach(System.out::println);
    }

    // ========================================================
    // CONCEPT: Stream reduce()
    // ========================================================
    //
    // Calculate total inventory value.

    public double calculateInventoryValue() {

        return products.stream()

                .map(Product::getInventoryValue)

                .reduce(
                        0.0,
                        Double::sum
                );
    }

    // ========================================================
    // CONCEPT: Stream sorted()
    // ========================================================

    public void sortProductsByPrice() {

        System.out.println(
                "\n===== PRODUCTS SORTED BY PRICE ====="
        );

        products.stream()

                .sorted(
                        (p1, p2) ->
                                Double.compare(
                                        p1.getPrice(),
                                        p2.getPrice()
                                )
                )

                .forEach(
                        Product::displayDetails
                );
    }

    // ========================================================
    // CONCEPT: Collection - Set
    // ========================================================
    //
    // Set does not allow duplicate values.

    public void showUniqueProductNames() {

        System.out.println(
                "\n===== UNIQUE PRODUCT NAMES ====="
        );

        Set<String> names =
                new HashSet<>();

        products.forEach(
                product ->
                        names.add(
                                product.getProductName()
                        )
        );

        names.forEach(
                System.out::println
        );
    }

    // ========================================================
    // CONCEPT: Map
    // ========================================================

    public void showProductMap() {

        System.out.println(
                "\n===== PRODUCT MAP ====="
        );

        productMap.forEach(
                (id, product) ->
                        System.out.println(
                                id
                                        + " -> "
                                        + product.getProductName()
                        )
        );
    }

    // ========================================================
    // CONCEPT: Polymorphism
    // ========================================================

    public void demonstratePolymorphism() {

        System.out.println(
                "\n===== POLYMORPHISM ====="
        );

        for (Product product : products) {

            System.out.println(
                    product.getProductName()
                            + " -> ₹"
                            + product.calculateFinalPrice()
            );
        }
    }

    // ========================================================
    // CONCEPT: Downcasting
    // CONCEPT: instanceof
    // ========================================================

    public void demonstrateDowncasting() {

        if (products.isEmpty()) {

            System.out.println(
                    "No products available."
            );

            return;
        }

        // ====================================================
        // CONCEPT: Upcasting
        // ====================================================
        // Actual object may be Laptop.
        //
        // But reference is Product.

        Product product = products.get(0);

        // ====================================================
        // CONCEPT: instanceof
        // ====================================================
        // Check the actual object before downcasting.

        if (product instanceof Laptop) {

            // =================================================
            // CONCEPT: Downcasting
            // =================================================
            // Product reference -> Laptop reference

            Laptop laptop =
                    (Laptop) product;

            System.out.println(
                    "Laptop RAM: "
                            + laptop.getRam()
                            + " GB"
            );

            System.out.println(
                    "Laptop Storage: "
                            + laptop.getStorage()
                            + " GB"
            );

        } else if (product instanceof Phone) {

            // Downcasting Product -> Phone

            Phone phone =
                    (Phone) product;

            System.out.println(
                    "Phone OS: "
                            + phone.getOperatingSystem()
            );

        } else {

            System.out.println(
                    "First product is not Laptop or Phone."
            );
        }
    }

    // ========================================================
    // Getter
    // ========================================================

    public List<Product> getProducts() {

        return products;
    }
}