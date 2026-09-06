// ============================================================
// CUSTOMER.JAVA
// ============================================================
// TOPICS COVERED:
//
// 1. Class
// 2. Encapsulation
// 3. Constructors
// 4. this
// 5. Getters
// 6. Setters
// 7. Method Overriding
// 8. equals()
// 9. hashCode()
// 10. toString()
// ============================================================

public class Customer {

    // ========================================================
    // CONCEPT: Encapsulation
    // ========================================================

    private int customerId;
    private String name;
    private String email;

    // ========================================================
    // Default Constructor
    // ========================================================

    public Customer() {
    }

    // ========================================================
    // Parameterized Constructor
    // CONCEPT: this
    // ========================================================

    public Customer(int customerId,
                    String name,
                    String email) {

        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    // ========================================================
    // Getters
    // ========================================================

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // ========================================================
    // Setters
    // ========================================================

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ========================================================
    // Display customer
    // ========================================================

    public void displayDetails() {

        System.out.println(
                "Customer ID : "
                        + customerId
        );

        System.out.println(
                "Name        : "
                        + name
        );

        System.out.println(
                "Email       : "
                        + email
        );
    }

    // ========================================================
    // CONCEPT: Method Overriding
    // ========================================================

    @Override
    public String toString() {

        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    // ========================================================
    // CONCEPT: equals()
    // ========================================================

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Customer)) {
            return false;
        }

        Customer other = (Customer) obj;

        return this.customerId == other.customerId;
    }

    // ========================================================
    // CONCEPT: hashCode()
    // ========================================================

    @Override
    public int hashCode() {

        return Integer.hashCode(customerId);
    }
}