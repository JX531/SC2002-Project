import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


/**
 * Helper class that is responsible for parsing inputs to prevent errors
 */
public class Helper {
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    /**Reads string from user and checks if its a valid string
     * @param prompt prompt the user to input
     * @return returns the input if its a valid string
     */
    // Reads a string from the user and checks if it's a valid string
    public static String readString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
        } while (input.trim().isEmpty());  // Repeat until a non-empty string is entered
        return input;
    }

    /**Reads int from user and checks if its a valid string
     * @param prompt prompt the user to input
     * @return returns the input if its a valid int
     */
    // Reads an integer value from the user
    public static int readInt (String prompt) {
        int input = 0;
        boolean valid = false;
        while (!valid) {
            try{ 
                input = Integer.parseInt(readString(prompt));
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer.");
            }
        }
        return input;
    }

    /**Reads a LocalDate date from user and checks if its a valid string
     * @param prompt prompt the user to input
     * @return returns the input if its a valid date in DD-MM-YYYY format
     */
    // Reads a date input and parse it to Date object
    public static LocalDate readDate(String prompt) {
        LocalDate date = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(prompt);
                date = LocalDate.parse(scanner.nextLine(),dateFormatter);
                isValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter in the format dd-mm-yyyy.");
            }
        }
        return date;
    }

}
