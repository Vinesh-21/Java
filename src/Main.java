public class Main {

    public static void main(String []args){
        System.out.println("Hello World!\nMy Name is Vinesh S R");
        variables();
    }


    public static void variables (){

        // Variables - Primitive and Reference


        // Primitive - Byte, Short, Int, Flot, Double, Char and Boolean
        // Primitive --> Stores value in [Stack memory]

//        -------------------------------------------------------------------------------------------------------------
        // Reference - Strings, Arrays, Lists, Objects
        // Reference --> Stores value in [Heap memory] but has memory address in the [stack] that points to the [heap]

//        int age = 23;
//        String name = "Vinesh S R";

//        Inside your variables() method:
//        age → Stack
//        name (reference) → Stack
//        "Vinesh S R" (String object) → Heap (String Pool)
//        -------------------------------------------------------------------------------------------------------------

        // Step 1: Declare the variable <Type> <Name>;
        // Step 2: Initialize the variable with value of <Type>;
        // OR
        // Step 3 -> Combo of Step 1 & 2 : Declare and initialize at the same line <Type> <Name> = value of <Type>;

//       =============================================================================================
//          Note: Just declaring the variable and using them without initializing them throws error.
//       =============================================================================================

        // Primitive Data Types in Java (with size)

        // byte    -> 1 byte  (8 bits)   -> Range: -128 to 127
        // short   -> 2 bytes (16 bits)  -> Range: -32,768 to 32,767
        // int     -> 4 bytes (32 bits)  -> Range: -2^31 to 2^31-1
        // long    -> 8 bytes (64 bits)  -> Range: -2^63 to 2^63-1

        // float   -> 4 bytes (32 bits)  -> Decimal (single precision)
        // double  -> 8 bytes (64 bits)  -> Decimal (double precision)

        // char    -> 2 bytes (16 bits)  -> Single Unicode character
        // boolean -> true/false (size JVM dependent, typically 1 byte)

//----------------------------------------------------------------------------------------------------------------------
//      Step 1
        int age;

//      Step 2
        age = 23;

//      Step 3
        String name = "Vinesh S R";

        System.out.println("My Name is"+name+" and age is "+age);

    }
}
