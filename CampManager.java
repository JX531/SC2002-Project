import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CampManager {
    //Sort input camp list by location alphabetically
    private static void sortCampsLocation(ArrayList<Camp> camplist){
        //create comparator
        Comparator<Camp> locationComparator = Comparator.comparing(Camp::getLocation);
        //sort by comparator
        Collections.sort(camplist, locationComparator);
    }
    //Sort input camp list by name alphabetically
    private static void sortCampsName(ArrayList<Camp> camplist){
        //create comparator
        Comparator<Camp> nameComparator = Comparator.comparing(Camp::getName);
        //sort by comparator
        Collections.sort(camplist, nameComparator);
    }
    //Sort input camp list by start date with earliest first
    private static void sortCampsDate(ArrayList<Camp> camplist){
        //create comparator
        Comparator<Camp> dateComparator = Comparator.comparing(Camp::getStartDate);
        //sort by comparator
        Collections.sort(camplist, dateComparator);
    }

    private static Boolean filterCheck(Object input, Object filter){
        if (input.equals(filter)){
            return true;
        }
        else{
            return false;
        }
    }

    private static void campSortMenu(ArrayList<Camp> camps){
        //print options
        System.out.printf("---------------------------------------------------------\n");
        System.out.println("Would you like to sort the camp list?");
        System.out.println("1. Sort by Location");
        System.out.println("2. Sort by Start Date");
        System.out.println("3. Sort by Name");
        //take input
        int choice = Helper.readInt("> \n");
        //if input is in valid range
        if (choice > 0 && choice <=3){
            switch (choice){
                case 1:
                //sort by location
                sortCampsLocation(camps);
                System.out.println("Sorted by Location \n");
                break;
                case 2:
                //sort by date
                sortCampsDate(camps);
                System.out.println("Sorted by Start Date \n");
                break;
                case 3:
                //sort by name alphabetically
                sortCampsName(camps);
                System.out.println("Sorted by Name \n");
                break;
            }
        }
        else{System.out.println("Invalid Choice");}
    }

    private static int campFilterMenu(){
        //print options
        int choice = -1;
        while (choice < 1 || choice > 4){
            System.out.printf("---------------------------------------------------------\n");
            System.out.println("Would you like to filter the camp list?");
            System.out.println("1. Filter by Name");
            System.out.println("2. Filter by Location");
            System.out.println("3. Filter by Start Date");
            System.out.println("4. No");
            choice = Helper.readInt("> ");
            if (choice < 1 || choice >4){
                System.out.println("Invalid option");
            }
        }
        return choice;
    }

    //print each camp and its index
    public static void printCampDetails(int index, Camp eachcamp){
        System.out.printf("---------------------------------------------------------\n");
            System.out.printf("%d.\n",index);
            System.out.printf("Name                      : %s\n",eachcamp.getName());
            System.out.printf("Faculty                   : %s\n",eachcamp.getUserGroup());
            System.out.printf("Location                  : %s\n",eachcamp.getLocation());
            System.out.printf("Attendee Slots Remaining  : %s\n",eachcamp.getSlots());
            System.out.printf("Committee Slots Remaining : %s\n",eachcamp.getRemainingCommittee());
            System.out.printf("Starts                    : %s\n",eachcamp.getStartDate());
            System.out.printf("Ends                      : %s\n",eachcamp.getEndDate());
            System.out.printf("Register before           : %s\n",eachcamp.getRegisterDate());
            System.out.printf("Description               : %s\n",eachcamp.getDescription());
            System.out.printf("Visible                   : %s\n",eachcamp.getVisibility());
    }

    //overloaded to not include index, use for single camp instead of multiple
    public static void printCampDetails(Camp eachcamp){
        System.out.printf("---------------------------------------------------------\n");
            System.out.printf("Name                      : %s\n",eachcamp.getName());
            System.out.printf("Faculty                   : %s\n",eachcamp.getUserGroup());
            System.out.printf("Location                  : %s\n",eachcamp.getLocation());
            System.out.printf("Attendee Slots Remaining  : %s\n",eachcamp.getSlots());
            System.out.printf("Committee Slots Remaining : %s\n",eachcamp.getRemainingCommittee());
            System.out.printf("Starts                    : %s\n",eachcamp.getStartDate());
            System.out.printf("Ends                      : %s\n",eachcamp.getEndDate());
            System.out.printf("Register before           : %s\n",eachcamp.getRegisterDate());
            System.out.printf("Description               : %s\n",eachcamp.getDescription());
            System.out.printf("Visible                   : %s\n",eachcamp.getVisibility());
    }

    // Lists input camp list, with option to include filters based on boolean filter
    public static void listCamp(ArrayList<Camp> camps, Boolean filter) {
        //if including filter, call filter menu
        int filterType = -1;
        String filterString = "'wadawdwadada'";
        LocalDate filterdate = LocalDate.of(2042, 12, 2);
        if (filter){
            //filter type
            filterType = campFilterMenu();
            //if filter type is string, get filterstring
            if (filterType == 1 || filterType == 2){
                filterString = Helper.readString("Input Filter > ");
            }
            //if filter type is date, get filter date
            if (filterType == 3){
                filterdate = Helper.readDate("Input Filter >");
            }
        }
        else{
            //sort if not filtering
            campSortMenu(camps);
        }
        //for each camp in the camp list
        int i = 1;
        System.out.printf("---------------------------------------------------------\n");
        System.out.printf("Camps\n");
        for (Camp eachcamp:camps){
            //print camp details with index for each filter type
            if (filterType == 1){
                if(filterCheck(filterString, eachcamp.getName())){
                    printCampDetails(i,eachcamp);
                    //increment index
                    i++;
                }
            }
            if (filterType == 2){
                if(filterCheck(filterString, eachcamp.getLocation())){
                    printCampDetails(i,eachcamp);
                    i++;
                }
            }
            if (filterType == 3){
                if(filterCheck(filterdate, eachcamp.getStartDate())){
                    printCampDetails(i,eachcamp);
                    i++;
                }
            }
            //no filter
            if (filterType == 4 || filterType == -1){
                printCampDetails(i,eachcamp);
                i++;
            }
            
        }
    }

    //overload to allow prefix
    public static void listCamp(ArrayList<Camp> camps, Boolean filter,String prefix) {
        //if including filter, call filter menu
        int filterType = -1;
        String filterString = "'wadawdwadada'";
        LocalDate filterdate = LocalDate.of(2022, 12, 2);
        if (filter){
            //filter type
            filterType = campFilterMenu();
            if (filterType == 1 || filterType == 2){
                filterString = Helper.readString("Input Filter > ");
            }
            if (filterType == 3){
                filterdate = Helper.readDate("Input Filter >");
            }
        }
        else{
            //sort if not filtering
            campSortMenu(camps);
        }
        //for each camp in the camp list
        int i = 1;
        System.out.printf("---------------------------------------------------------\n");
        System.out.printf("%s Camps\n",prefix);
        for (Camp eachcamp:camps){
            //print camp details with index
            if (filterType == 1){if(filterCheck(filterString, eachcamp.getName())){printCampDetails(i,eachcamp);i++;}}
            if (filterType == 2){if(filterCheck(filterString, eachcamp.getLocation())){printCampDetails(i,eachcamp);i++;}}
            if (filterType == 3){if(filterCheck(filterdate, eachcamp.getStartDate())){printCampDetails(i,eachcamp);i++;}}
            if (filterType == 4 || filterType == -1){printCampDetails(i,eachcamp);i++;}
        }
    }

    //Sort input student list by name alphabetically
    private static void sortStudentName(ArrayList<Student> studentList){
        //create comparator
        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
        //sort by comparator
        Collections.sort(studentList, nameComparator);
    }
    //Sort input committee student list by point in decreasing order
    private static void sortCommitteePoint(ArrayList<Student> committeeList){
        //create comparator
        Comparator<Student> poinComparator = Comparator.comparing(Student::getPoint);
        //sort by comparator
        Collections.sort(committeeList, poinComparator);
    }
    
    //prints details of student and their index ( in a list of students )
    private static void printStudentDetails(int index, Student eachStudent){
        System.out.printf("%d.\n",index);
        System.out.printf("Name                      : %s\n",eachStudent.getName());
        System.out.printf("Faculty                   : %s\n",eachStudent.getFaculty());
        if (eachStudent.getCommitteeOf() != null){
            System.out.printf("Role                      : %s\n","Committee Member");
            System.out.printf("Points                    : %s\n",eachStudent.getPoint());
        }
        else{
            System.out.printf("Role                      : %s\n","Attendee");
        }
    }

    //generate camp report
    public static void generateCampReport(Camp camp){
        //print options to generate only for committee members / attendees only / all participants
        System.out.printf("---------------------------------------------------------\n");
        System.out.println("Generate report for \n");
        System.out.println("1. All participants \n");
        System.out.println("2. Attendees only \n");
        System.out.println("3. Committee only\n");
        //get input
        int choice = Helper.readInt("> \n");
        //default is to sort by name alphabetically
        sortStudentName(camp.getCommitteeList());
        sortStudentName(camp.getStudentList());
        //if input is in valid range
        if (choice > 0 && choice <=3){
            switch (choice){
                case 1://list all participants
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("All Participants\n");
                int i = 1;
                //prints committee members at top first
                //for each student in the committee
                for (Student eachStudent:camp.getCommitteeList()){
                    //print details
                    printStudentDetails(i, eachStudent);
                    //increment index
                    i++;
                }
                //print attendees next
                //for each student in the attendee student list
                for (Student eachStudent:camp.getStudentList()){
                    //print details
                    printStudentDetails(i, eachStudent);
                    //increment index
                    i++;
                }
                break;
                case 2://list only attendees
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("Attendees \n");
                i = 1;
                //for each student in the attendee student list
                for (Student eachStudent:camp.getStudentList()){
                    //print details
                    printStudentDetails(i, eachStudent);
                    //increment index
                    i++;
                }
                break;
                case 3://list committee members only
                //option to sort them by points in decreasing order
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("Sort by points? \n");
                System.out.println("1. Yes\n");
                System.out.println("2. No\n");
                //get input
                int pointSort = Helper.readInt("> \n");
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("Committee Members \n");
                //if input is in valid range
                if (pointSort>0 && pointSort<=2){
                    //choose to sort by points
                    if (pointSort == 1){
                        //sort list by points
                        sortCommitteePoint(camp.getCommitteeList());
                        System.out.println("Sorted by Points \n");
                    }
                    i = 1;
                    //for each member in committee list
                    for (Student eachStudent:camp.getCommitteeList()){
                        //print details
                        printStudentDetails(i, eachStudent);
                        //increment index
                        i++;
                    }
                }
                else{System.out.println("Invalid option\n");}
                break;
            }
        }
        else{System.out.println("Invalid option\n");}
    }

    public static void createCamp(Staff user,ArrayList<Camp> CampMasterList) {
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
        Staff staffInCharge = user;

        // Own school status
        String ownSchoolInput = Helper.readString("Is this camp only for your own school? (yes/no): ");
        Boolean ownSchool = ownSchoolInput.equalsIgnoreCase("yes");

        // Create the camp object
        Camp newCamp = new Camp(name, userGroup, location, description, startDate, endDate, registerDate, slots, staffInCharge, ownSchool);

        // Add the new camp to the master list and the staff's list of owned camps
        CampMasterList.add(newCamp);
        user.addCampsOwned(newCamp);

        System.out.println("New camp created successfully!");
    }

    public static void editCamp(Staff user) {
        if (user.getCampsOwned().isEmpty()) {
            System.out.println("You do not own any camps to edit.");
            return;
        }

        // Display the list of camps owned by the staff member
        System.out.println("Select a camp to edit:");
        for (int i = 0; i < user.getCampsOwned().size(); i++) {
            System.out.printf("%d. %s\n", i + 1, user.getCampsOwned().get(i).getName());
        }

        // Get the index of the camp to edit
        int campIndex = Helper.readInt("Enter the number of the camp you wish to edit: ") - 1;

        // Check if the index is valid
        if (campIndex < 0 || campIndex >= user.getCampsOwned().size()) {
            System.out.println("Invalid camp number. Please try again.");
            return;
        }

        // Get the selected camp
        Camp selectedCamp = user.getCampsOwned().get(campIndex);
        if (selectedCamp.getCommitteeList().size() != 0 || selectedCamp.getStudentList().size()!= 0){
            System.out.println("Students already registered for camp, unable to edit");
            return;
        }
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
                selectedCamp.setName(Helper.readString("Enter the new name: "));
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
    public static void withdrawnFromCamp(Student user){
        System.out.printf("---------------------------------------------------------\n");
                int campindex = Helper.readInt("Select camp to withdrawn from : ");
                
                if (campindex > 0 && campindex <= user.getRegisteredCamps().size()){
                    Camp target = user.getRegisteredCamps().get(campindex-1);
                    if (user.getCommitteeOf() == target){
                        System.out.printf("---------------------------------------------------------\n");
                        System.out.printf("You cannot withdrawn from a camp you are a committee member of\n");
                    }
                    else{
                        target.removeStudent(user); // Remove student from camp first
                        user.addWithdrawnCamp(target); // add camp to withdrawn list
                        user.removeRegisteredCamp(target); // remove camp from registered list
                        System.out.printf("Successfully withdrawn from camp : %s\n",target.getName());
                    }
                }
                else{System.out.println("Invalid choice");}
    }
     //returns true if clash, false if no clash
     private static Boolean checkClash(Student user, Camp camp){
        for (Camp eachcamp : user.getRegisteredCamps()){
            //target camp ends before registered camp starts or target camp starts after registered camp ends
            if (camp.getEndDate().isBefore(eachcamp.getStartDate()) || camp.getStartDate().isAfter(eachcamp.getEndDate())){
                // no clash, check next camp
                continue;
            }
            else{
                // clash, return true
                return true;
            }
        }

        //checked all camps and no clash, return false
        return false;
    }
    public static void registerForCamp(Student user, ArrayList<Camp> available){
        System.out.printf("---------------------------------------------------------\n");
                //Select camp to register for
                int campindex = Helper.readInt("Select Camp to register for : ");               
                if (campindex > 0 && campindex <= available.size()){
                    //Register as attendee or committee
                    Camp target = available.get(campindex-1);
                    if (LocalDate.now().isAfter(target.getRegisterDate())){System.out.println("The registration date for this camp has passed");}
                    else{
                        if(user.getWithdrawnCamps().contains(target) || user.getRegisteredCamps().contains(target)){System.out.println("You have already registered or withdrawn from this camp");}
                        else{
                            if(checkClash(user,target)){System.out.println("The camp clashes with your registered camps");}
                            else{
                                System.out.printf("---------------------------------------------------------\n");
                                System.out.printf("1. Attendee\n");
                                System.out.printf("2. Committee\n");
                                System.out.printf("---------------------------------------------------------\n");
                                int answer = Helper.readInt("Register as : ");
                                if (answer >= 1 && answer <= 2){
                                    switch(answer){
                                    case 1://as attendeee
                                    if (target.getSlots() == 0){System.out.println("Attendee slots for this camp are full");}
                                    else{
                                        user.addRegisteredCamp(target);
                                        target.addStudent(user);
                                        System.out.printf("Successfully registered as attendee for : %s\n",target.getName());
                                    }
                                    break;
                                    case 2://as committee
                                    if (user.getCommitteeOf() != null){System.out.println("You are already in the committee of a camp");}
                                    else{
                                        if (target.getRemainingCommittee() == 0){System.out.println("Committee slots for this camp are full");}
                                        else{
                                            user.setCommitteeOf(target);
                                            target.addCommittee(user);
                                            System.out.printf("Successfully registered as committee member for : %s\n",target.getName());
                                        }
                                    }
                                    break;
                                    }   
                                }
                                else{System.out.println("Invalid choice");}
                            }
                        }
                    }
                    
                    
                } 
                else{System.out.println("Invalid choice");}
    }
    public static void deleteCamp(Staff user, ArrayList<Camp> CampMasterList) {
    	Scanner scanner = new Scanner(System.in);

        // Display the list of camps owned by the staff member
        System.out.println("Select a camp to delete:");
        if (user.getCampsOwned().isEmpty()) {
            System.out.println("You do not own any camps to delete.");
            return;
        }
        
        for (int i = 0; i < user.getCampsOwned().size(); i++) {
            System.out.printf("%d. %s\n", i + 1, user.getCampsOwned().get(i).getName());
        }

        // Get the index of the camp to delete
        System.out.print("Enter the number of the camp you wish to delete: ");
        int campIndex = scanner.nextInt();
        
        // Validate the index
        if (campIndex < 1 || campIndex > user.getCampsOwned().size()) {
            System.out.println("Invalid camp number. Please try again.");
            return;
        }
        Camp campToDelete = user.getCampsOwned().remove(campIndex - 1);
        if (campToDelete.getCommitteeList().size() != 0 || campToDelete.getStudentList().size()!= 0){
            System.out.println("Students already registered for camp, unable to delete");
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
        
        CampMasterList.remove(campToDelete);
        System.out.println("Camp deleted successfully: " + campToDelete.getName());
    }

    public static void toggleCampVisibility(Staff user) {

        // Display the list of camps owned by the staff member
        System.out.println("Select a camp to toggle its visibility:");
        if (user.getCampsOwned().isEmpty()) {
            System.out.println("You do not own any camps to toggle visibility.");
            return;
        }

        for (int i = 0; i < user.getCampsOwned().size(); i++) {
            Camp camp = user.getCampsOwned().get(i);
            System.out.printf("%d. %s (Currently %s)\n", i + 1, camp.getName(), camp.getVisibility() ? "Visible" : "Hidden");
        }

        // Get the index of the camp to toggle visibility
        int campIndex = Helper.readInt("Enter the number of the camp: ");

        // Validate the index
        if (campIndex < 1 || campIndex > user.getCampsOwned().size()) {
            System.out.println("Invalid camp number. Please try again.");
            return;
        }

        // Toggle the visibility
        Camp selectedCamp = user.getCampsOwned().get(campIndex - 1);
        if (selectedCamp.getCommitteeList().size() != 0 || selectedCamp.getStudentList().size()!= 0){
            System.out.println("Students already registered for camp, unable to toggle visibilty");
            return;
        }
        selectedCamp.setVisibility(!selectedCamp.getVisibility());
        System.out.println("Camp visibility toggled. The camp is now " + (selectedCamp.getVisibility() ? "visible" : "hidden") + ".");
    }
}
