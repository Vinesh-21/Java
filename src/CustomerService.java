// ============================================================
// CUSTOMERSERVICE.JAVA
// ============================================================
// TOPICS COVERED:
//
// 1. List
// 2. Set
// 3. Map
// 4. Streams
// 5. filter()
// 6. sorted()
// 7. Optional
// 8. Method References
// 9. Constructor References
// ============================================================

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public class CustomerService {

    // ========================================================
    // CONCEPT: List
    // ========================================================

    private List<Customer> customers;

    // ========================================================
    // CONCEPT: Set
    // ========================================================

    private Set<Customer> customerSet;

    // ========================================================
    // CONCEPT: Map
    // ========================================================

    private Map<Integer, Customer> customerMap;

    // ========================================================
    // Constructor
    // ========================================================

    public CustomerService() {

        customers = new ArrayList<>();

        customerSet = new HashSet<>();

        customerMap = new HashMap<>();
    }

    // ========================================================
    // Add customer
    // ========================================================

    public void addCustomer(Customer customer) {

        customers.add(customer);

        customerSet.add(customer);

        customerMap.put(
                customer.getCustomerId(),
                customer
        );
    }

    // ========================================================
    // CONCEPT: Optional
    // ========================================================

    public Optional<Customer> findCustomer(int id) {

        return Optional.ofNullable(
                customerMap.get(id)
        );
    }

    // ========================================================
    // CONCEPT: Stream filter()
    // ========================================================

    public void searchByName(String name) {

        customers.stream()

                .filter(customer ->
                        customer.getName()
                                .equalsIgnoreCase(name))

                // CONCEPT: Method Reference
                .forEach(
                        Customer::displayDetails
                );
    }

    // ========================================================
    // CONCEPT: Stream sorted()
    // ========================================================

    public void displayCustomersSorted() {

        customers.stream()

                .sorted(
                        (c1, c2) ->
                                c1.getName()
                                        .compareToIgnoreCase(
                                                c2.getName()
                                        )
                )

                // CONCEPT: Method Reference
                .forEach(
                        Customer::displayDetails
                );
    }

    // ========================================================
    // CONCEPT: Method Reference
    // ========================================================
    //
    // Customer::displayDetails
    //
    // This is shorter than:
    //
    // customer -> customer.displayDetails()

    public void displayCustomers() {

        customers.forEach(
                Customer::displayDetails
        );
    }

    // ========================================================
    // CONCEPT: Constructor Reference
    // ========================================================
    //
    // Customer::new
    //
    // This refers to the Customer constructor.

    public Customer createCustomerUsingConstructorReference() {

        Supplier<Customer> supplier =
                Customer::new;

        Customer customer =
                supplier.get();

        customer.setCustomerId(999);
        customer.setName(
                "Reference Customer"
        );
        customer.setEmail(
                "reference@example.com"
        );

        return customer;
    }

    // ========================================================
    // Getter
    // ========================================================

    public List<Customer> getCustomers() {

        return customers;
    }
}