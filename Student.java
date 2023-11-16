import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Camp> registeredCamps = new ArrayList<>();
    private ArrayList<Camp> withdrawnCamps = new ArrayList<>();
    private ArrayList<Enquiry> enquiriesMade = new ArrayList<>();
    private ArrayList<Suggestion> suggestionsMade = new ArrayList<>();
    private Camp committeeOf = null;
    private int point = 0;

    //constructor
    public Student(String userID, String faculty) {
        super(userID, faculty);
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

    public void viewRegisteredCamps() {
        System.out.println("Registered Camps");
        System.out.println("--------------------");
        int i = 1;
        for (Camp camp : registeredCamps) {
            System.out.printf("%d. %s\n",i,camp.getName());
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
    public void menu() {
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

        //User options
        System.out.println("---------------------------------------------");
        System.out.println("1.  View camps available to you");
        System.out.println("2.  Register for a camp");
        System.out.println("3.  View registered camps");
        System.out.println("4.  Withdraw from a camp");
        System.out.println("5.  View withdrawn camps");
        System.out.println("6.  Submit an enquiry");
        System.out.println("7.  View enquiries");
        System.out.println("8.  Edit an enquiry");

        //Committee exclusive options
        if (this.committeeOf != null){
            System.out.println("9.  View committee details");
            System.out.println("10. View enquiries");
            System.out.println("11. Answer enquiries");
            System.out.println("12. Submit suggestion");
            System.out.println("13. View suggestions");
            System.out.println("14. Edit a suggestion");
        }
        System.out.println("---------------------------------------------");
        System.out.printf("Input an option >>");    
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


