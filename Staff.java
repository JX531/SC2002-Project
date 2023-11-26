import java.util.ArrayList;

/**
 * Staff class represents staff in system
 */
public class Staff extends User {

    /**
     * List of camps that staff owns
     */
    private ArrayList<Camp> campsOwned;

    /** Constructs a new staff with the following attributes
     * @param name name of staff
     * @param userID ID of staff, which is their email dropping the @e.ntu.edu.sg
     * @param faculty faculty of staff
     */
    // Constructor
    public Staff(String name, String userID, String faculty) {
        super(name, userID, faculty);
        this.campsOwned = new ArrayList<>();
    }

    /** Add camp to the list of camps owned
     * @param camp camp that is added to list
     */
    public void addCampsOwned(Camp camp){
        this.campsOwned.add(camp);
    }

    /** Remove camp from the list of camps owned
     * @param camp camp that is deleted from list
     */
    public void removeCampsOwned(Camp camp){
        this.campsOwned.remove(camp);
    }

    /** Get the list of camps owned
     * @return returns the list of camps owned
     */
    public ArrayList<Camp> getCampsOwned(){
        return this.campsOwned;
    }

    /** Menu that is called in main to provide functionality of the app
     * @param CampMasterList list of camps that exists
     */
    // Menu method for Staff
    public void menu(ArrayList<Camp> CampMasterList) {
        int choice;
        do {
            System.out.println("---------------------------------------------");
            System.out.printf("Name    : %s\n",this.getName());
            System.out.printf("UserID  : %s\n",this.getID());
            System.out.printf("Faculty : %s\n",this.getFaculty());
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
            System.out.println("9. Answer Enquiries of Own Camp");
            System.out.println("10. View Suggestions of Own Camp");
            System.out.println("11. Approve Suggestions of Own Camp");
            System.out.println("12. Generate Report of Camps");
            System.out.println("13. Generate Performance Report");
            System.out.println("---------------------------------------------");
            choice = Helper.readInt("Input an option >>");

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
                    CampManager.createCamp(this,CampMasterList);
                    break;
                case 3:
                    CampManager.editCamp(this);
                    break;
                case 4:
                    CampManager.deleteCamp(this,CampMasterList);
                    break;
                case 5:
                    CampManager.toggleCampVisibility(this);
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
                case 9: // answer enquiry
                    answerEnquiriesOfOwnCamp();
                break;
                case 10:
                    viewSuggestionsOfOwnCamp();
                    break;
                case 11://approve suggestion
                    processSuggestionsOfOwnCamp();
                    break;
                case 12:
                    generateReportOfCamps();
                    break;
                case 13:
                    generatePerformanceReport();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    /** View all camps that exists
     * @param CampMasterList list of camps that exists
     */
    private void viewAllCamps(ArrayList<Camp> CampMasterList) {
    	if (CampMasterList.isEmpty()) {
            System.out.println("There are no camps currently available.");
            return;
        }
        CampManager.listCamp(CampMasterList, true, "All");
    }

    /** 
     * View all camps owned by staff
     */
    private void viewOwnCamps() {
        if (this.campsOwned.isEmpty()) {
            System.out.println("You do not currently own any camps.");
            return;
        }

        // Using the CampManager class to list the camps
        // The second parameter 'true' is to enable filtering and sorting
        CampManager.listCamp(this.campsOwned, true, "Owned");
    }

    /** 
     * View enquiries of selected camp owned by staff
     */
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
        EnquiryManager.viewCampEnquiries(selectedCamp);
    }

    /** 
     * Answer enquiry of selected camp owned by staff
     */
    private void answerEnquiriesOfOwnCamp() {
        if (campsOwned.isEmpty()) {
            System.out.println("You do not own any camps to answer enquiries.");
            return;
        }

        // Display the list of camps owned by the staff member
        System.out.println("Select a camp to answer its enquiries:");
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
        EnquiryManager.answerEnquiry(selectedCamp);
    }


    /** 
     * Generate report of selected camp
     */
    private void generateReportOfCamps() {
    	   if (campsOwned.isEmpty()) {
    	        System.out.println("You do not own any camps to generate reports for.");
    	        return;
    	    }

    	    CampManager.listCamp(campsOwned, false,"Owned");
            int campIndex = Helper.readInt("Enter the number of the camp: ") - 1;
            if (campIndex < 0 || campIndex >= campsOwned.size()) {
                System.out.println("Invalid camp number. Please try again.");
                return;
            }
            Camp camp = campsOwned.get(campIndex);
            System.out.println("---------------------------------------------------------");
            System.out.println("Camp Report              : " + camp.getName());
            System.out.println("Location                 : " + camp.getLocation());
            System.out.println("Dates                    : " + camp.getStartDate() + " to " + camp.getEndDate());
            System.out.println("Registration End Date    : " + camp.getRegisterDate());
            System.out.println("Number of Slots          : " + camp.getSlots());
            System.out.println("Committee Slots Remaining: " + camp.getRemainingCommittee());
            CampManager.generateCampReport(camp);
    	}

    /** 
     * Generate performance report of selected camp
     */
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

    /** 
     * View suggestion of selected camp owned by staff
     */
        private void viewSuggestionsOfOwnCamp() {
            if (this.getCampsOwned().isEmpty()) {
                System.out.println("You do not own any camps to view suggestions.");
                return;
            }
    
            // Display the list of camps owned by the staff member
            System.out.println("Select a camp to view its suggestions:");
            for (int i = 0; i < this.getCampsOwned().size(); i++) {
                System.out.printf("%d. %s\n", i + 1, this.getCampsOwned().get(i).getName());
            }
    
            // Get the index of the camp to view suggestions
            int campIndex = Helper.readInt("Enter the number of the camp: ") - 1;
    
            // Check if the index is valid
            if (campIndex < 0 || campIndex >= this.getCampsOwned().size()) {
                System.out.println("Invalid camp number. Please try again.");
                return;
            }
    
            // Get the selected camp
            Camp selectedCamp = this.getCampsOwned().get(campIndex);
    
            SuggestionManager.viewSuggestionsofCamp(selectedCamp);
        }

    /** 
     * Process suggestion of selected camp owned by staff
     */
        private void processSuggestionsOfOwnCamp() {
            if (this.getCampsOwned().isEmpty()) {
                System.out.println("You do not own any camps to view suggestions.");
                return;
            }
    
            // Display the list of camps owned by the staff member
            System.out.println("Select a camp to view its suggestions:");
            for (int i = 0; i < this.getCampsOwned().size(); i++) {
                System.out.printf("%d. %s\n", i + 1, this.getCampsOwned().get(i).getName());
            }
    
            // Get the index of the camp to view suggestions
            int campIndex = Helper.readInt("Enter the number of the camp: ") - 1;
    
            // Check if the index is valid
            if (campIndex < 0 || campIndex >= this.getCampsOwned().size()) {
                System.out.println("Invalid camp number. Please try again.");
                return;
            }
    
            // Get the selected camp
            Camp selectedCamp = this.getCampsOwned().get(campIndex);
    
            SuggestionManager.viewSuggestionsofCamp(selectedCamp);
            SuggestionManager.processSuggestionsofCamp(selectedCamp);
        }
}
