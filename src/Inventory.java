import java.util.Scanner;

public class Inventory {

    // ==================================================
    // POLYMORPHISM
    // ==================================================
    // The array uses the parent type Product.
    //
    // It can store:
    // Laptop
    // Phone
    // GroceryProduct
    // Product
    //
    // because all of them are Products.
    // ==================================================

    private Product[] products;

    private int productCount;

    private Scanner sc;


    // ==================================================
    // CONSTRUCTOR
    // ==================================================

    public Inventory(Scanner sc) {

        // Uses final constant from Product.

        this.products =
                new Product[Product.MAX_PRODUCTS];

        this.productCount = 0;

        this.sc = sc;
    }


    // ==================================================
    // METHOD OVERLOADING - VERSION 1
    // ==================================================
    // addProduct(Product)
    // ==================================================

    public void addProduct(Product product) {

        if (productCount >= products.length) {

            System.out.println(
                    "Inventory is full."
            );

            return;
        }


        products[productCount] = product;

        productCount++;

        System.out.println(
                "Product added successfully."
        );
    }


    // ==================================================
    // METHOD OVERLOADING - VERSION 2
    // ==================================================
    // Same method name.
    // Different parameter list.
    //
    // This demonstrates METHOD OVERLOADING.
    // ==================================================

    public void addProduct(
            int id,
            String name,
            double price,
            int quantity) {


        Product product =
                new Product(
                        id,
                        name,
                        price,
                        quantity
                );


        // Calls overloaded addProduct(Product)

        addProduct(product);
    }


    // ==================================================
    // VIEW PRODUCTS
    // ==================================================

    public void viewProducts() {

        System.out.println();
        System.out.println(
                "===== PRODUCTS ====="
        );


        if (productCount == 0) {

            System.out.println(
                    "No products available."
            );

            return;
        }


        for (int i = 0; i < productCount; i++) {

            System.out.println();

            System.out.println(
                    "----- Product "
                            + (i + 1)
                            + " -----"
            );


            // ==================================================
            // POLYMORPHISM
            // ==================================================
            // Product reference points to an actual child
            // object.
            // ==================================================

            Product product = products[i];


            // ==================================================
            // DYNAMIC METHOD DISPATCH
            // ==================================================
            // Java determines which displayDetails()
            // implementation to call at RUNTIME.
            //
            // If object = Laptop
            //     Laptop.displayDetails()
            //
            // If object = Phone
            //     Phone.displayDetails()
            //
            // If object = GroceryProduct
            //     GroceryProduct.displayDetails()
            // ==================================================

            product.displayDetails();


            System.out.println(
                    "Stock Status : "
                            + product.getStockStatus()
            );


            // ==================================================
            // DYNAMIC METHOD DISPATCH
            // ==================================================
            // The actual object's overridden
            // calculateFinalPrice() executes.
            // ==================================================

            System.out.printf(
                    "Final Price  : ₹%.2f%n",
                    product.calculateFinalPrice()
            );
        }
    }


    // ==================================================
    // SEARCH PRODUCT
    // ==================================================

    public void searchProduct() {

        System.out.print(
                "Enter Product ID: "
        );

        int id = sc.nextInt();


        Product foundProduct = null;


        for (int i = 0; i < productCount; i++) {

            if (
                    products[i].getProductId()
                            == id
            ) {

                foundProduct = products[i];

                break;
            }
        }


        System.out.println();


        if (foundProduct != null) {

            System.out.println(
                    "Product Found"
            );

            System.out.println();


            // POLYMORPHISM
            // Calls correct overridden method.

            foundProduct.displayDetails();


            System.out.println(
                    "Stock Status : "
                            + foundProduct.getStockStatus()
            );

        } else {

            System.out.println(
                    "Product not found."
            );
        }
    }


    // ==================================================
    // CALCULATE INVENTORY VALUE
    // ==================================================

    public void calculateInventoryValue() {

        double totalValue = 0;


        for (int i = 0; i < productCount; i++) {

            totalValue +=
                    products[i].getInventoryValue();
        }


        System.out.println();

        System.out.println(
                "===== INVENTORY VALUE ====="
        );

        System.out.printf(
                "Total Inventory Value: ₹%.2f%n",
                totalValue
        );
    }


    // ==================================================
    // SHOW LOW STOCK PRODUCTS
    // ==================================================

