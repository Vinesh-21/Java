import java.util.Scanner;

public class Main {

    public static void main(String []args){

//        variables();
//        userInput();
        operators();

    }

    // ==========================================================
    // Variables Method
    // ==========================================================
    public static void variables (){

        // Variables - Primitive and Reference

        // Primitive - byte, short, int, long, float, double, char, boolean
        // Primitive -> Stores actual value

        // Reference - Strings, Arrays, Lists, Objects
        // Reference -> Stores memory address (object stored in Heap)

        // Primitive Data Types in Java (with size)

        // byte    -> 1 byte  (8 bits)
        // short   -> 2 bytes (16 bits)
        // int     -> 4 bytes (32 bits)
        // long    -> 8 bytes (64 bits)
        // float   -> 4 bytes (32 bits)
        // double  -> 8 bytes (64 bits)
        // char    -> 2 bytes (16 bits, Unicode)
        // boolean -> true/false (JVM dependent size)

        // Step 1: Declare
        int age;

        // Step 2: Initialize
        age = 23;

        // Step 3: Declare + Initialize
        String name = "Vinesh S R";

        System.out.println("My Name is " + name + " and age is " + age);
    }


    // ==========================================================
    // User Input Method
    // ==========================================================
    public static void userInput(){

        // Scanner is used to take input from user
        Scanner sc = new Scanner(System.in);

        System.out.println("What is your name?");
        String input = sc.nextLine();

        System.out.println("Hi there " + input + "!");
        sc.close();
    }


    // ==========================================================
    // Operators Method
    // ==========================================================
    public static void operators(){

        // Operators in Java

        // 1️⃣ Arithmetic Operators
        // +  Addition
        // -  Subtraction
        // *  Multiplication
        // /  Division
        // %  Modulus (Remainder)

        int a = 10;
        int b = 5;

        System.out.println("Arithmetic Operators:");
        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b));
        System.out.println("Modulus: " + (a % b));


        // 2️⃣ Assignment Operators
        // =   Assign
        // +=  Add and assign
        // -=  Subtract and assign
        // *=  Multiply and assign
        // /=  Divide and assign

        int x = 20;
        x += 10;  // x = x + 10

        System.out.println("\nAssignment Operator:");
        System.out.println("Value of x after += 10: " + x);


        // 3️⃣ Comparison (Relational) Operators
        // ==  Equal to
        // !=  Not equal to
        // >   Greater than
        // <   Less than
        // >=  Greater than or equal
        // <=  Less than or equal

        System.out.println("\nComparison Operators:");
        System.out.println("Is a == b? " + (a == b));
        System.out.println("Is a != b? " + (a != b));
        System.out.println("Is a > b? " + (a > b));
        System.out.println("Is a < b? " + (a < b));


        // 4️⃣ Logical Operators
        // &&  AND
        // ||  OR
        // !   NOT

        boolean condition1 = true;
        boolean condition2 = false;

        System.out.println("\nLogical Operators:");
        System.out.println("condition1 && condition2: " + (condition1 && condition2));
        System.out.println("condition1 || condition2: " + (condition1 || condition2));
        System.out.println("!condition1: " + (!condition1));


        // 5️⃣ Increment & Decrement
        // ++  Increase by 1
        // --  Decrease by 1

        int num = 5;
        num++;  // num = num + 1

        System.out.println("\nIncrement Operator:");
        System.out.println("Value after increment: " + num);
    }
}
