// ============================================================
// MAIN.JAVA
// ============================================================
// TOPICS COVERED:
//
// 1. Object creation
// 2. Scanner
// 3. while loop
// 4. switch
// 5. Polymorphism
// 6. Upcasting
// 7. Optional
// 8. Enum
// 9. Collections through services
// ============================================================

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ====================================================
        // Create Scanner object
        // ====================================================

        Scanner sc =
                new Scanner(System.in);

        // ====================================================
        // Create service objects
        // ====================================================

        Inventory inventory =
                new Inventory();

        CustomerService customerService =
                new CustomerService();

        OrderService orderService =
                new OrderService();

        boolean exit = false;

        // ====================================================
        // CONCEPT: while loop
        // ====================================================

        while (!exit) {

            System.out.println(
                    "\n======================================"
            );

            System.out.println(
                    "       SMART INVENTORY SYSTEM"
            );

            System.out.println(
                    "======================================"
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
                    "8. Sort Products By Price"
            );

            System.out.println(
                    "9. Show Product Names"
            );

            System.out.println(
                    "10. Show Unique Product Names"
            );

            System.out.println(
                    "11. Show Product Map"
            );

            System.out.println(
                    "12. Demonstrate Polymorphism"
            );

            System.out.println(
                    "13. Demonstrate Downcasting"
            );

            System.out.println(
                    "14. Add Customer"
            );

            System.out.println(
                    "15. View Customers"
            );

            System.out.println(
                    "16. Search Customer"
            );

            System.out.println(
                    "17. Create Order"
            );

            System.out.println(
                    "18. View Orders"
            );

            System.out.println(
                    "19. Search Order"
            );

            System.out.println(
                    "20. Update Order Status"
            );

            System.out.println(
                    "21. Calculate Total Sales"
            );

            System.out.println(
                    "22. Exit"
            );

            System.out.print(
                    "\nEnter choice: "
            );

            int choice =
                    sc.nextInt();

            sc.nextLine();

            // =================================================
            // CONCEPT: switch
            // =================================================

            switch (choice) {

                // =================================================
                // ADD LAPTOP
                // =================================================

                case 1:

                    System.out.print(
                            "Product ID: "
                    );

                    int laptopId =
                            sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                            "Product Name: "
                    );

                    String laptopName =
                            sc.nextLine();

                    System.out.print(
                            "Price: "
                    );

                    double laptopPrice =
                            sc.nextDouble();

                    System.out.print(
                            "Quantity: "
                    );

                    int laptopQuantity =
                            sc.nextInt();

                    System.out.print(
                            "Warranty Years: "
                    );

                    int warranty =
                            sc.nextInt();

                    System.out.print(
                            "RAM GB: "
                    );

                    int ram =
                            sc.nextInt();

                    System.out.print(
                            "Storage GB: "
                    );

                    int storage =
                            sc.nextInt();

                    // =================================================
                    // Create Laptop object
                    // =================================================

                    Laptop laptop =
                            new Laptop(
                                    laptopId,
                                    laptopName,
                                    laptopPrice,
                                    laptopQuantity,
                                    warranty,
                                    ram,
                                    storage
                            );

                    // =================================================
                    // CONCEPT: Upcasting
                    // =================================================
                    //
                    // Laptop object is assigned to Product reference.

                    Product laptopProduct =
                            laptop;

                    inventory.addProduct(
                            laptopProduct
                    );

                    System.out.println(
                            "Laptop added."
                    );

                    break;

                // =================================================
                // ADD PHONE
                // =================================================

                case 2:

                    System.out.print(
                            "Product ID: "
                    );

                    int phoneId =
                            sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                            "Product Name: "
                    );

                    String phoneName =
                            sc.nextLine();

                    System.out.print(
                            "Price: "
                    );

                    double phonePrice =
                            sc.nextDouble();

                    System.out.print(
                            "Quantity: "
                    );

                    int phoneQuantity =
                            sc.nextInt();

                    System.out.print(
                            "Warranty Years: "
                    );

                    int phoneWarranty =
                            sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                            "Operating System: "
                    );

                    String os =
                            sc.nextLine();

                    Phone phone =
                            new Phone(
                                    phoneId,
                                    phoneName,
                                    phonePrice,
                                    phoneQuantity,
                                    phoneWarranty,
                                    os
                            );

                    // CONCEPT: Upcasting

                    Product phoneProduct =
                            phone;

                    inventory.addProduct(
                            phoneProduct
                    );

                    System.out.println(
                            "Phone added."
                    );

                    break;

                // =================================================
                // ADD GROCERY
                // =================================================

                case 3:

                    System.out.print(
                            "Product ID: "
                    );

                    int groceryId =
                            sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                            "Product Name: "
                    );

                    String groceryName =
                            sc.nextLine();

                    System.out.print(
                            "Price: "
                    );

                    double groceryPrice =
                            sc.nextDouble();

                    System.out.print(
                            "Quantity: "
                    );

                    int groceryQuantity =
                            sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                            "Expiry Date: "
                    );

                    String expiry =
                            sc.nextLine();

                    GroceryProduct grocery =
                            new GroceryProduct(
                                    groceryId,
                                    groceryName,
                                    groceryPrice,
                                    groceryQuantity,
                                    expiry
                            );

                    // CONCEPT: Upcasting

                    Product groceryProduct =
                            grocery;

                    inventory.addProduct(
                            groceryProduct
                    );

                    System.out.println(
                            "Grocery product added."
                    );

                    break;

                // =================================================
                // VIEW PRODUCTS
                // =================================================

                case 4:

                    inventory.viewProducts();

                    break;

                // =================================================
                // SEARCH PRODUCT
                // =================================================

                case 5:

                    System.out.print(
                            "Enter Product ID: "
                    );

                    int searchId =
                            sc.nextInt();

                    // CONCEPT: Optional

                    Optional<Product> productResult =
                            inventory.findProduct(
                                    searchId
                            );

                    if (productResult.isPresent()) {

                        Product product =
                                productResult.get();

                        product.displayDetails();

                    } else {

                        System.out.println(
                                "Product not found."
                        );
                    }

                    break;

                // =================================================
                // INVENTORY VALUE
                // =================================================

                case 6:

                    System.out.println(
                            "Inventory Value: ₹"
                                    + inventory
                                    .calculateInventoryValue()
                    );

                    break;

                // =================================================
                // LOW STOCK
                // =================================================

                case 7:

                    inventory.showLowStockProducts();

                    break;

                // =================================================
                // SORT
                // =================================================

                case 8:

                    inventory.sortProductsByPrice();

                    break;

                // =================================================
                // MAP
                // =================================================

                case 9:

                    inventory.showProductNames();

                    break;

                // =================================================
                // SET
                // =================================================

                case 10:

                    inventory.showUniqueProductNames();

                    break;

                // =================================================
                // MAP
                // =================================================

                case 11:

                    inventory.showProductMap();

                    break;

                // =================================================
                // POLYMORPHISM
                // =================================================

                case 12:

                    inventory.demonstratePolymorphism();

                    break;

                // =================================================
                // DOWNCASTING
                // =================================================

                case 13:

                    inventory.demonstrateDowncasting();

                    break;

                // =================================================
                // ADD CUSTOMER
                // =================================================

                case 14:

                    System.out.print(
                            "Customer ID: "
                    );

                    int customerId =
                            sc.nextInt();

                    sc.nextLine();

                    System.out.print(
                            "Customer Name: "
                    );

                    String customerName =
                            sc.nextLine();

                    System.out.print(
                            "Customer Email: "
                    );

                    String customerEmail =
                            sc.nextLine();

                    Customer customer =
                            new Customer(
                                    customerId,
                                    customerName,
                                    customerEmail
                            );

                    customerService.addCustomer(
                            customer
                    );

                    System.out.println(
                            "Customer added."
                    );

                    break;

                // =================================================
                // VIEW CUSTOMERS
                // =================================================

                case 15:

                    customerService.displayCustomers();

                    break;

                // =================================================
                // SEARCH CUSTOMER
                // =================================================

                case 16:

                    System.out.print(
                            "Enter Customer ID: "
                    );

                    int findCustomerId =
                            sc.nextInt();

                    // CONCEPT: Optional

                    Optional<Customer> customerResult =
                            customerService.findCustomer(
                                    findCustomerId
                            );

                    if (customerResult.isPresent()) {

                        customerResult
                                .get()
                                .displayDetails();

                    } else {

                        System.out.println(
                                "Customer not found."
                        );
                    }

                    break;

                // =================================================
                // CREATE ORDER
                // =================================================

                case 17:

                    if (customerService
                            .getCustomers()
                            .isEmpty()) {

                        System.out.println(
                                "Add a customer first."
                        );

                        break;
                    }

                    if (inventory
                            .getProducts()
                            .isEmpty()) {

                        System.out.println(
                                "Add a product first."
                        );

                        break;
                    }

                    System.out.print(
                            "Order ID: "
                    );

                    int orderId =
                            sc.nextInt();

                    System.out.print(
                            "Customer ID: "
                    );

                    int orderCustomerId =
                            sc.nextInt();

                    Optional<Customer> selectedCustomer =
                            customerService.findCustomer(
                                    orderCustomerId
                            );

                    if (selectedCustomer.isEmpty()) {

                        System.out.println(
                                "Customer not found."
                        );

                        break;
                    }

                    // =================================================
                    // CONCEPT: Enum
                    // =================================================

                    System.out.println(
                            "1. CASH"
                    );

                    System.out.println(
                            "2. CARD"
                    );

                    System.out.println(
                            "3. UPI"
                    );

                    System.out.println(
                            "4. NET_BANKING"
                    );

                    System.out.print(
                            "Payment method: "
                    );

                    int paymentChoice =
                            sc.nextInt();

                    PaymentMethod paymentMethod;

                    switch (paymentChoice) {

                        case 1:

                            paymentMethod =
                                    PaymentMethod.CASH;

                            break;

                        case 2:

                            paymentMethod =
                                    PaymentMethod.CARD;

                            break;

                        case 3:

                            paymentMethod =
                                    PaymentMethod.UPI;

                            break;

                        default:

                            paymentMethod =
                                    PaymentMethod.NET_BANKING;
                    }

                    Order order =
                            new Order(
                                    orderId,
                                    selectedCustomer.get(),
                                    paymentMethod
                            );

                    System.out.print(
                            "Product ID: "
                    );

                    int orderProductId =
                            sc.nextInt();

                    Optional<Product> selectedProduct =
                            inventory.findProduct(
                                    orderProductId
                            );

                    if (selectedProduct.isEmpty()) {

                        System.out.println(
                                "Product not found."
                        );

                        break;
                    }

                    System.out.print(
                            "Quantity: "
                    );

                    int orderQuantity =
                            sc.nextInt();

                    order.addItem(
                            selectedProduct.get(),
                            orderQuantity
                    );

                    orderService.addOrder(
                            order
                    );

                    System.out.println(
                            "Order created."
                    );

                    order.displayOrder();

                    break;

                // =================================================
                // VIEW ORDERS
                // =================================================

                case 18:

                    orderService.displayAllOrders();

                    break;

                // =================================================
                // SEARCH ORDER
                // =================================================

                case 19:

                    System.out.print(
                            "Enter Order ID: "
                    );

                    int findOrderId =
                            sc.nextInt();

                    // CONCEPT: Optional

                    Optional<Order> orderResult =
                            orderService.findOrder(
                                    findOrderId
                            );

                    if (orderResult.isPresent()) {

                        orderResult
                                .get()
                                .displayOrder();

                    } else {

                        System.out.println(
                                "Order not found."
                        );
                    }

                    break;

                // =================================================
                // UPDATE ORDER STATUS
                // =================================================

                case 20:

                    System.out.print(
                            "Order ID: "
                    );

                    int updateOrderId =
                            sc.nextInt();

                    System.out.println(
                            "1. PLACED"
                    );

                    System.out.println(
                            "2. PROCESSING"
                    );

                    System.out.println(
                            "3. SHIPPED"
                    );

                    System.out.println(
                            "4. DELIVERED"
                    );

                    System.out.println(
                            "5. CANCELLED"
                    );

                    System.out.print(
                            "Choose status: "
                    );

                    int statusChoice =
                            sc.nextInt();

                    OrderStatus status;

                    switch (statusChoice) {

                        case 1:

                            status =
                                    OrderStatus.PLACED;

                            break;

                        case 2:

                            status =
                                    OrderStatus.PROCESSING;

                            break;

                        case 3:

                            status =
                                    OrderStatus.SHIPPED;

                            break;

                        case 4:

                            status =
                                    OrderStatus.DELIVERED;

                            break;

                        default:

                            status =
                                    OrderStatus.CANCELLED;
                    }

                    orderService.updateOrderStatus(
                            updateOrderId,
                            status
                    );

                    break;

                // =================================================
                // TOTAL SALES
                // =================================================

                case 21:

                    System.out.println(
                            "Total Sales: ₹"
                                    + orderService
                                    .calculateTotalSales()
                    );

                    break;

                // =================================================
                // EXIT
                // =================================================

                case 22:

                    exit = true;

                    System.out.println(
                            "Thank you for using Smart Inventory!"
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid choice."
                    );
            }
        }

        sc.close();
    }
}