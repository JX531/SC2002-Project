import java.util.ArrayList;
public class Student extends User {
    private ArrayList<Camp> registeredCamps;
    private ArrayList<Camp> withdrawnCamps;
    private ArrayList<Enquiry> enquiriesMade;
    private ArrayList<Suggestion> suggestionsMade;
    private Camp committeeOf;
    private int point;

    //constructor
    public Student(String name, String userID, String faculty) {
        super(name, userID, faculty);
        this.registeredCamps = new ArrayList<Camp>();
        this.withdrawnCamps = new ArrayList<Camp>();
        this.enquiriesMade = new ArrayList<Enquiry>();
        this.suggestionsMade = new ArrayList<Suggestion>();
        this.committeeOf = null;
        this.point = 0;
    }

    //Overloaded
    public Student(String name, String userID, String faculty, String password, ArrayList<Camp> registeredCamps, ArrayList<Camp> withdrawnCamps, ArrayList<Enquiry> enquiriesMade ,ArrayList<Suggestion> suggestionsMade, Camp committeeOf, int point) {
        super(name, userID, faculty, password);
        this.registeredCamps = registeredCamps;
        this.withdrawnCamps = withdrawnCamps;
        this.enquiriesMade = enquiriesMade;
        this.suggestionsMade = suggestionsMade;
        this.committeeOf = committeeOf;
        this.point = point;
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
                CampManager.listCamp(available, true, "Available");
                break; // View available camps END

                case 3: //Register for a camp
                //Show available camps first
                CampManager.listCamp(available, false, "Available");
                CampManager.registerForCamp(this, available);
                break;//Register for camp END

                case 4://View registered camps
                if (registeredCamps.isEmpty()){
                    System.out.printf("You are not registered for any camps\n");
                    break;
                }
                CampManager.listCamp(registeredCamps,false,"Registered");
                break; //View registered camps END

                case 5: // Withdraw from a camp
                //Display registered Camps first
                if (registeredCamps.isEmpty()){
                    System.out.printf("You are not registered for any camps\n");
                    break;
                }
                CampManager.listCamp(registeredCamps,false,"Registered");
                CampManager.withdrawnFromCamp(this);
                break; // withdraw from a camp END

                case 6://View withdrawn camps
                CampManager.listCamp(withdrawnCamps,false,"Withdrawn");
                break;//View withdrwan camps END

                case 7://Submit Enquiry
                //Show camps to submit enquiries to
                CampManager.listCamp(CampMasterList,false,"All");
                EnquiryManager.submitEnquiry(this, CampMasterList);
                break; //submit enquiry END

                case 8: //View Enquries Made
                if (this.enquiriesMade.isEmpty()){
                    System.out.printf("No enquiries made\n");
                    break;
                }
                EnquiryManager.viewEnquiriesMade(this);
                break; //View Enquiries END

                case 9://Edit enquiry
                if (this.enquiriesMade.isEmpty()){
                    System.out.printf("No enquiries to edit\n");
                    break;
                }
                //Show enquiries first
                EnquiryManager.viewEnquiriesMade(this);
                EnquiryManager.editEnquiry(this);

                break;//Edit Enquire END

                case 10://Delete enquiry
                if (this.enquiriesMade.isEmpty()){
                    System.out.printf("No enquiries to delete\n");
                    break;
                }
                //Show enquiries first
                EnquiryManager.viewEnquiriesMade(this);
                EnquiryManager.deleteEnquiry(this);
                break; //Delete enquiry END

                case 11: // View Committe Details
                if(this.committeeOf !=null){
                    CampManager.printCampDetails(committeeOf);
                }
                break; // View Committee Details END

                case 12: // View Enquiry made to your camp
                if (this.committeeOf.getEnquiries().isEmpty()){
                    System.out.printf("No enquiries have been made\n");
                    break;
                }
                if(this.committeeOf !=null){
                    EnquiryManager.viewCampEnquiries(this.committeeOf);
                }
                break; //View Enquiry of camp END

                case 13: // Answer Enquiry
                if (this.committeeOf.getEnquiries().isEmpty()){
                    System.out.printf("No enquiries to answer\n");
                    break;
                }
                //First show enquiries made to your camp
                if(this.committeeOf !=null){
                    EnquiryManager.viewCampEnquiries(this.committeeOf);
                    EnquiryManager.answerEnquiry(this.committeeOf);
                }
                break; // Answer Enquiry End

                case 14://Submit suggestion
                if(this.committeeOf !=null){
                    SuggestionManager.submitSuggestion(this);
                }
                break; // Submit suggestion END

                case 15://View suggestions made
                if(this.committeeOf !=null){
                    if (this.suggestionsMade.isEmpty()){
                        System.out.printf("No suggestions to view\n");
                        break;
                    }
                    SuggestionManager.viewSuggestionsMade(this);
                }
                break; //View suggestions made END

                case 16://Edit Suggestion
                //Show suggestions made first
                if(this.committeeOf !=null){
                    if (this.suggestionsMade.isEmpty()){
                        System.out.printf("No suggestions to edit\n");
                        break;
                    }
                    SuggestionManager.viewSuggestionsMade(this);
                    SuggestionManager.editSuggestion(this);
                }
                break;//Edit suggestion END

                case 17://Delete Suggestion
                //Show suggestions made first
                if(this.committeeOf !=null){
                    if (this.suggestionsMade.isEmpty()){
                        System.out.printf("No suggestions to delete\n");
                        break;
                    }
                    SuggestionManager.viewSuggestionsMade(this);
                    SuggestionManager.deleteSuggestion(this);
                }
                break;

                case 18:
                CampManager.generateCampReport(this.committeeOf);
                break;
                
            }   
        }  
    }

}


