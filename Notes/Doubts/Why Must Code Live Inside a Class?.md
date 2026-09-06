# Why Must Code Live Inside a Class?

- Java enforces a strict object-oriented architecture where classes act as the fundamental container for all data and behavior.
- Every variable and method must belong to an explicit namespace (a class). Nothing floats freely.

#### JVM & ClassLoader Architecture

- The Java Virtual Machine (JVM) does not execute arbitrary instructions.
- JVM’s foundational unit of execution is a **class**.
    1. **Compilation:** `javac Main.java` → `Main.class` (bytecode)
    2. **Loading:** The JVM's **ClassLoader** looks specifically for the `.class` file.
    3. **Execution:** The JVM loads the class metadata into memory and looks for the standard **entry point**:

       ```java
       public static void main(String[] args){}
       ```

       > 💡 *If code could exist outside a class, the JVM wouldn't have a container to load, link, and secure.*

#### Why `static` is Required for `main`

Since the entry point is wrapped inside a class, you might wonder: *Do I need to create an object (**`new Main()`**) just to run the program?*

- **The Solution:** The `static` keyword attaches the `main` method directly to the **class itself**, rather than any individual instance.
- This allows the JVM to execute `Main.main(args)` immediately without instantiating the class first.