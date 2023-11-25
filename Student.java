import java.time.LocalDate;
import java.util.ArrayList;
public class Student extends User {
    private ArrayList<Camp> registeredCamps;
    private ArrayList<Camp> withdrawnCamps;
    private ArrayList<Enquiry> enquiriesMade;
    private ArrayList<Suggestion> suggestionsMade;
    private Camp committeeOf = null;
    private int point = 0;

    //constructor
    public Student(String name, String userID, String faculty) {
        super(name, userID, faculty);
        this.registeredCamps = new ArrayList<Camp>();
        this.withdrawnCamps = new ArrayList<Camp>();
        this.enquiriesMade = new ArrayList<Enquiry>();
        this.suggestionsMade = new ArrayList<Suggestion>();
    }

    //Overloaded
    public Student(String name, String userID, String faculty, String password, ArrayList<Camp> registeredCamps, ArrayList<Camp> withdrawnCamps, ArrayList<Enquiry> enquiriesMade ,ArrayList<Suggestion> suggestionsMade) {
        super(name, userID, faculty, password);
        this.registeredCamps = registeredCamps;
        this.withdrawnCamps = withdrawnCamps;
        this.enquiriesMade = enquiriesMade;
        this.suggestionsMade = suggestionsMade;
    }

    public void addRegisteredCamp(Camp camp) {
        this.registeredCamps.add(camp);
    }

    public void removeRegisteredCamp(Camp camp) {
        this.registeredCamps.remove(camp);
    }

    public ArrayList<Camp> getRegisteredCamps(){
        return this.registeredCamps;
    }
    public void addWithdrawnCamp(Camp camp) {
        this.withdrawnCamps.add(camp);
    }
    //cannot remove withdrawn camps

    public ArrayList<Camp> getWithdrawnCamps(){
        return this.withdrawnCamps;
    }

    //making enquiry
    public void addEnquiriesMade(Enquiry enquiry){
        this.enquiriesMade.add(enquiry);
    }

