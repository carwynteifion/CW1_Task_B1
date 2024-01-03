import java.util.Scanner;

public class Main {
    // Creates a new scanner object to read user input
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // Runs the program
        new Main().run(args);
    }

    public void run(String[] args) {
        /*
        This is the core program. First, displayWelcome() is run. Then, the user inputs
        for fullName, employeeAge and employeeNumber are collected by the scanner and
        assigned appropriately. These values are then passed through the pinGenerator.
        */
        displayWelcome();
        String fullName = getFullName();
        int employeeAge = getEmployeeAge();
        int employeeNumber = getEmployeeNumber();
        pinGenerator(employeeNumber, fullName, employeeAge);
    }

    // Prints welcome message to the user
    private static void displayWelcome() {
        System.out.println("Welcome to USW Employee Management System");
    }

    // Prompts user to input full name and assigns input to a new String
    private String getFullName() {
        System.out.println("Please enter your full name:");
        String fullName = scanner.nextLine();

        /* Ensures fullName user input only consists of letters and spaces, is not an empty string,
        and contains a space which is not at the start or end of the string, before proceeding.
        Prints appropriate error messages if any of the above is true. */
        while (
                !fullName.matches("^[-'a-zA-ZÀ-ÿ\\s]*$") ||
                !fullName.contains(" ") ||
                fullName.startsWith(" ") ||
                fullName.endsWith(" ")
        ) {
            if (!fullName.matches("^[-'a-zA-ZÀ-ÿ\\s]*$")) {
                System.out.println("Names can only contain letters and spaces. Please try again.");
            } else if (fullName.isEmpty()) {
                System.out.println("Nothing entered. Please enter your full name to continue:");
            } else if (!fullName.contains(" ") || fullName.startsWith(" ") || fullName.endsWith(" ")) {
                System.out.println(
                        "Names should be separated with a space and not begin or end with a space. Please try again."
                );
            }
            fullName = scanner.nextLine();
        }
        return fullName;
    }

    // Prompts user to input age; also checks value entered is an int between 18 and 100.
    private int getEmployeeAge() {
        int employeeAge;
        do {
            System.out.println("Please enter your age (18-100):");
            if (scanner.hasNextInt()) {
                employeeAge = scanner.nextInt();
                if (employeeAge >= 18 && employeeAge <= 100) {
                    break;
                } else {
                    System.out.println("Age must be between 18 and 100. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter your age as an integer (18-100).");
                scanner.next();
            }
        } while (true);
        return employeeAge;
    }

    // Prompts user to input employee number; also checks value entered is an int.
    private int getEmployeeNumber() {
        int employeeNumber;
        System.out.println("Please enter your employee number:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter employee number using numeric characters only (0-9).");
            scanner.next();
        }
        employeeNumber = scanner.nextInt();
        return employeeNumber;
    }

    // Calculates the pseudo-random PIN.
    private static void pinGenerator(int employeeNumber, String fullName, int employeeAge) {
        // Ensures employee number is displayed as eight characters using string formatting.
        String eightDigitEmployeeNumber = String.format("%08d", employeeNumber);

        // Displays message to user to confirm the PIN is being generated.
        System.out.println("Generating PIN...");

        // Splits value of fullName wherever there is a space into a new String array
        String[] nameSplit = fullName.split(" ");

        // Assigns the value of the array's first item to a new String
        String firstName = nameSplit[0];

        // Calculates PIN by multiplying the length of firstName by employeeAge.
        int pin = firstName.length() * employeeAge;

        // Returns the remainder of the pin's value when divided by 10000 to ensure the pin is always four digits
        pin %= 10000;

        // Converts calculated PIN to 4 digit string using string formatting
        String fourDigitPin = String.format("%04d", pin);

        // Confirms user inputs and account creation, and provides PIN to user
        System.out.println(
                "Hi " + fullName + ". Your employee account "
                        + eightDigitEmployeeNumber + " has been created. Your unique PIN is " + fourDigitPin + "."
        );
    }
}