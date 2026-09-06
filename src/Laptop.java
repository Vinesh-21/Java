// ============================================================
// LAPTOP.JAVA
// ============================================================
// TOPICS COVERED:
//
// 1. Inheritance
// 2. Multilevel Inheritance
// 3. super()
// 4. this
// 5. Method Overriding
// 6. Polymorphism
// ============================================================

// CONCEPT: Multilevel Inheritance
//
// Product
//    ↓
// ElectronicsProduct
//    ↓
// Laptop

public class Laptop extends ElectronicsProduct {

    private int ram;
    private int storage;

    // ========================================================
    // CONCEPT: Constructor
    // ========================================================

    public Laptop() {

        // Calls ElectronicsProduct constructor.
        super();
    }

    // ========================================================
    // CONCEPT: Parameterized Constructor
    // CONCEPT: super()
    // CONCEPT: this
    // ========================================================

    public Laptop(int productId,
                  String productName,
                  double price,
                  int quantity,
                  int warrantyYears,
                  int ram,
                  int storage) {

        // Send common information to parent.
        super(productId,
                productName,
                price,
                quantity,
                warrantyYears);

        // 'this' refers to current Laptop object.
        this.ram = ram;
        this.storage = storage;
    }

    // ========================================================
    // Getters
    // ========================================================

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    // ========================================================
    // Setters
    // ========================================================

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // ========================================================

    @Override
    public void displayDetails() {

        // Call parent displayDetails()
        super.displayDetails();

        System.out.println("RAM      : " + ram + " GB");
        System.out.println("Storage  : " + storage + " GB");
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // ========================================================
    // Laptop has its own final price calculation.

    @Override
    public double calculateFinalPrice() {

        return getPrice() * 1.08;
    }
}