    //deleting enquiry
    public void removeEnquiriesMade(Enquiry enquiry){
        this.enquiriesMade.remove(enquiry);
    }
    public ArrayList<Enquiry> getEnquiriesMade(){
        return this.enquiriesMade;
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
    
    public void addPoint(){
        this.point += 1;
    }

    public void setPoint(int point){
        this.point = point;
    }

    public void addSuggestionsMade(Suggestion suggestion){
        this.suggestionsMade.add(suggestion);
    }

    public void removeSuggestionsMade(Suggestion suggestion){
        this.suggestionsMade.remove(suggestion);
    }

    public ArrayList<Suggestion> getSuggestionsMade(){
        return this.suggestionsMade;
    }
    

    //returns true if clash, false if no clash
    private Boolean checkClash(Camp camp){
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
        //Temp list to store camps avaiable to user
        ArrayList<Camp> available = new ArrayList<Camp>();
        for (Camp eachcamp : CampMasterList){
            if ((eachcamp.getOwnSchool() == false || eachcamp.getUserGroup().equals(this.getFaculty())) && (eachcamp.getVisibility())){ // If camp is NOT locked to ownschool OR user's faculty matches camp AND is visible
                available.add(eachcamp);
            }
        }
        System.out.printf("debug size of available list = %d\n",available.size());
        //While not logged out
        while (choice != 0){
            //User details
            System.out.println("---------------------------------------------");
            System.out.printf("Username    : %s\n", this.getName());
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
            System.out.println("1.  Change Password");
            System.out.println("2.  View camps available to you");
            System.out.println("3.  Register for a camp");
            System.out.println("4.  View registered camps");
            System.out.println("5.  Withdraw from a camp");
            System.out.println("6.  View withdrawn camps");
            System.out.println("7.  Submit an enquiry");
            System.out.println("8.  View enquiries made");
            System.out.println("9.  Edit an enquiry");
            System.out.println("10.  Delete an enquiry");

            //Committee exclusive options
            if (this.committeeOf != null){
                System.out.println("11. View committee camp details");
                System.out.println("12. View camp's enquiries");
                System.out.println("13. Answer enquiries");
                System.out.println("14. Submit suggestion");
                System.out.println("15. View suggestions");
                System.out.println("16. Edit a suggestion");
                System.out.println("17. Delete a suggestion");
                System.out.println("18. Generate camp report");
            }
            System.out.println("---------------------------------------------");
            System.out.printf("Input an option >>");
            
            //SWITCH STATEMENTS
            choice = Helper.readInt("");
            if ((this.committeeOf == null && choice >=11) || choice > 18){
                System.out.printf("Invalid option\n");
            }
            switch(choice){
                case 0: //Logs out 
                break;

                case 1://change password
                String new_password = Helper.readString("Input new password : ");
                this.setPassword(new_password);
                System.out.printf("Password successfully changed\n");
                System.out.printf("You will now be logged out\n");
                choice = 0;
                break;
                
                case 2: //View Camps Available to you  
                FilterLister.listCamp(available, true, "Available");
                break; // View available camps END

                case 3: //Register for a camp
                //Show available camps first
                FilterLister.listCamp(available, false, "Available");
                System.out.printf("---------------------------------------------------------\n");
                //Select camp to register for
                int campindex = Helper.readInt("Select Camp to register for : ");
                
                if (campindex > 0 && campindex <= available.size()){
                    //Register as attendee or committee
                    Camp target = available.get(campindex-1);
                    if (LocalDate.now().isAfter(target.getRegisterDate())){System.out.println("The registration date for this camp has passed");}
                    else{
                        if(this.withdrawnCamps.contains(target) || this.registeredCamps.contains(target)){System.out.println("You have already registered or withdrawn from this camp");}
                        else{
                            if(checkClash(target)){System.out.println("The camp clashes with your registered camps");}
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
                                        this.registeredCamps.add(target);
                                        target.addStudent(this);
                                        System.out.printf("Successfully registered as attendee for : %s\n",target.getName());
                                    }
                                    break;
                                    case 2://as committee
                                    if (this.committeeOf != null){System.out.println("You are already in the committee of a camp");}
                                    else{
                                        if (target.getRemainingCommittee() == 0){System.out.println("Committee slots for this camp are full");}
                                        else{
                                            this.committeeOf = target;
                                            target.addCommittee(this);
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
                break;//Register for camp END

                case 4://View registered camps
                FilterLister.listCamp(registeredCamps,false,"Registered");
                break; //View registered camps END

                case 5: // Withdraw from a camp
                //Display registered Camps first
                FilterLister.listCamp(registeredCamps,false,"Registered");
                System.out.printf("---------------------------------------------------------\n");
                campindex = Helper.readInt("Select camp to withdrawn from : ");
                
                if (campindex > 0 && campindex <= registeredCamps.size()){
                    Camp target = registeredCamps.get(campindex-1);
                    if (this.committeeOf == target){
                        System.out.printf("---------------------------------------------------------\n");
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

                case 6://View withdrawn camps
                FilterLister.listCamp(registeredCamps,false,"Withdrawn");
                break;//View withdrwan camps END

                case 7://Submit Enquiry
                //Show camps to submit enquiries to
                FilterLister.listCamp(CampMasterList,false,"All");
                EnquiryManager.submitEnquiry(this, CampMasterList);
                break; //submit enquiry END

                case 8: //View Enquries Made
                EnquiryManager.viewEnquiriesMade(this);
                break; //View Enquiries END

                case 9://Edit enquiry
                //Show enquiries first
                EnquiryManager.viewEnquiriesMade(this);
                EnquiryManager.editEnquiry(this);

                break;//Edit Enquire END

                case 10://Delete enquiry
                //Show enquiries first
                EnquiryManager.viewEnquiriesMade(this);
                EnquiryManager.deleteEnquiry(this);
                break; //Delete enquiry END

                case 11: // View Committe Details
                if(this.committeeOf !=null){
                    FilterLister.printCampDetails(committeeOf);
                }
                break; // View Committee Details END

                case 12: // View Enquiry made to your camp
                if(this.committeeOf !=null){
                    EnquiryManager.viewCampEnquiries(this);
                }
                break; //View Enquiry of camp END

                case 13: // Answer Enquiry
                //First show enquiries made to your camp
                if(this.committeeOf !=null){
                    EnquiryManager.viewCampEnquiries(this);
                    EnquiryManager.answerEnquiry(this);
                }
                break; // Answer Enquiry End

                case 14://Submit suggestion
                if(this.committeeOf !=null){
                    SuggestionManager.submitSuggestion(this);
                }
                break; // Submit suggestion END

                case 15://View suggestions made
                if(this.committeeOf !=null){
                    SuggestionManager.viewSuggestionsMade(this);
                }
                break; //View suggestions made END

                case 16://Edit Suggestion
                //Show suggestions made first
                if(this.committeeOf !=null){
                    SuggestionManager.viewSuggestionsMade(this);
                    SuggestionManager.editSuggestion(this);
                }
                break;//Edit suggestion END

                case 17://Delete Suggestion
                //Show suggestions made first
                if(this.committeeOf !=null){
                    SuggestionManager.viewSuggestionsMade(this);
                    SuggestionManager.deleteSuggestion(this);
                }
                break;

                case 18:
                FilterLister.generateCampReport(this.committeeOf);
                break;
                
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


