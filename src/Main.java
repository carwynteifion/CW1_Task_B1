import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Creates a new scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prints welcome message to the user
        System.out.println("Welcome to USW Employee Management System");

        // Prompts user to input full name and assigns input to a new String named fullName
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

        /* Prompts user to input age; also checks value entered is an int between 18 and 100.
        If the value is an int, assigns user input to a new int, employeeAge. */
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

        /* Prompts user to input employee number; also checks value entered is an int.
        If the value is an int, assigns user input to a new int, employeeNumber. */
        int employeeNumber;
        System.out.println("Please enter your employee number:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter employee number using numeric characters only (0-9).");
            scanner.next();
        }
        employeeNumber = scanner.nextInt();

        /* Ensures employee number is displayed as eight characters using string formatting.
        The new String, eightDigitEmployeeNumber, is assigned the value of the String
        employeeNumber, formatted to eight digits by passing it through the function
        String.format().
         */
        String eightDigitEmployeeNumber = String.format("%08d", employeeNumber);

        // Calculates the pseudo-random PIN.
        // First, displays a message to the user to confirm the PIN is being generated.
        System.out.println("Generating PIN...");

        /* Then, creates a new string array, nameSplit, which is assigned the value of
        fullName after being split into array items wherever there is a space, using
        fullName.split(" ").*/
        String[] nameSplit = fullName.split(" ");

        // A new string, firstName, is assigned the value of the array's first item.
        String firstName = nameSplit[0];

        // Then, the int pin is calculated by multiplying the length of firstName by employeeAge.
        int pin = firstName.length() * employeeAge;

        /* Finally, to ensure the pin is always at most four digits, the line pin %= 10000
        returns the remainder of the pin's value when divided by 10000. */
        pin %= 10000;

        // Convert calculated PIN to 4 digit string using string formatting
        String fourDigitPin = String.format("%04d", pin);

        // Confirms user inputs and account creation, and provides PIN to user
        System.out.println(
                "Hi " + fullName + ". Your employee account "
                        + eightDigitEmployeeNumber + " has been created. Your unique PIN is " + fourDigitPin + "."
        );
    }
}