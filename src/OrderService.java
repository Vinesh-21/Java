// ============================================================
// ORDERSERVICE.JAVA
// ============================================================
// TOPICS COVERED:
//
// 1. List
// 2. Map
// 3. Streams
// 4. filter()
// 5. map()
// 6. reduce()
// 7. sorted()
// 8. Optional
// 9. Method References
// 10. Enum
// ============================================================

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderService {

    // ========================================================
    // CONCEPT: List
    // ========================================================

    private List<Order> orders;

    // ========================================================
    // CONCEPT: Map
    // ========================================================

    private Map<Integer, Order> orderMap;

    // ========================================================
    // Constructor
    // ========================================================

    public OrderService() {

        orders = new ArrayList<>();

        orderMap = new HashMap<>();
    }

    // ========================================================
    // Add Order
    // ========================================================

    public void addOrder(Order order) {

        orders.add(order);

        orderMap.put(
                order.getOrderId(),
                order
        );
    }

    // ========================================================
    // CONCEPT: Optional
    // ========================================================

    public Optional<Order> findOrder(int id) {

        return Optional.ofNullable(
                orderMap.get(id)
        );
    }

    // ========================================================
    // CONCEPT: Stream filter()
    // ========================================================

    public void showPlacedOrders() {

        orders.stream()

                .filter(order ->
                        order.getStatus()
                                == OrderStatus.PLACED)

                // CONCEPT: Method Reference
                .forEach(
                        Order::displayOrder
                );
    }

    // ========================================================
    // CONCEPT: Stream sorted()
    // ========================================================

    public void sortOrdersByTotal() {

        orders.stream()

                .sorted(
                        (o1, o2) ->
                                Double.compare(
                                        o2.calculateFinalTotal(),
                                        o1.calculateFinalTotal()
                                )
                )

                // CONCEPT: Method Reference
                .forEach(
                        Order::displayOrder
                );
    }

    // ========================================================
    // CONCEPT: Stream map()
    // ========================================================
    //
    // Convert Order objects into final totals.

    // ========================================================
    // CONCEPT: Stream reduce()
    // ========================================================
    //
    // Add all order totals.

    public double calculateTotalSales() {

        return orders.stream()

                .map(
                        Order::calculateFinalTotal
                )

                .reduce(
                        0.0,
                        Double::sum
                );
    }

    // ========================================================
    // Update order status
    // ========================================================

    public void updateOrderStatus(
            int orderId,
            OrderStatus status) {

        // CONCEPT: Optional

        Optional<Order> result =
                findOrder(orderId);

        if (result.isPresent()) {

            result.get()
                    .updateStatus(status);

            System.out.println(
                    "Order status updated."
            );

        } else {

            System.out.println(
                    "Order not found."
            );
        }
    }

    // ========================================================
    // CONCEPT: Method Reference
    // ========================================================

    public void displayAllOrders() {

        orders.forEach(
                Order::displayOrder
        );
    }

    // ========================================================
    // Getter
    // ========================================================

    public List<Order> getOrders() {

        return orders;
    }
}