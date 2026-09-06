// ============================================================
// GROCERYPRODUCT.JAVA
// ============================================================
// TOPICS COVERED:
//
// 1. Inheritance
// 2. super()
// 3. this
// 4. Method Overriding
// 5. Polymorphism
// ============================================================

public class GroceryProduct extends Product {

    private String expiryDate;

    // ========================================================
    // Constructor
    // ========================================================

    public GroceryProduct() {

        super();
    }

    // ========================================================
    // Parameterized Constructor
    // ========================================================

    public GroceryProduct(int productId,
                          String productName,
                          double price,
                          int quantity,
                          String expiryDate) {

        // CONCEPT: super()
        super(productId,
                productName,
                price,
                quantity);

        // CONCEPT: this
        this.expiryDate = expiryDate;
    }

    // ========================================================
    // Getter
    // ========================================================

    public String getExpiryDate() {

        return expiryDate;
    }

    // ========================================================
    // Setter
    // ========================================================

    public void setExpiryDate(String expiryDate) {

        this.expiryDate = expiryDate;
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // ========================================================

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println(
                "Expiry   : "
                        + expiryDate
        );
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // ========================================================

    @Override
    public double calculateFinalPrice() {

        return getPrice();
    }
}