// ======================================================
// MULTILEVEL INHERITANCE
// ======================================================
//
// Product
//    ↓
// ElectronicsProduct
//    ↓
// Laptop
//
// Laptop IS-A ElectronicsProduct
// Laptop IS-A Product
// ======================================================

public class Laptop extends ElectronicsProduct {

    private int ram;
    private int storage;


    // ==================================================
    // DEFAULT CONSTRUCTOR
    // ==================================================

    public Laptop() {

        // Calls ElectronicsProduct constructor.
        super();
    }


    // ==================================================
    // PARAMETERIZED CONSTRUCTOR
    // ==================================================

    public Laptop(
            int productId,
            String productName,
            double price,
            int quantity,
            int warrantyYears,
            int ram,
            int storage) {


        // ==================================================
        // super(...)
        // ==================================================
        // Calls ElectronicsProduct constructor.
        //
        // ElectronicsProduct will then call
        // Product's constructor.
        // ==================================================

        super(
                productId,
                productName,
                price,
                quantity,
                warrantyYears
        );


        // ==================================================
        // this
        // ==================================================
        // this.ram = instance variable
        // ram      = parameter
        // ==================================================

        this.ram = ram;
        this.storage = storage;
    }


    // ==================================================
    // GETTERS
    // ==================================================

    public int getRam() {

        return ram;
    }

    public int getStorage() {

        return storage;
    }


    // ==================================================
    // SETTERS
    // ==================================================

    public void setRam(int ram) {

        this.ram = ram;
    }

    public void setStorage(int storage) {

        this.storage = storage;
    }


    // ==================================================
    // METHOD OVERRIDING
    // ==================================================

    @Override
    public void displayDetails() {

        // Calls ElectronicsProduct's displayDetails().
        //
        // That method itself calls Product's
        // displayDetails().

        super.displayDetails();

        System.out.println(
                "RAM      : "
                        + ram
                        + " GB"
        );

        System.out.println(
                "Storage  : "
                        + storage
                        + " GB"
        );
    }


    // ==================================================
    // METHOD OVERRIDING
    // ==================================================

    @Override
    public double calculateFinalPrice() {

        // Laptop has an 8% additional charge.

        return getPrice() * 1.08;
    }
}