    public void showLowStockProducts() {

        System.out.println();

        System.out.println(
                "===== LOW STOCK PRODUCTS ====="
        );


        boolean found = false;


        for (int i = 0; i < productCount; i++) {

            Product product = products[i];


            if (product.getQuantity() <= 5) {

                System.out.println(
                        product.getProductName()
                                + " → "
                                + product.getQuantity()
                );

                found = true;
            }
        }


        if (!found) {

            System.out.println(
                    "No low stock products."
            );
        }
    }


    // ==================================================
    // ADD LAPTOP
    // ==================================================

    public void addLaptop() {

        System.out.print(
                "Enter Product ID: "
        );

        int id = sc.nextInt();


        System.out.print(
                "Enter Product Name: "
        );

        String name = sc.next();


        System.out.print(
                "Enter Price: "
        );

        double price = sc.nextDouble();


        System.out.print(
                "Enter Quantity: "
        );

        int quantity = sc.nextInt();


        System.out.print(
                "Enter Warranty Years: "
        );

        int warranty = sc.nextInt();


        System.out.print(
                "Enter RAM (GB): "
        );

        int ram = sc.nextInt();


        System.out.print(
                "Enter Storage (GB): "
        );

        int storage = sc.nextInt();


        // ==================================================
        // OBJECT CREATION
        // ==================================================

        Laptop laptop =
                new Laptop(
                        id,
                        name,
                        price,
                        quantity,
                        warranty,
                        ram,
                        storage
                );


        // ==================================================
        // UPCASTING
        // ==================================================
        // Laptop → Product
        //
        // A Laptop IS-A Product.
        // Therefore this is safe.
        // ==================================================

        Product product = laptop;


        // Product reference now points to Laptop object.

        addProduct(product);
    }


    // ==================================================
    // ADD PHONE
    // ==================================================

    public void addPhone() {

        System.out.print(
                "Enter Product ID: "
        );

        int id = sc.nextInt();


        System.out.print(
                "Enter Product Name: "
        );

        String name = sc.next();


        System.out.print(
                "Enter Price: "
        );

        double price = sc.nextDouble();


        System.out.print(
                "Enter Quantity: "
        );

        int quantity = sc.nextInt();


        System.out.print(
                "Enter Warranty Years: "
        );

        int warranty = sc.nextInt();


        System.out.print(
                "Enter Operating System: "
        );

        String os = sc.next();


        Phone phone =
                new Phone(
                        id,
                        name,
                        price,
                        quantity,
                        warranty,
                        os
                );


        // ==================================================
        // UPCASTING
        // ==================================================

        Product product = phone;

        addProduct(product);
    }


    // ==================================================
    // ADD GROCERY PRODUCT
    // ==================================================

    public void addGroceryProduct() {

        System.out.print(
                "Enter Product ID: "
        );

        int id = sc.nextInt();


        System.out.print(
                "Enter Product Name: "
        );

        String name = sc.next();


        System.out.print(
                "Enter Price: "
        );

        double price = sc.nextDouble();


        System.out.print(
                "Enter Quantity: "
        );

        int quantity = sc.nextInt();


        System.out.print(
                "Enter Expiry Date: "
        );

        String expiryDate = sc.next();


        GroceryProduct grocery =
                new GroceryProduct(
                        id,
                        name,
                        price,
                        quantity,
                        expiryDate
                );


        // ==================================================
        // UPCASTING
        // ==================================================

        Product product = grocery;

        addProduct(product);
    }


    // ==================================================
    // DOWNCASTING
    // ==================================================
    // Product → Laptop
    //
    // Downcasting should only happen when the actual
    // object is really a Laptop.
    // ==================================================

    public void demonstrateDowncasting() {

        if (productCount == 0) {

            System.out.println(
                    "No products available."
            );

            return;
        }


        Product product = products[0];


        // ==================================================
        // instanceof
        // ==================================================
        // Checks the actual object type before
        // performing downcasting.
        // ==================================================

        if (product instanceof Laptop) {


            // ==================================================
            // DOWNCASTING
            // ==================================================
            // Product → Laptop
            // ==================================================

            Laptop laptop =
                    (Laptop) product;


            // Now Laptop-specific methods are available.

            System.out.println(
                    "Laptop RAM: "
                            + laptop.getRam()
                            + " GB"
            );


            System.out.println(
                    "Laptop Storage: "
                            + laptop.getStorage()
                            + " GB"
            );

        } else {

            System.out.println(
                    "First product is not a Laptop."
            );
        }
    }
}