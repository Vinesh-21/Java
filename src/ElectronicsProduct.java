// ======================================================
// INHERITANCE
// ======================================================
// ElectronicsProduct extends Product.
//
// Therefore:
//
// ElectronicsProduct IS-A Product
//
// ElectronicsProduct inherits Product's:
// - productId
// - productName
// - price
// - quantity
// - getters
// - setters
// - methods
// ======================================================

public class ElectronicsProduct extends Product {

    private int warrantyYears;


    // ==================================================
    // DEFAULT CONSTRUCTOR
    // ==================================================

    public ElectronicsProduct() {

        // super() calls the parent Product constructor.
        super();
    }


    // ==================================================
    // PARAMETERIZED CONSTRUCTOR
    // ==================================================

    public ElectronicsProduct(
            int productId,
            String productName,
            double price,
            int quantity,
            int warrantyYears) {


        // ==================================================
        // super(...)
        // ==================================================
        // Calls the parent Product constructor.
        //
        // Parent receives:
        // productId
        // productName
        // price
        // quantity
        // ==================================================

        super(
                productId,
                productName,
                price,
                quantity
        );


        // this refers to the current ElectronicsProduct
        // object.

        this.warrantyYears = warrantyYears;
    }


    // ==================================================
    // GETTER
    // ==================================================

    public int getWarrantyYears() {

        return warrantyYears;
    }


    // ==================================================
    // SETTER
    // ==================================================

    public void setWarrantyYears(int warrantyYears) {

        this.warrantyYears = warrantyYears;
    }


    // ==================================================
    // METHOD OVERRIDING
    // ==================================================
    // Product already has displayDetails().
    //
    // ElectronicsProduct provides its own version.
    // ==================================================

    @Override
    public void displayDetails() {

        // super.displayDetails()
        // calls the parent Product implementation.

        super.displayDetails();

        System.out.println(
                "Warranty : "
                        + warrantyYears
                        + " years"
        );
    }


    // ==================================================
    // METHOD OVERRIDING
    // ==================================================
    // ElectronicsProduct changes the behavior of
    // calculateFinalPrice().
    // ==================================================

    @Override
    public double calculateFinalPrice() {

        // Example:
        // Electronics have a 5% additional charge.

        return getPrice() * 1.05;
    }
}