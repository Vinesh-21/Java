// ============================================================
// ORDER.JAVA
// ============================================================
// TOPICS COVERED:
//
// 1. Encapsulation
// 2. Interface implementation
// 3. Collections - List
// 4. ArrayList
// 5. Enum
// 6. Composition
// 7. Streams
// 8. map()
// 9. reduce()
// 10. Method References
// 11. Polymorphism
// ============================================================

import java.util.ArrayList;
import java.util.List;

// ============================================================
// CONCEPT: Interface Implementation
// ============================================================
// Order implements Discountable.
//
// Therefore Order MUST implement calculateDiscount().

public class Order implements Discountable {

    private int orderId;

    private Customer customer;

    // ========================================================
    // CONCEPT: Collection - List
    // ========================================================

    private List<OrderItem> items;

    // ========================================================
    // CONCEPT: Enum
    // ========================================================

    private OrderStatus status;

    private PaymentMethod paymentMethod;

    // ========================================================
    // Constructor
    // ========================================================

    public Order(int orderId,
                 Customer customer,
                 PaymentMethod paymentMethod) {

        this.orderId = orderId;
        this.customer = customer;
        this.paymentMethod = paymentMethod;

        // Create ArrayList.
        this.items = new ArrayList<>();

        // Set initial enum value.
        this.status = OrderStatus.PLACED;
    }

    // ========================================================
    // Getters
    // ========================================================

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    // ========================================================
    // Add item to List
    // ========================================================

    public void addItem(Product product,
                        int quantity) {

        OrderItem item =
                new OrderItem(product, quantity);

        items.add(item);
    }

    // ========================================================
    // CONCEPT: Stream map()
    // ========================================================
    // Convert each OrderItem into its total value.

    // ========================================================
    // CONCEPT: Stream reduce()
    // ========================================================
    // Add all item totals together.

    public double calculateSubtotal() {

        return items.stream()

                .map(OrderItem::getItemTotal)

                .reduce(
                        0.0,
                        Double::sum
                );
    }

    // ========================================================
    // CONCEPT: Interface method implementation
    // ========================================================

    @Override
    public double calculateDiscount() {

        double subtotal =
                calculateSubtotal();

        if (subtotal >= 50000) {

            return subtotal * 0.10;

        } else if (subtotal >= 10000) {

            return subtotal * 0.05;

        } else {

            return 0;
        }
    }

    public double calculateFinalTotal() {

        return calculateSubtotal()
                - calculateDiscount();
    }

    // ========================================================
    // Update Enum value
    // ========================================================

    public void updateStatus(OrderStatus status) {

        this.status = status;
    }

    // ========================================================
    // CONCEPT: Method Reference
    // ========================================================
    // OrderItem::displayItem

    public void displayOrder() {

        System.out.println(
                "\n----------------------------"
        );

        System.out.println(
                "Order ID      : "
                        + orderId
        );

        System.out.println(
                "Customer      : "
                        + customer.getName()
        );

        System.out.println(
                "Payment       : "
                        + paymentMethod
        );

        System.out.println(
                "Status        : "
                        + status
        );

        System.out.println("\nItems:");

        // Method Reference
        items.forEach(
                OrderItem::displayItem
        );

        System.out.println(
                "----------------------------"
        );

        System.out.println(
                "Subtotal      : ₹"
                        + calculateSubtotal()
        );

        System.out.println(
                "Discount      : ₹"
                        + calculateDiscount()
        );

        System.out.println(
                "Final Total   : ₹"
                        + calculateFinalTotal()
        );

        System.out.println(
                "----------------------------"
        );
    }
}