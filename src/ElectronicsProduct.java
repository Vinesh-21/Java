// ============================================================
// ELECTRONICSPRODUCT.JAVA
// ============================================================
// TOPICS COVERED:
//
// 1. Inheritance
// 2. super()
// 3. Method Overriding
// 4. Abstraction
// ============================================================

// CONCEPT: Inheritance
// ElectronicsProduct inherits from Product.

public abstract class ElectronicsProduct extends Product {

    private int warrantyYears;

    // ========================================================
    // CONCEPT: Constructor
    // CONCEPT: super()
    // ========================================================

    public ElectronicsProduct() {

        // Calls Product's default constructor.
        super();
    }

    // ========================================================
    // CONCEPT: Parameterized Constructor
    // CONCEPT: super()
    // ========================================================

    public ElectronicsProduct(int productId,
                              String productName,
                              double price,
                              int quantity,
                              int warrantyYears) {

        // CONCEPT: super
        // Calls the parent Product constructor.
        super(productId,
                productName,
                price,
                quantity);

        this.warrantyYears = warrantyYears;
    }

    // ========================================================
    // CONCEPT: Getter
    // ========================================================

    public int getWarrantyYears() {

        return warrantyYears;
    }

    // ========================================================
    // CONCEPT: Setter
    // ========================================================

    public void setWarrantyYears(int warrantyYears) {

        this.warrantyYears = warrantyYears;
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // ========================================================

    @Override
    public void displayDetails() {

        // CONCEPT: super
        // Call the parent class implementation first.

        super.displayDetails();

        System.out.println(
                "Warranty : "
                        + warrantyYears
                        + " years"
        );
    }
}