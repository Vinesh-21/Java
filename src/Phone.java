// ======================================================
// INHERITANCE
// ======================================================
//
// Product
//    ↓
// ElectronicsProduct
//    ↓
// Phone
//
// Phone IS-A ElectronicsProduct
// Phone IS-A Product
// ======================================================

public class Phone extends ElectronicsProduct {

    private String operatingSystem;


    // ==================================================
    // DEFAULT CONSTRUCTOR
    // ==================================================

    public Phone() {

        super();
    }


    // ==================================================
    // PARAMETERIZED CONSTRUCTOR
    // ==================================================

    public Phone(
            int productId,
            String productName,
            double price,
            int quantity,
            int warrantyYears,
            String operatingSystem) {


        // ==================================================
        // super()
        // ==================================================
        // Calls ElectronicsProduct constructor.
        // ==================================================

        super(
                productId,
                productName,
                price,
                quantity,
                warrantyYears
        );


        // this = current Phone object

        this.operatingSystem = operatingSystem;
    }


    // ==================================================
    // GETTER
    // ==================================================

    public String getOperatingSystem() {

        return operatingSystem;
    }


    // ==================================================
    // SETTER
    // ==================================================

    public void setOperatingSystem(String operatingSystem) {

        this.operatingSystem = operatingSystem;
    }


    // ==================================================
    // METHOD OVERRIDING
    // ==================================================

    @Override
    public void displayDetails() {

        // Calls parent implementation first.

        super.displayDetails();

        System.out.println(
                "OS       : "
                        + operatingSystem
        );
    }


    // ==================================================
    // METHOD OVERRIDING
    // ==================================================

    @Override
    public double calculateFinalPrice() {

        // Phone has a 3% additional charge.

        return getPrice() * 1.03;
    }
}