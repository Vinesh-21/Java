# # Java Data Types, Wrappers & Type Casting
 
 - Java is a statically-typed language where every variable must have a declared type before compilation.
 - Memory allocation, valid operations, and default values are strictly enforced by the type system.
 
 ---
 
 ### Primitive Data Types
 
 Primitives are the most fundamental building blocks in Java. They are not objects; they hold raw binary values directly in stack memory.
 
 | Type | Size | Default Value | Range / Representation |
 | :--- | :--- | :--- | :--- |
 | `byte` | 8 bits (1 byte) | `0` | -128 to 127 |
 | `short` | 16 bits (2 bytes) | `0` | -32,768 to 32,767 |
 | `int` | 32 bits (4 bytes) | `0` | -2^31 to 2^31 - 1 (standard integer) |
 | `long` | 64 bits (8 bytes) | `0L` | -2^63 to 2^63 - 1 (append with `L`) |
 | `float` | 32 bits (4 bytes) | `0.0f` | IEEE 754 single-precision (append with `f`) |
 | `double` | 64 bits (8 bytes) | `0.0d` | IEEE 754 double-precision (standard decimal) |
 | `char` | 16 bits (2 bytes) | `'\u0000'` | 0 to 65,535 (Unicode character) |
 | `boolean`| JVM-dependent | `false` | `true` or `false` |
 
 > 💡 *`double` and `float` cannot represent fractional base-10 values (like `0.1`) with exact precision due to binary floating-point representation. Always use `BigDecimal` for precise financial calculations.*
 
 ---
 
 ### Binary Numeric Promotion
 
 In arithmetic operations, the Java compiler enforces automatic widening to prevent register mismatch at the bytecode level.
 
 - **The Rule:** Any integer operand smaller than `int` (`byte`, `short`, `char`) is automatically promoted to `int` before an arithmetic operator is evaluated.
 - If any operand is `long`, `float`, or `double`, the entire expression widens to match the widest operand:
 
 ```java
 byte a = 10;
 byte b = 20;
 
 // Compilation Error: (a + b) is promoted to an int
 // byte c = a + b;
 
 byte c = (byte) (a + b); // Explicit cast required
 ```
 
 ---
 
 ### Type Casting
 
 Type casting converts a value from one primitive type to another.
 
 #### 1. Widening Casting (Implicit / Safe)
 - Automatically performed when passing a smaller data type to a larger data type.
 - **Direction:** `byte` → `short` → `int` → `long` → `float` → `double`
 
 ```java
 int count = 100;
 double accurateCount = count; // Automatic widening: 100.0
 ```
 
 #### 2. Narrowing Casting (Explicit / Unsafe)
 - Manually required when passing a larger data type to a smaller data type.
 - Carries risk of data truncation and integer overflow.
 
 ```java
 double price = 99.99;
 int roundedPrice = (int) price; // Truncates decimal to 99
 
 int largeNumber = 130;
 byte smallByte = (byte) largeNumber; // Overflows: wraps around to -126
 ```
 
 #### 3. Compound Assignment Casting Gotcha
 Compound assignment operators (`+=`, `-=`, `*=`, `/=`) insert an implicit narrowing cast under the hood.
 
 ```java
 byte x = 127;
 x += 1; // Evaluates as x = (byte)(x + 1)
 // Result: -128 (silent rollover with no compiler warning)
 ```
 
 ---
 
 ### Primitive Wrapper Classes
 
 Each primitive has an equivalent object representation in the `java.lang` package. Wrappers allow primitives to participate in object-oriented systems (e.g., Collections like `ArrayList<Integer>`).
 
 | Primitive | Wrapper Class |
 | :--- | :--- |
 | `byte` | `Byte` |
 | `short` | `Short` |
 | `int` | `Integer` |
 | `long` | `Long` |
 | `float` | `Float` |
 | `double` | `Double` |
 | `char` | `Character` |
 | `boolean` | `Boolean` |
 
 #### Autoboxing and Unboxing
 - **Autoboxing:** Automatic conversion of a primitive to its corresponding wrapper class (`int` → `Integer`).
 - **Unboxing:** Automatic extraction of the primitive value from the wrapper object (`Integer` → `int`).
 
 ```java
 Integer boxed = 10; // Autoboxing: Integer.valueOf(10)
 int unboxed = boxed; // Unboxing: boxed.intValue()
 ```
 
 > ⚠️ *Watch out for `NullPointerException`: attempting to unbox a wrapper whose reference is `null` throws an NPE at runtime.*
 
 #### The Wrapper Cache Mechanics
 To optimize memory, the JVM maintains an internal flyweight cache for frequently used wrapper objects.
 
 - **Cached Ranges:**
   - `Byte`, `Short`, `Integer`, `Long`: -128 to 127
   - `Character`: 0 to 127
   - `Boolean`: `TRUE` and `FALSE`
 
 - **Reference vs. Value Equality:**
 
 ```java
 Integer a = 127;
 Integer b = 127;
 System.out.println(a == b);      // true (points to same cached instance)
 
 Integer x = 128;
 Integer y = 128;
 System.out.println(x == y);      // false (distinct heap objects)
 System.out.println(x.equals(y)); // true (compares primitive values)
 ```
 
 > 💡 *Always use `.equals()` when comparing wrapper class instances to avoid subtle bugs introduced by the cache boundary.