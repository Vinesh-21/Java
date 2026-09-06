// ============================================================
// PHONE.JAVA
// ============================================================
// TOPICS COVERED:
//
// 1. Inheritance
// 2. super()
// 3. this
// 4. Method Overriding
// 5. Polymorphism
// ============================================================

public class Phone extends ElectronicsProduct {

    private String operatingSystem;

    // ========================================================
    // Constructor
    // ========================================================

    public Phone() {

        super();
    }

    // ========================================================
    // Parameterized Constructor
    // ========================================================

    public Phone(int productId,
                 String productName,
                 double price,
                 int quantity,
                 int warrantyYears,
                 String operatingSystem) {

        // CONCEPT: super()
        super(productId,
                productName,
                price,
                quantity,
                warrantyYears);

        // CONCEPT: this
        this.operatingSystem = operatingSystem;
    }

    // ========================================================
    // Getter
    // ========================================================

    public String getOperatingSystem() {

        return operatingSystem;
    }

    // ========================================================
    // Setter
    // ========================================================

    public void setOperatingSystem(String operatingSystem) {

        this.operatingSystem = operatingSystem;
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // ========================================================

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println(
                "OS       : "
                        + operatingSystem
        );
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // ========================================================

    @Override
    public double calculateFinalPrice() {

        return getPrice() * 1.03;
    }
}