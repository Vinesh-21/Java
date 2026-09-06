// ======================================================
// INHERITANCE
// ======================================================
//
// Product
//    ↓
// GroceryProduct
//
// GroceryProduct IS-A Product
// ======================================================

public class GroceryProduct extends Product {

    private String expiryDate;


    // ==================================================
    // DEFAULT CONSTRUCTOR
    // ==================================================

    public GroceryProduct() {

        super();
    }


    // ==================================================
    // PARAMETERIZED CONSTRUCTOR
    // ==================================================

    public GroceryProduct(
            int productId,
            String productName,
            double price,
            int quantity,
            String expiryDate) {


        // ==================================================
        // super()
        // ==================================================
        // Calls Product constructor.
        // ==================================================

        super(
                productId,
                productName,
                price,
                quantity
        );


        this.expiryDate = expiryDate;
    }


    // ==================================================
    // GETTER
    // ==================================================

    public String getExpiryDate() {

        return expiryDate;
    }


    // ==================================================
    // SETTER
    // ==================================================

    public void setExpiryDate(String expiryDate) {

        this.expiryDate = expiryDate;
    }


    // ==================================================
    // METHOD OVERRIDING
    // ==================================================

    @Override
    public void displayDetails() {

        // Calls Product.displayDetails()

        super.displayDetails();

        System.out.println(
                "Expiry   : "
                        + expiryDate
        );
    }


    // ==================================================
    // METHOD OVERRIDING
    // ==================================================

    @Override
    public double calculateFinalPrice() {

        // Grocery has no additional charge.

        return getPrice();
    }
}