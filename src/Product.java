public class Product {

    // ==================================================
    // ENCAPSULATION
    // ==================================================
    // Product fields are private.
    // They cannot be accessed directly from outside
    // the Product class.
    // ==================================================

    private int productId;
    private String productName;
    private double price;
    private int quantity;


    // ==================================================
    // final KEYWORD
    // ==================================================
    // MAX_PRODUCTS is a constant.
    // Once assigned, its value cannot be changed.
    // ==================================================

    public static final int MAX_PRODUCTS = 100;


    // ==================================================
    // DEFAULT CONSTRUCTOR
    // ==================================================
    // Creates a Product object without providing values.
    // ==================================================

    public Product() {
    }


    // ==================================================
    // PARAMETERIZED CONSTRUCTOR
    // ==================================================
    // Creates a Product object with values.
    //
    // this.productId → instance variable
    // productId      → constructor parameter
    //
    // "this" refers to the current object.
    // ==================================================

    public Product(
            int productId,
            String productName,
            double price,
            int quantity) {

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }


    // ==================================================
    // GETTERS
    // ==================================================
    // Used to access private fields from outside.
    // This is part of ENCAPSULATION.
    // ==================================================

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


    // ==================================================
    // SETTERS
    // ==================================================
    // Used to modify private fields.
    // ==================================================

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


    // ==================================================
    // PRODUCT METHOD
    // ==================================================
    // Displays common product information.
    // ==================================================

    public void displayDetails() {

        System.out.println("ID       : " + productId);
        System.out.println("Name     : " + productName);
        System.out.println("Price    : ₹" + price);
        System.out.println("Quantity : " + quantity);
    }


    // ==================================================
    // PRODUCT METHOD
    // ==================================================
    // Inventory Value = price × quantity
    // ==================================================

    public double getInventoryValue() {

        return price * quantity;
    }


    // ==================================================
    // METHOD
    // ==================================================
    // This method will be OVERRIDDEN by child classes.
    //
    // Product → normal product price
    // Electronics → different calculation
    // Laptop → different calculation
    // Phone → different calculation
    // Grocery → normal price
    // ==================================================

    public double calculateFinalPrice() {

        return price;
    }


    // ==================================================
    // PRODUCT METHOD
    // ==================================================
    // Determines the stock status.
    // ==================================================

    public String getStockStatus() {

        if (quantity == 0) {

            return "OUT OF STOCK";

        } else if (quantity <= 5) {

            return "LOW STOCK";

        } else {

            return "AVAILABLE";
        }
    }


    // ==================================================
    // OBJECT CLASS
    // ==================================================
    // Every Java class ultimately inherits from Object.
    //
    // toString() is being OVERRIDDEN here.
    //
    // Instead of Java's default object representation,
    // we provide meaningful Product information.
    // ==================================================

    @Override
    public String toString() {

        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }


    // ==================================================
    // OBJECT CLASS
    // ==================================================
    // equals() is being OVERRIDDEN.
    //
    // Our rule:
    // Two products are considered equal if their
    // product IDs are the same.
    // ==================================================

    @Override
    public boolean equals(Object obj) {

        // Same object reference
        if (this == obj) {

            return true;
        }

        // Check whether obj is a Product
        if (!(obj instanceof Product)) {

            return false;
        }

        // DOWNCASTING
        // Object → Product
        Product other = (Product) obj;

        return this.productId == other.productId;
    }


    // ==================================================
    // OBJECT CLASS
    // ==================================================
    // hashCode() is being OVERRIDDEN.
    //
    // It uses the same product ID used by equals().
    //
    // Rule:
    // If two objects are equal, they should have the
    // same hash code.
    // ==================================================

    @Override
    public int hashCode() {

        return Integer.hashCode(productId);
    }
}