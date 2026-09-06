// ============================================================
// ORDERITEM.JAVA
// ============================================================
// TOPICS COVERED:
//
// 1. Composition
// 2. Encapsulation
// 3. Constructor
// 4. this
// 5. Polymorphism
// ============================================================

public class OrderItem {

    private Product product;
    private int quantity;

    // ========================================================
    // Constructor
    // ========================================================

    public OrderItem(Product product,
                     int quantity) {

        this.product = product;
        this.quantity = quantity;
    }

    // ========================================================
    // Getters
    // ========================================================

    public Product getProduct() {

        return product;
    }

    public int getQuantity() {

        return quantity;
    }

    // ========================================================
    // CONCEPT: Polymorphism
    // ========================================================
    // product is a Product reference.
    //
    // But the actual object may be:
    // Laptop
    // Phone
    // GroceryProduct
    //
    // Java will call the correct calculateFinalPrice()
    // at runtime.

    public double getItemTotal() {

        return product.calculateFinalPrice()
                * quantity;
    }

    // ========================================================
    // CONCEPT: Method
    // ========================================================

    public void displayItem() {

        System.out.println(
                product.getProductName()
                        + " x "
                        + quantity
                        + " = ₹"
                        + getItemTotal()
        );
    }
}