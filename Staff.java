import java.util.ArrayList;
import java.util.List;

public class Staff extends User {

    private List<Camp> campsOwned;

    public Staff(String userId, String faculty) {
        super(userId, faculty);
        this.campsOwned = new ArrayList<>();
    }

    public void addOwnedCamp(Camp camp) {
        campsOwned.add(camp);
    }

    public boolean removeOwnedCamp(Camp camp) {
        return campsOwned.remove(camp);
    }

    public List<Camp> viewOwnedCamps() {
        return new ArrayList<>(campsOwned);
    }

    public void replyToInquiry(Inquiry inquiry, String reply) {
        if (campsOwned.contains(inquiry.getCamp()) && !inquiry.isProcessed()) {
            inquiry.setReply(reply);
            inquiry.setProcessed(true);
        }
    }

    public void reviewSuggestion(Suggestion suggestion, boolean approve) {
        if (campsOwned.contains(suggestion.getCamp())) {
            suggestion.setApproved(approve);
        }
    }

    public void generateReport() {
    }

    // Menu method for Staff
    public void menu(ArrayList<Camp> CampMasterList) {
        int choice;
        do {
            System.out.println("Staff Menu:");
            System.out.println("1. Print Info");
            System.out.println("2. Create Camp");
            System.out.println("3. Edit Camp");
            System.out.println("4. Delete Camp");
            System.out.println("5. Toggle Camp Visibility");
            System.out.println("6. View All Camps");
            System.out.println("7. View Own Camps");
            System.out.println("8. View Enquiries of Own Camp");
            System.out.println("9. View Suggestions of Own Camp");
            System.out.println("10. Generate Report of Camps");
            System.out.println("11. Generate Performance Report");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    printInfo();
                    break;
                case 2:
                    createCamp(CampMasterList);
                    break;
                case 3:
                    editCamp(CampMasterList);
                    break;
                case 4:
                    deleteCamp(CampMasterList);
                    break;
                case 5:
                    toggleCampVisibility(CampMasterList);
                    break;
                case 6:
                    viewAllCamps(CampMasterList);
                    break;
                case 7:
                    viewOwnCamps();
                    break;
                case 8:
                    viewEnquiriesOfOwnCamp();
                    break;
                case 9:
                    viewSuggestionsOfOwnCamp();
                    break;
                case 10:
                    generateReportOfCamps();
                    break;
                case 11:
                    generatePerformanceReport();
                    break;
                case 12:
                    System.out.println("Exiting staff menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 12);
    }

    private void printInfo() {
        System.out.println("User ID: " + this.getID());
        System.out.println("Faculty: " + this.getFaculty());
        // Print any additional information if needed
    }

    private void createCamp(ArrayList<Camp> CampMasterList) {
    	Scanner scanner = new Scanner(System.in);

        // Gather camp details from the staff member
        System.out.print("Enter camp name: ");
        String name = scanner.nextLine();

        System.out.print("Enter user group: ");
        String userGroup = scanner.nextLine();

        System.out.print("Enter camp location: ");
        String location = scanner.nextLine();

        System.out.print("Enter camp description: ");
        String description = scanner.nextLine();

        System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Enter end date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Enter registration end date (YYYY-MM-DD): ");
        LocalDate registerDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.print("Enter the number of slots available: ");
        int slots = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline

        // Own school status
        System.out.print("Is this camp only for your own school? (yes/no): ");
        String ownSchoolInput = scanner.nextLine();
        Boolean ownSchool = ownSchoolInput.equalsIgnoreCase("yes");

        // Create the camp object
        Camp newCamp = new Camp(name, userGroup, location, description, startDate, endDate, registerDate, slots, this, ownSchool);

        // Add the new camp to the master list and the staff's list of owned camps
        CampMasterList.add(newCamp);
        this.campsOwned.add(newCamp);

        System.out.println("New camp created successfully!");
    }

    private void editCamp(ArrayList<Camp> CampMasterList) {
        Scanner scanner = new Scanner(System.in);

        // Display the list of camps owned by the staff member
        System.out.println("Select a camp to edit:");
        for (int i = 0; i < campsOwned.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, campsOwned.get(i).getName());
        }

        // Get the index of the camp to edit
        System.out.print("Enter the number of the camp you wish to edit: ");
        int campIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over

        // Check if the index is valid
        if (campIndex < 1 || campIndex > campsOwned.size()) {
            System.out.println("Invalid camp number. Please try again.");
            return;
        }

        // Get the selected camp
        Camp selectedCamp = campsOwned.get(campIndex - 1);

        // Present options for what to edit
        System.out.println("What would you like to edit?");
        System.out.println("1. Name");
        System.out.println("2. Description");
        System.out.println("3. Location");
        System.out.println("4. Start Date");
        System.out.println("5. End Date");
        System.out.println("6. Registration End Date");
        System.out.println("7. Number of Slots");
        System.out.println("8. Own School Status");
        System.out.println("9. Visibility");
        System.out.print("Enter your choice: ");
        int editChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over

        // Process the editing based on user choice
        switch (editChoice) {
            case 1:
                System.out.print("Enter the new name: ");
                selectedCamp.setname(scanner.nextLine());
                break;
            case 2:
                System.out.print("Enter the new description: ");
                selectedCamp.setDescription(scanner.nextLine());
                break;
            case 3:
                System.out.print("Enter the new location: ");
                selectedCamp.setLocation(scanner.nextLine());
                break;
            case 4:
                System.out.print("Enter the new start date (YYYY-MM-DD): ");
                selectedCamp.setStartDate(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE));
                break;
            case 5:
                System.out.print("Enter the new end date (YYYY-MM-DD): ");
                selectedCamp.setEndDate(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE));
                break;
            case 6:
                System.out.print("Enter the new registration end date (YYYY-MM-DD): ");
                selectedCamp.setRegisterDate(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE));
                break;
            case 7:
                System.out.print("Enter the new number of slots: ");
                selectedCamp.setSlots(scanner.nextInt());
                scanner.nextLine(); // Consume the newline left-over
                break;
            case 8:
                System.out.print("Is this camp only for your own school? (yes/no): ");
                selectedCamp.setOwnSchool(scanner.nextLine().equalsIgnoreCase("yes"));
                break;
            case 9:
                System.out.print("Should the camp be visible? (yes/no): ");
                selectedCamp.setVisibility(scanner.nextLine().equalsIgnoreCase("yes"));
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        System.out.println("Camp has been updated successfully.");
    }

    private void deleteCamp(ArrayList<Camp> CampMasterList) {
    	Scanner scanner = new Scanner(System.in);

        // Display the list of camps owned by the staff member
        System.out.println("Select a camp to delete:");
        if (campsOwned.isEmpty()) {
            System.out.println("You do not own any camps to delete.");
            return;
        }
        
        for (int i = 0; i < campsOwned.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, campsOwned.get(i).getName());
        }

        // Get the index of the camp to delete
        System.out.print("Enter the number of the camp you wish to delete: ");
        int campIndex = scanner.nextInt();

        // Validate the index
        if (campIndex < 1 || campIndex > campsOwned.size()) {
            System.out.println("Invalid camp number. Please try again.");
            return;
        }

        // Confirm deletion
        scanner.nextLine(); // Consume the newline left-over
        System.out.println("Are you sure you want to delete this camp? (yes/no)");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (!confirmation.equals("yes")) {
            System.out.println("Camp deletion canceled.");
            return;
        }

        // Delete the camp
        Camp campToDelete = campsOwned.remove(campIndex - 1);
        CampMasterList.remove(campToDelete);
        System.out.println("Camp deleted successfully: " + campToDelete.getName());
    }

    private void toggleCampVisibility(ArrayList<Camp> CampMasterList) {
        Scanner scanner = new Scanner(System.in);

        // Display the list of camps owned by the staff member
        System.out.println("Select a camp to toggle its visibility:");
        if (campsOwned.isEmpty()) {
            System.out.println("You do not own any camps to toggle visibility.");
            return;
        }

        for (int i = 0; i < campsOwned.size(); i++) {
            Camp camp = campsOwned.get(i);
            System.out.printf("%d. %s (Currently %s)\n", i + 1, camp.getName(), camp.getVisibility() ? "Visible" : "Hidden");
        }

        // Get the index of the camp to toggle visibility
        System.out.print("Enter the number of the camp: ");
        int campIndex = scanner.nextInt();

        // Validate the index
        if (campIndex < 1 || campIndex > campsOwned.size()) {
            System.out.println("Invalid camp number. Please try again.");
            return;
        }

        // Toggle the visibility
        Camp selectedCamp = campsOwned.get(campIndex - 1);
        selectedCamp.setVisibility(!selectedCamp.getVisibility());
        System.out.println("Camp visibility toggled. The camp is now " + (selectedCamp.getVisibility() ? "visible" : "hidden") + ".");
    }

    private void viewAllCamps(ArrayList<Camp> CampMasterList) {
        // Display all camps in CampMasterList
    }

    private void viewOwnCamps() {
        // Display camps from campsOwned
    }

    private void viewEnquiriesOfOwnCamp() {
        // Display enquiries for a selected camp from campsOwned
    }

    private void viewSuggestionsOfOwnCamp() {
        // Display suggestions for a selected camp from campsOwned
    }

    private void generateReportOfCamps() {
        // Generate a report of all the camps, perhaps detailing their attendees and statuses
    }

    private void generatePerformanceReport() {
        // Generate a performance report for camps, could detail the participation or feedback
    }

