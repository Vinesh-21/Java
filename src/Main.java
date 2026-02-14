import java.util.Scanner;

public class Main {

    public static void main(String []args){

//        variables();
//        userInput();
//        operators();
//        mathLibrary();
//        stringMethods();
//        conditionalStatements();
//        switchStatement();
        enhancedSwitchStatement();

    }

    // ==========================================================
    // Variables Method
    // ==========================================================
    public static void variables (){

        int age = 23;
        String name = "Vinesh S R";

        System.out.println("My Name is " + name + " and age is " + age);
    }


    // ==========================================================
    // User Input Method
    // ==========================================================
    public static void userInput(){

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

        int a = 10;
        int b = 5;

        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b));
        System.out.println("Modulus: " + (a % b));
    }


    // ==========================================================
    // Math Library Method
    // ==========================================================
    public static void mathLibrary(){

        // Math class provides mathematical operations

        int num1 = 25;
        int num2 = 10;

        System.out.println("Maximum: " + Math.max(num1, num2));
        System.out.println("Minimum: " + Math.min(num1, num2));
        System.out.println("Square Root: " + Math.sqrt(64));
        System.out.println("Power: " + Math.pow(2, 3));
        System.out.println("Absolute Value: " + Math.abs(-20));
        System.out.println("Random Number (0-1): " + Math.random());
    }


    // ==========================================================
    // String Methods and Substring Method
    // ==========================================================
    public static void stringMethods(){

        String text = "Java Programming";

        // Length of string
        System.out.println("Length: " + text.length());

        // Convert to upper case
        System.out.println("Uppercase: " + text.toUpperCase());

        // Convert to lower case
        System.out.println("Lowercase: " + text.toLowerCase());

        // Check if contains word
        System.out.println("Contains 'Java'? " + text.contains("Java"));

        // Index of character
        System.out.println("Index of 'P': " + text.indexOf("P"));

        // Substring (start index)
        System.out.println("Substring from index 5: " + text.substring(5));

        // Substring (start and end index)
        System.out.println("Substring (0 to 4): " + text.substring(0, 4));
    }


    // ==========================================================
    // Conditional Statements
    // ==========================================================
    public static void conditionalStatements(){

        int age = 20;

        // Simple if
        if(age >= 18){
            System.out.println("You are an adult.");
        }

        // if-else
        if(age >= 18){
            System.out.println("Eligible to vote.");
        } else {
            System.out.println("Not eligible to vote.");
        }

        // Nested if-else
        if(age >= 18){
            if(age >= 60){
                System.out.println("Senior Citizen.");
            } else {
                System.out.println("Adult but not senior.");
            }
        } else {
            System.out.println("Minor.");
        }
    }


    // ==========================================================
    // Switch Statement (Traditional)
    // ==========================================================
    public static void switchStatement(){

        int day = 3;

        switch(day){
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Invalid day");
        }
    }


    // ==========================================================
    // Enhanced Switch Statement (Java 14+)
    // ==========================================================
    public static void enhancedSwitchStatement(){

        int day = 2;

        switch(day){
            case 1 -> System.out.println("Monday");
            case 2 -> System.out.println("Tuesday");
            case 3 -> System.out.println("Wednesday");
            default -> System.out.println("Invalid day");
        }

        // Returning value using enhanced switch
        String result = switch(day){
            case 1 -> "Start of week";
            case 2 -> "Second day";
            case 3 -> "Midweek";
            default -> "Unknown";
        };

        System.out.println("Meaning: " + result);
    }
}
