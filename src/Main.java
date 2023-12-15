import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Creates a new scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prints welcome message
        System.out.println("Welcome to USW Employee Management System");

        // Prompts user to input full name
        System.out.println("Please enter your full name:");
        String fullName = scanner.nextLine();

        /* Ensures fullName user input only consists of letters and spaces, is not an empty string,
        and contains a space which is not at the start or end of the string, before proceeding. */
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

        // Input age; also checks value entered is an int that is between 18 and 100.
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

        // Input employee number; also checks value entered is an int
        int employeeNumber;
        System.out.println("Please enter your employee number:");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter employee number using numeric characters only (0-9).");
            scanner.next();
        }
        employeeNumber = scanner.nextInt();

        // Ensures employee number is displayed as eight characters
        String eightDigitEmployeeNumber = String.format("%08d", employeeNumber);

        // Calculate pseudo-random PIN.
        System.out.println("Generating PIN...");
        String[] nameSplit = fullName.split(" ");
        String firstName = nameSplit[0];
        int pin = firstName.length() * employeeAge;
        pin %= 10000;

        // Convert calculated PIN to 4 digit string
        String fourDigitPin = String.format("%04d", pin);

        //Provide PIN to user
        System.out.println(
                "Hi " + fullName + ". Your employee account "
                        + eightDigitEmployeeNumber + " has been created. Your unique PIN is " + fourDigitPin + "."
        );
    }
}