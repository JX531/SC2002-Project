import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Staff extends User {

    private ArrayList<Camp> campsOwned;
    private Scanner scanner = new Scanner(System.in);

    // Constructor
    public Staff(String userID, String faculty) {
        super(userID, faculty);
        this.campsOwned = new ArrayList<>();
    }

    // Menu method for Staff
    public void menu(ArrayList<Camp> CampMasterList) {
        int choice;
        do {
        	System.out.println("---------------------------------------------");
        	System.out.println("0.  Logout");
            System.out.println("1. Change Password");
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
            System.out.println("---------------------------------------------");
            System.out.printf("Input an option >>");
            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    break;
                case 1://change password
                String new_password = Helper.readString("Input new password : ");
                this.setPassword(new_password);
                System.out.printf("Password successfully changed\n");
                System.out.printf("You will now be logged out\n");
                choice = 0;
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
        // Gather camp details from the staff member using Helper methods
        String name = Helper.readString("Enter camp name: ");
        String userGroup = Helper.readString("Enter user group: ");
        String location = Helper.readString("Enter camp location: ");
        String description = Helper.readString("Enter camp description: ");
        LocalDate startDate = Helper.readDate("Enter start date (dd-mm-yyyy): ");
        LocalDate endDate = Helper.readDate("Enter end date (dd-mm-yyyy): ");
        LocalDate registerDate = Helper.readDate("Enter registration end date (dd-mm-yyyy): ");
        int slots = Helper.readInt("Enter the number of slots available: ");

        // Assuming the staffInCharge is the current Staff object ('this')
        Staff staffInCharge = this;

        // Own school status
        String ownSchoolInput = Helper.readString("Is this camp only for your own school? (yes/no): ");
        Boolean ownSchool = ownSchoolInput.equalsIgnoreCase("yes");

        // Create the camp object
        Camp newCamp = new Camp(name, userGroup, location, description, startDate, endDate, registerDate, slots, staffInCharge, ownSchool);

        // Add the new camp to the master list and the staff's list of owned camps
        CampMasterList.add(newCamp);
        this.campsOwned.add(newCamp);

        System.out.println("New camp created successfully!");
    }


    private void editCamp(ArrayList<Camp> CampMasterList) {
        if (campsOwned.isEmpty()) {
            System.out.println("You do not own any camps to edit.");
            return;
        }

        // Display the list of camps owned by the staff member
        System.out.println("Select a camp to edit:");
        for (int i = 0; i < campsOwned.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, campsOwned.get(i).getName());
        }

        // Get the index of the camp to edit
        int campIndex = Helper.readInt("Enter the number of the camp you wish to edit: ") - 1;

        // Check if the index is valid
        if (campIndex < 0 || campIndex >= campsOwned.size()) {
            System.out.println("Invalid camp number. Please try again.");
            return;
        }

        // Get the selected camp
        Camp selectedCamp = campsOwned.get(campIndex);

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
        int editChoice = Helper.readInt("");

        // Process the editing based on user choice
        switch (editChoice) {
            case 1:
                selectedCamp.setname(Helper.readString("Enter the new name: "));
                break;
            case 2:
                selectedCamp.setDescription(Helper.readString("Enter the new description: "));
                break;
            case 3:
                selectedCamp.setLocation(Helper.readString("Enter the new location: "));
                break;
            case 4:
                selectedCamp.setStartDate(Helper.readDate("Enter the new start date (dd-mm-yyyy): "));
                break;
            case 5:
                selectedCamp.setEndDate(Helper.readDate("Enter the new end date (dd-mm-yyyy): "));
                break;
            case 6:
                selectedCamp.setRegisterDate(Helper.readDate("Enter the new registration end date (dd-mm-yyyy): "));
                break;
            case 7:
                selectedCamp.setSlots(Helper.readInt("Enter the new number of slots: "));
                break;
            case 8:
                String ownSchoolResponse = Helper.readString("Is this camp only for your own school? (yes/no): ");
                selectedCamp.setOwnSchool(ownSchoolResponse.equalsIgnoreCase("yes"));
                break;
            case 9:
                String visibilityResponse = Helper.readString("Should the camp be visible? (yes/no): ");
                selectedCamp.setVisibility(visibilityResponse.equalsIgnoreCase("yes"));
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
    	if (CampMasterList.isEmpty()) {
            System.out.println("There are no camps currently available.");
            return;
        }

        System.out.println("List of All Camps:");
        for (Camp camp : CampMasterList) {
            System.out.println("=========================================");
            System.out.println("Camp Name: " + camp.getName());
            System.out.println("User Group: " + camp.getUserGroup());
            System.out.println("Location: " + camp.getLocation());
            System.out.println("Description: " + camp.getDescription());
            System.out.println("Start Date: " + camp.getStarDate());
            System.out.println("End Date: " + camp.getEndDate());
            System.out.println("Registration End Date: " + camp.getRegisterDate());
            System.out.println("Slots Available: " + camp.getSlots());
            System.out.println("Committee Slots Remaining: " + camp.getRemainingCommittee());
            System.out.println("Is for Own School: " + (camp.getOwnSchool() ? "Yes" : "No"));
            System.out.println("Visibility: " + (camp.getVisibility() ? "Visible" : "Hidden"));
            System.out.println("=========================================");
        }
    }

    private void viewOwnCamps() {
        if (this.campsOwned.isEmpty()) {
            System.out.println("You do not currently own any camps.");
            return;
        }

        // Using the FilterLister class to list the camps
        // The second parameter 'true' is to enable filtering and sorting
        FilterLister.listCamp(this.campsOwned, true, "Owned");
    }

    private void viewEnquiriesOfOwnCamp() {
        if (campsOwned.isEmpty()) {
            System.out.println("You do not own any camps to view enquiries.");
            return;
        }

        // Display the list of camps owned by the staff member
        System.out.println("Select a camp to view its enquiries:");
        for (int i = 0; i < campsOwned.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, campsOwned.get(i).getName());
        }

        // Get the index of the camp to view enquiries
        int campIndex = Helper.readInt("Enter the number of the camp: ") - 1;

        // Check if the index is valid
        if (campIndex < 0 || campIndex >= campsOwned.size()) {
            System.out.println("Invalid camp number. Please try again.");
            return;
        }

        // Get the selected camp
        Camp selectedCamp = campsOwned.get(campIndex);

        // Check if there are enquiries for the selected camp
        if (selectedCamp.getEnquiries().isEmpty()) {
            System.out.println("There are no enquiries for this camp.");
            return;
        }

        // Display enquiries for the selected camp
        System.out.println("Enquiries for " + selectedCamp.getName() + ":");
        for (Enquiry enquiry : selectedCamp.getEnquiries()) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Enquiry: " + enquiry.getQuestion());
            System.out.println("Response: " + (enquiry.getAnswer() != null ? enquiry.getAnswer() : "No response yet."));
        }
    }

    private void viewSuggestionsOfOwnCamp() {
    	    if (campsOwned.isEmpty()) {
    	        System.out.println("You do not own any camps to view suggestions.");
    	        return;
    	    }

    	    // Display the list of camps owned by the staff member
    	    System.out.println("Select a camp to view its suggestions:");
    	    for (int i = 0; i < campsOwned.size(); i++) {
    	        System.out.printf("%d. %s\n", i + 1, campsOwned.get(i).getName());
    	    }

    	    // Get the index of the camp to view suggestions
    	    int campIndex = Helper.readInt("Enter the number of the camp: ") - 1;

    	    // Check if the index is valid
    	    if (campIndex < 0 || campIndex >= campsOwned.size()) {
    	        System.out.println("Invalid camp number. Please try again.");
    	        return;
    	    }

    	    // Get the selected camp
    	    Camp selectedCamp = campsOwned.get(campIndex);

    	    // Check if there are suggestions for the selected camp
    	    if (selectedCamp.getSuggestions().isEmpty()) {
    	        System.out.println("There are no suggestions for this camp.");
    	        return;
    	    }

    	    // Display suggestions for the selected camp
    	    System.out.println("Suggestions for " + selectedCamp.getName() + ":");
    	    for (Suggestion suggestion : selectedCamp.getSuggestions()) {
    	        System.out.println("---------------------------------------------------------");
    	        System.out.println("Suggestion: " + suggestion.getSuggestion());
    	        System.out.println("Created by: " + suggestion.getCreatedby().getName());
    	        System.out.println("Status: " + (suggestion.getProcessed() ? "Processed" : "Unprocessed") + ", " +
    	                           (suggestion.getApproved() ? "Approved" : "Not Approved"));
    	    }
    }
  

    private void generateReportOfCamps() {
    	   if (campsOwned.isEmpty()) {
    	        System.out.println("You do not own any camps to generate reports for.");
    	        return;
    	    }

    	    System.out.println("Generating reports for owned camps:");

    	    for (Camp camp : campsOwned) {
    	        System.out.println("---------------------------------------------------------");
    	        System.out.println("Camp Report: " + camp.getName());
    	        System.out.println("Location: " + camp.getLocation());
    	        System.out.println("Dates: " + camp.getStarDate() + " to " + camp.getEndDate());
    	        System.out.println("Registration End Date: " + camp.getRegisterDate());
    	        System.out.println("Number of Slots: " + camp.getSlots());
    	        System.out.println("Committee Slots Remaining: " + camp.getRemainingCommittee());

    	        // Attendees
    	        System.out.println("Attendees:");
    	        for (Student attendee : camp.getStudentList()) {
    	            System.out.println(" - " + attendee.getName());
    	        }

    	        // Committee members
    	        System.out.println("Committee Members:");
    	        for (Student member : camp.getCommitteeList()) {
    	            System.out.println(" - " + member.getName());
    	        }

    	        // Enquiries
    	        System.out.println("Enquiries:");
    	        for (Enquiry enquiry : camp.getEnquiries()) {
    	            System.out.println(" - Question: " + enquiry.getQuestion());
    	            System.out.println("   Answer: " + (enquiry.getAnswer() != null ? enquiry.getAnswer() : "No response yet."));
    	        }

    	        // Suggestions
    	        System.out.println("Suggestions:");
    	        for (Suggestion suggestion : camp.getSuggestions()) {
    	            System.out.println(" - Suggestion: " + suggestion.getSuggestion());
    	            System.out.println("   Status: " + (suggestion.getProcessed() ? "Processed" : "Unprocessed") +
    	                               ", " + (suggestion.getApproved() ? "Approved" : "Not Approved"));
    	        }
    	    }
    	}

    private void generatePerformanceReport() {
    	   if (campsOwned.isEmpty()) {
    	        System.out.println("You do not own any camps to generate performance reports for.");
    	        return;
    	    }

    	    System.out.println("Generating performance reports for committee members in owned camps:");

    	    for (Camp camp : campsOwned) {
    	        System.out.println("---------------------------------------------------------");
    	        System.out.println("Camp: " + camp.getName());
    	        System.out.println("Committee Performance:");

    	        // Check if there are committee members in the camp
    	        if (camp.getCommitteeList().isEmpty()) {
    	            System.out.println("No committee members in this camp.");
    	            continue;
    	        }

    	        for (Student committeeMember : camp.getCommitteeList()) {
    	            // Display the committee member's name and their points
    	            System.out.println(" - Member: " + committeeMember.getName() + ", Points: " + committeeMember.getPoint());
    	        }
    	    }
    	}


}
