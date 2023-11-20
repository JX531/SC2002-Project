import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Student extends User {
    private ArrayList<Camp> registeredCamps;
    private ArrayList<Camp> withdrawnCamps;
    private ArrayList<Enquiry> enquiriesMade;
    private ArrayList<Suggestion> suggestionsMade;
    private Camp committeeOf = null;
    private int point = 0;

    //constructor
    public Student(String userID, String faculty) {
        super(userID, faculty);
        this.registeredCamps = new ArrayList<Camp>();
        this.withdrawnCamps = new ArrayList<Camp>();
        this.enquiriesMade = new ArrayList<Enquiry>();
        this.suggestionsMade = new ArrayList<Suggestion>();
    }

    public void addRegisteredCamp(Camp camp) {
        this.registeredCamps.add(camp);
    }

    public void removeRegisteredCamp(int index) {
        if (index >= 0 && index < registeredCamps.size()) {
            this.registeredCamps.remove(index);
        }
        else {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    public void viewCamps(ArrayList<Camp> camps, String prefix) {
        int i = 1;
        System.out.printf("---------------------------------------------------------\n");
        System.out.printf("%s Camps\n",prefix);
        for (Camp eachcamp:camps){
            System.out.printf("---------------------------------------------------------\n");
            System.out.printf("%d.\n",i);
            System.out.printf("Name                      : %s\n",eachcamp.getName());
            System.out.printf("Faculty                   : %s\n",eachcamp.getUserGroup());
            System.out.printf("Location                  : %s\n",eachcamp.getLocation());
            System.out.printf("Attendee Slots Remaining  : %s\n",eachcamp.getSlots());
            System.out.printf("Committee Slots Remaining : %s\n",eachcamp.getRemainingCommittee());
            System.out.printf("Starts                    : %s\n",eachcamp.getStarDate());
            System.out.printf("Ends                      : %s\n",eachcamp.getEndDate());
            System.out.printf("Register before           : %s\n",eachcamp.getRegisterDate());
            System.out.printf("Description               : %s\n",eachcamp.getDescription());
            i++;
        }
    }

    public void addWithdrawnCamp(Camp camp) {
        this.withdrawnCamps.add(camp);
    }
    //cannot remove withdrawn camps

    //making enquiry
    public void addEnquiriesMade(Enquiry enquiry){
        this.enquiriesMade.add(enquiry);
    }

    //deleting enquiry
    public void removeEnquiriesMade(int index){
        if (index >= 0 && index < enquiriesMade.size()) {
            this.enquiriesMade.remove(index);
        }
        else {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    public void setCommitteeOf(Camp camp) {
        this.committeeOf = camp;
    }
    
    public Camp getCommitteeOf(){
        return this.committeeOf;
    }

    public int getPoint(){
        return this.point;
    }
    
    public void incPoint(){
        this.point += 1;
    }

    //making suggestion
    public void addSuggestionsMade(Suggestion suggestion){
        this.suggestionsMade.add(suggestion);
    }

    //deleting suggestion
    public void removeSuggestionsMade(int index){
        if (index >= 0 && index < suggestionsMade.size()) {
            this.suggestionsMade.remove(index);
        }
        else {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    //returns true if clash, false if no clash
    public Boolean checkClash(Camp camp){
        for (Camp eachcamp : registeredCamps){
            //target camp ends before registered camp starts or target camp starts after registered camp ends
            if (camp.getEndDate().isBefore(eachcamp.getStarDate()) || camp.getStarDate().isAfter(eachcamp.getEndDate())){
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

    @Override
    public void menu(ArrayList<Camp> CampMasterList){
        int choice = -1;
        Scanner scan = new Scanner(System.in);

        //Temp list to store camps avaiable to user
        ArrayList<Camp> available = new ArrayList<Camp>();
        for (Camp eachcamp : CampMasterList){
            if (eachcamp.getOwnSchool() == false || eachcamp.getUserGroup().equals(this.getFaculty())){ // If camp is NOT locked to ownschool OR user's faculty matches camp
                available.add(eachcamp);
            }
        }
        System.out.printf("%d\n",available.size());
        //While not logged out
        while (choice != 0){
            //User details
            System.out.println("---------------------------------------------");
            System.out.printf("Username    : %s\n", this.getID());
            System.out.printf("Faculty     : %s\n", this.getFaculty());
            if (this.committeeOf != null){
                System.out.printf("Committe of : %s\n", this.getCommitteeOf().getName());
                System.out.printf("Points      : %d\n", this.point);
            }
            else{
                System.out.printf("Committe of : No Committee Assigned\n");
            }

            //Student options
            System.out.println("---------------------------------------------");
            System.out.println("0.  Logout");
            System.out.println("1.  View camps available to you");
            System.out.println("2.  Register for a camp");
            System.out.println("3.  View registered camps");
            System.out.println("4.  Withdraw from a camp");
            System.out.println("5.  View withdrawn camps");
            System.out.println("6.  Submit an enquiry");
            System.out.println("7.  View enquiries made");
            System.out.println("8.  Edit an enquiry");
            System.out.println("9.  Delete an enquiry");

            //Committee exclusive options
            if (this.committeeOf != null){
                System.out.println("10. View committee details");
                System.out.println("11. View camp's enquiries");
                System.out.println("12. Answer enquiries");
                System.out.println("13. Submit suggestion");
                System.out.println("14. View suggestions");
                System.out.println("15. Edit a suggestion");
                System.out.println("16. Delete a suggestion");
            }
            System.out.println("---------------------------------------------");
            System.out.printf("Input an option >>");
            
            //SWITCH STATEMENTS
            choice = Helper.readInt("");
            switch(choice){
                case 0: //Logs out 
                break;

                case 1: //View Camps Available to you  
                viewCamps(available, "Available");
                break; // View available camps END

                case 2: //Register for a camp
                //Show available camps first
                viewCamps(available, "Available");
                System.out.printf("---------------------------------------------------------\n");
                //Select camp to register for
                int campindex = Helper.readInt("Select Camp to register for : ");

                if (campindex > 0 && campindex <= available.size()){
                    //Register as attendee or committee
                    Camp target = available.get(campindex-1);
                    System.out.printf("1. Attendee\n");
                    System.out.printf("2. Committee\n");
                    int answer = Helper.readInt("Register as : ");
                    if (answer >= 1 && answer <= 2){
                        switch(answer){
                            case 1://as attendeee
                            if (target.getSlots() == 0){ //Attendee slots full
                                System.out.println("Camp Attendee slots are full");
                            }
                            else{
                                if(registeredCamps.contains(target) || withdrawnCamps.contains(target)){//Already registered or withdrawn from camp
                                    System.out.println("You are already registered or withdrawn from the camp");
                                }
                                else{
                                    if(checkClash(target)){//clashes with registered camps
                                        System.out.println("Camp clashes with a registered camp");
                                    }
                                    else{//Register for camp as attendee successfully
                                        this.registeredCamps.add(target);
                                        target.addStudent(this);
                                        System.out.printf("Successfully registered as attendee for camp : %s\n",target.getName());
                                    }
                                }
                            }
                            break;
                            case 2://as committee
                            if (target.getRemainingCommittee() == 0){ //Attendee slots full
                                System.out.println("Camp Committee slots are full");
                            }
                            else{
                                if(registeredCamps.contains(target) || withdrawnCamps.contains(target)){//Already registered or withdrawn from camp
                                    System.out.println("You are already registered or withdrawn from the camp");
                                }
                                else{
                                    if(checkClash(target)){//clashes with registered camps
                                        System.out.println("Camp clashes with a registered camp");
                                    }
                                    else{//Already a committee member
                                        if (committeeOf != null){
                                            System.out.printf("You are already on a committe for camp : %s\n",committeeOf.getName());
                                        }
                                        else{//Register for camp as committee successfully
                                            this.registeredCamps.add(target);
                                            this.committeeOf = target;
                                            target.addCommittee(this);
                                            System.out.printf("Successfully registered as committee for camp : %s\n",target.getName());
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                    else{System.out.println("Invalid choice");}
                } 
                else{System.out.println("Invalid choice");}
                break;//Register for camp END

                case 3://View registered camps
                viewCamps(registeredCamps, "Registered");
                break; //View registered camps END

                case 4: // Withdraw from a camp
                //Display registered Camps first
                viewCamps(registeredCamps, "Registered");
                
                campindex = Helper.readInt("Select camp to withdrawn from : ");
                System.out.printf("---------------------------------------------------------\n");
                if (campindex > 0 && campindex <= registeredCamps.size()){
                    Camp target = registeredCamps.get(campindex-1);
                    if (this.committeeOf == target){
                        System.out.printf("You cannot withdrawn from a camp you are a committee member of\n");
                    }
                    else{
                        target.removeStudent(this); // Remove student from camp first
                        this.withdrawnCamps.add(target); // add camp to withdrawn list
                        this.registeredCamps.remove(target); // remove camp from registered list
                        System.out.printf("Successfully withdrawn from camp : %s\n",target.getName());
                    }
                }
                else{System.out.println("Invalid choice");}
                break; // withdraw from a camp END

                case 5://View withdrawn camps
                viewCamps(withdrawnCamps, "Withdrawn");
                break;//View withdrwan camps END

                case 6://Submit Enquiry
                //Show camps to submit enquiries to
                viewCamps(CampMasterList, "All");
                
                int campIndex = Helper.readInt("Select Camp to send Enquiry to : ");
                System.out.printf("---------------------------------------------------------\n");
                if (campIndex >0 && campIndex <= CampMasterList.size()){
                    Camp target = CampMasterList.get(campIndex-1);
                    //create new enquiry
                    Enquiry new_enquiry = new Enquiry(Helper.readString("Input Enquiry : "), target);
                    target.addEnquiry(new_enquiry);
                    this.enquiriesMade.add(new_enquiry);
                    System.out.printf("Successfully sent Enquiry to : %s\n", target.getName());
                }
                else{System.out.println("Invalid choice");}
                break; //submit enquiry END

                case 7: //View Enquries Made
                int i = 1;
                
                System.out.printf("Enquiries Made\n");
                System.out.printf("---------------------------------------------------------\n");
                for (Enquiry eachEnquiry : enquiriesMade){
                    System.out.printf("%d.\n",i);
                    System.out.printf("Sent to  : %s\n", eachEnquiry.getSentTo().getName());
                    System.out.printf("Question : %s\n", eachEnquiry.getQuestion());
                    System.out.printf("Answer   : %s\n", eachEnquiry.getAnswer());
                    i++;
                }
                break; //View Enquiries END

                case 8://Edit enquiry
                //Show enquiries first
                i = 1;
                
                System.out.printf("Enquiries Made\n");
                System.out.printf("---------------------------------------------------------\n");
                for (Enquiry eachEnquiry : enquiriesMade){
                    System.out.printf("%d.\n",i);
                    System.out.printf("Sent to  : %s\n", eachEnquiry.getSentTo().getName());
                    System.out.printf("Question : %s\n", eachEnquiry.getQuestion());
                    System.out.printf("Answer   : %s\n", eachEnquiry.getAnswer());
                    i++;
                }
                
                int enquiryIndex = Helper.readInt("Select Enquiry to edit : ");
                System.out.printf("---------------------------------------------------------\n");
                //edit the enquiry
                if (enquiryIndex > 0 && enquiryIndex <=enquiriesMade.size()){
                    Enquiry target = enquiriesMade.get(enquiryIndex-1);
                    if (target.getAnswer() != null){
                        System.out.printf("Enquiry has already be answered, unable to edit\n");
                    }
                    else{
                        String new_question = Helper.readString("Input new question : ");
                        target.setQuestion(new_question);
                        System.out.printf("Successfully editted enquiry\n");
                    }
                }
                else{System.out.println("Invalid choice");}

                break;//Edit Enquire END

                case 9://Delete enquiry
                //Show enquiries first
                i = 1;
                
                System.out.printf("Enquiries Made\n");
                System.out.printf("---------------------------------------------------------\n");
                for (Enquiry eachEnquiry : enquiriesMade){
                    System.out.printf("%d.\n",i);
                    System.out.printf("Sent to  : %s\n", eachEnquiry.getSentTo().getName());
                    System.out.printf("Question : %s\n", eachEnquiry.getQuestion());
                    System.out.printf("Answer   : %s\n", eachEnquiry.getAnswer());
                    i++;
                }
                
                enquiryIndex = Helper.readInt("Select Enquiry to delete : ");
                System.out.printf("---------------------------------------------------------\n");
                //delete the suggestion
                if (enquiryIndex > 0 && enquiryIndex <=enquiriesMade.size()){
                    Enquiry target = enquiriesMade.get(enquiryIndex-1);
                    if (target.getAnswer() != null){
                        System.out.printf("Enquiry has already been answered, unable to delete\n");
                    }
                    else{
                        target.getSentTo().removeEnquiry(target);
                        this.enquiriesMade.remove(target);
                        System.out.printf("Enquiry successfully deleted\n");
                    }
                }
                else{System.out.println("Invalid choice");}

                break; //Delete enquiry END

                case 10: // View Committe Details
                i = 1;
                for (Student eachStudent : this.committeeOf.getCommitteeList()){
                    System.out.printf("---------------------------------------------------------\n");
                    System.out.printf("Committee List\n");
                    System.out.printf("%d. %s\n",i, eachStudent.getID());
                    i++;
                }
                break; // View Committee Details END

                case 11: // View Enquiry of camp
                i = 1;
                System.out.printf("Enquiries made to : %s\n", this.committeeOf.getName());
                System.out.printf("---------------------------------------------------------\n");
                for (Enquiry eachEnquiry : this.committeeOf.getEnquiries()){
                    System.out.printf("%d. %s\n",i, eachEnquiry.getQuestion());
                    System.out.printf("Answer   : %s\n", eachEnquiry.getAnswer());
                    i++;
                }
                break; //View Enquiry of camp END

                case 12: // Answer Enquiry
                //First show enquiries made to your camp
                i = 1;
                System.out.printf("Enquiries made to : %s\n", this.committeeOf.getName());
                System.out.printf("---------------------------------------------------------\n");
                for (Enquiry eachEnquiry : this.committeeOf.getEnquiries()){
                    System.out.printf("%d.\n",i);
                    System.out.printf("Question : %s\n", eachEnquiry.getQuestion());
                    System.out.printf("Answer   : %s\n", eachEnquiry.getAnswer());
                    i++;
                }
                
                enquiryIndex = Helper.readInt("Select Enquiry to answer : ");
                System.out.printf("---------------------------------------------------------\n");
                //answer the suggestion
                if ( enquiryIndex > 0 && enquiryIndex <= this.committeeOf.getEnquiries().size()){
                    Enquiry target = this.committeeOf.getEnquiries().get(enquiryIndex-1);
                    if (target.getAnswer() != null){
                        System.out.printf("Enquiry has already been answered\n");
                    }
                    else{
                        target.setAnswer(Helper.readString("Input answer to enquiry : "));
                        System.out.printf("---------------------------------------------------------\n");
                        System.out.printf("Successfully answered enquiry : %s\n",target.getQuestion());
                    }
                }
                else{System.out.println("Invalid choice");}

                break; // Answer Enquiry End

                case 13://Submit suggestion
                Suggestion new_suggestion = new Suggestion(Helper.readString("Input suggestion : "), this);
                this.committeeOf.addSuggestion(new_suggestion);
                this.suggestionsMade.add(new_suggestion);
                break; // Submit suggestion END

                case 14://View suggestions made
                System.out.printf("Suggestions made\n");
                System.out.printf("---------------------------------------------------------\n");
                i = 1;
                for (Suggestion eachSuggestion:suggestionsMade){
                    System.out.printf("%d. %s\n", i, eachSuggestion.getSuggestion());
                    System.out.printf("Approved : %s\n", eachSuggestion.getApproved());
                }
                break; //View suggestions made END

                case 15://Edit Suggestion
                //Show suggestions made first
                System.out.printf("Suggestions made\n");
                System.out.printf("---------------------------------------------------------\n");
                i = 1;
                for (Suggestion eachSuggestion:suggestionsMade){
                    System.out.printf("%d. %s\n", i, eachSuggestion.getSuggestion());
                    System.out.printf("Approved : %s\n", eachSuggestion.getApproved());
                }
                System.out.printf("---------------------------------------------------------\n");
                int suggestionIndex = Helper.readInt("Select suggestion to edit : ");
                System.out.printf("---------------------------------------------------------\n");
                //edit the suggestion
                if (suggestionIndex > 0 && suggestionIndex <= suggestionsMade.size()){
                    Suggestion target = suggestionsMade.get(suggestionIndex-1);
                    if (target.getApproved() == true){
                        System.out.printf("Suggestion already approved, unable to edit\n");
                    }
                    else{
                        target.setSuggestion(Helper.readString("Input new suggestion : "));
                        System.out.printf("Successfully editted suggestion\n");
                    }
                }
                else{System.out.println("Invalid choice");}
                break;//Edit suggestion END

                case 16://Delete Suggestion
                //Show suggestions made first
                System.out.printf("Suggestions made\n");
                System.out.printf("---------------------------------------------------------\n");
                i = 1;
                for (Suggestion eachSuggestion:suggestionsMade){
                    System.out.printf("%d. %s\n", i, eachSuggestion.getSuggestion());
                    System.out.printf("Approved : %s\n", eachSuggestion.getApproved());
                }
                System.out.printf("---------------------------------------------------------\n");
                suggestionIndex = Helper.readInt("Select suggestion to edit : ");
                System.out.printf("---------------------------------------------------------\n");
                //delete the suggestion
                if (suggestionIndex > 0 && suggestionIndex <= suggestionsMade.size()){
                    Suggestion target = suggestionsMade.get(suggestionIndex-1);
                    if (target.getApproved() == true){
                        System.out.printf("Suggestion already approved, unable to delete\n");
                    }
                    else{
                        this.committeeOf.removeSuggestion(target);
                        this.suggestionsMade.remove(target);
                        System.out.printf("Successfully deleted suggestion\n");
                    }
                }
                else{System.out.println("Invalid choice");}
                break;//Delete Suggestion END
            }   
        }  
    }

    // //main for debugging & testing
    // public static void main(String[] args){
    //     Staff testStaff = new Staff("test staff","SCSE");

    //     Camp testCamp1 = new Camp("test camp 1", "SCSE","test","test", LocalDate.of(2022, 12, 3),
    //         LocalDate.of(2022, 12, 5),
    //         LocalDate.of(2022, 11, 28), 10, testStaff, false);

    //     Camp testCamp2 = new Camp("test camp 2", "SCSE","test","test", LocalDate.of(2022, 12, 4),
    //         LocalDate.of(2022, 12, 4),
    //         LocalDate.of(2022, 11, 28), 10, testStaff, false);    
            
    //     Student testStudent = new Student("test student", "SCSE");
    //     testStudent.addRegisteredCamp(testCamp1);
    //     testStudent.addRegisteredCamp(testCamp2);
    //     testStudent.committeeOf = testCamp1;

    //     System.out.println(testStudent.checkClash(testCamp2));
    //     testStudent.viewRegisteredCamps();
    //     //testStudent.menu();
    // }
}


