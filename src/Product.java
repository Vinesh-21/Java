// ============================================================
// PRODUCT.JAVA
// ============================================================
// TOPICS COVERED IN THIS FILE:
//
// 1. Class
// 2. Encapsulation
// 3. private fields
// 4. Getters and Setters
// 5. Constructors
// 6. this keyword
// 7. Abstraction
// 8. Abstract method
// 9. Method Overriding
// 10. Object class methods
//     - toString()
//     - equals()
//     - hashCode()
// ============================================================

// CONCEPT: Abstraction
// Product is abstract because we do not want to create a
// generic Product object directly.
//
// Instead, Product is the parent of:
// Laptop
// Phone
// GroceryProduct

public abstract class Product {

    // ========================================================
    // CONCEPT: Encapsulation
    // ========================================================
    // Fields are private.
    // Other classes cannot directly access them.
    // They must use getters and setters.

    private int productId;
    private String productName;
    private double price;
    private int quantity;

    // ========================================================
    // CONCEPT: Constructor
    // ========================================================

    public Product() {
    }

    // ========================================================
    // CONCEPT: Parameterized Constructor
    // CONCEPT: this keyword
    // ========================================================

    public Product(int productId,
                   String productName,
                   double price,
                   int quantity) {

        // 'this' refers to the current object.
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    // ========================================================
    // CONCEPT: Getters
    // ========================================================

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // ========================================================
    // CONCEPT: Setters
    // ========================================================

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // ========================================================
    // CONCEPT: Abstraction
    // ========================================================
    // This method has no implementation in Product.
    //
    // Every child class must provide its own implementation.

    public abstract double calculateFinalPrice();

    // ========================================================
    // Normal method
    // ========================================================

    public double getInventoryValue() {

        return price * quantity;
    }

    // ========================================================
    // Stock status
    // ========================================================

    public String getStockStatus() {

        if (quantity == 0) {

            return "OUT OF STOCK";

        } else if (quantity <= 5) {

            return "LOW STOCK";

        } else {

            return "AVAILABLE";
        }
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // ========================================================
    // Child classes can override displayDetails().

    public void displayDetails() {

        System.out.println("ID       : " + productId);
        System.out.println("Name     : " + productName);
        System.out.println("Price    : ₹" + price);
        System.out.println("Quantity : " + quantity);
        System.out.println("Stock    : " + getStockStatus());
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // Object.toString() is overridden here.
    // ========================================================

    @Override
    public String toString() {

        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // Object.equals() is overridden.
    //
    // Two products are considered equal when their IDs
    // are the same.
    // ========================================================

    @Override
    public boolean equals(Object obj) {

        // Same object
        if (this == obj) {
            return true;
        }

        // Object must be a Product
        if (!(obj instanceof Product)) {
            return false;
        }

        // CONCEPT: Downcasting
        // Object -> Product
        Product other = (Product) obj;

        return this.productId == other.productId;
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // Object.hashCode() is overridden.
    // ========================================================

    @Override
    public int hashCode() {

        return Integer.hashCode(productId);
    }
}