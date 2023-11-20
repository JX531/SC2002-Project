import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Helper {
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    // Reads a string from the user and checks if it's a valid string
    public static String readString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
        } while (input.trim().isEmpty());  // Repeat until a non-empty string is entered
        return input;
    }

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
                System.out.println("Please enter in the format dd/mm/yyyy.");
            }
        }
        return date;
    }

    // Lists camps with different filter options
    public static void listCamp(ArrayList<Camp> camps) {
        System.out.println("Filter options:");
        System.out.println("1. By Location");
        System.out.println("2. By Start Date");
        System.out.print("Choose a filter option: ");
        int filterOption = readInt("");
        switch (filterOption) {
            case 1:
                String location = readString("Enter location: ");
                camps.stream()
                        .filter(camp -> camp.getLocation().equalsIgnoreCase(location))
                        .forEach(Helper::printCamp);
                break;
            case 2:
                LocalDate startDate = readDate("Enter start date (dd-mm-yyyy): ");
                camps.stream()
                        .filter(camp -> camp.getStarDate().equals(startDate) || camp.getStarDate().isAfter(startDate))
                        .forEach(Helper::printCamp);
                break;
            default:
                System.out.println("Invalid filter option.");
                break;
        }
    }

    // Prints the details of a camp
    private static void printCamp(Camp camp) {
        System.out.println("Camp Name: " + camp.getName());
        System.out.println("Location: " + camp.getLocation());
        System.out.println("Start Date: " + dateFormatter.format(camp.getStarDate()));
        // Other details can be added here
    }
    
    // Allows a student to view the details of a camp
    public static void campDetails(Camp camp) {
        printCamp(camp);
        // Additional details specific to the student's perspective can be added here
    }

    // Selection of a camp from a list
    public static Camp selectCamp(ArrayList<Camp> camps) {
        for (int i = 0; i < camps.size(); i++) {
            System.out.println((i + 1) + ": " + camps.get(i).getName());
        }
        int index = readInt("Enter the camp number: ");
        if (index > 0 && index <= camps.size()) {
            return camps.get(index - 1); // Adjust for zero-based indexing
        } else {
            System.out.println("Invalid selection.");
            return null;
        }
    
    }
}
