import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ==================================================
        // OBJECT CREATION
        // ==================================================
        // Create Scanner object.
        // ==================================================

        Scanner sc = new Scanner(System.in);


        // ==================================================
        // OBJECT CREATION
        // ==================================================
        // Create Inventory object.
        // ==================================================

        Inventory inventory =
                new Inventory(sc);


        // ==================================================
        // APPLICATION FLOW
        // ==================================================
        // Controls whether the program should continue.
        // ==================================================

        boolean exit = false;


        // ==================================================
        // LOOP
        // ==================================================
        // Keep showing the menu until user selects Exit.
        // ==================================================

        while (!exit) {

            System.out.println();

            System.out.println(
                    "===== SMART INVENTORY SYSTEM ====="
            );

            System.out.println(
                    "1. Add Laptop"
            );

            System.out.println(
                    "2. Add Phone"
            );

            System.out.println(
                    "3. Add Grocery Product"
            );

            System.out.println(
                    "4. View All Products"
            );

            System.out.println(
                    "5. Search Product"
            );

            System.out.println(
                    "6. Calculate Inventory Value"
            );

            System.out.println(
                    "7. Show Low Stock Products"
            );

            System.out.println(
                    "8. Demonstrate Downcasting"
            );

            System.out.println(
                    "9. Exit"
            );


            // ==================================================
            // SCANNER
            // ==================================================

            System.out.print(
                    "Enter your choice: "
            );

            int choice = sc.nextInt();


            System.out.println();


            // ==================================================
            // SWITCH
            // ==================================================
            // Determines which Inventory method should run.
            // ==================================================

            switch (choice) {

                case 1:

                    inventory.addLaptop();

                    break;


                case 2:

                    inventory.addPhone();

                    break;


                case 3:

                    inventory.addGroceryProduct();

                    break;


                case 4:

                    inventory.viewProducts();

                    break;


                case 5:

                    inventory.searchProduct();

                    break;


                case 6:

                    inventory.calculateInventoryValue();

                    break;


                case 7:

                    inventory.showLowStockProducts();

                    break;


                case 8:

                    inventory.demonstrateDowncasting();

                    break;


                case 9:

                    System.out.println(
                            "Thank you for using "
                                    + "Smart Inventory System."
                    );

                    exit = true;

                    break;


                default:

                    System.out.println(
                            "Invalid choice. "
                                    + "Please try again."
                    );
            }
        }


        // ==================================================
        // CLOSE SCANNER
        // ==================================================

        sc.close();
    }
}