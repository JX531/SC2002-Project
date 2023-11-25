import java.time.LocalDate;
import java.util.ArrayList;

//NOTES//
//We need to add SUGGESTIONS for camps too, as well as POINTS system for committee members//
public class Camp {
    private String name;
    private String userGroup;
    private String location;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registerDate;
    private int slot;
    private int remainingCommittee;
    private ArrayList<Student> studentList;
    private ArrayList<Student> committee;
    private Staff staffInCharge;
    private ArrayList<Enquiry> enquiries;
    private ArrayList<Suggestion> suggestions;  // Suggestion probably similar to Enquiries? 
    private Boolean ownSchool;
    private Boolean visibility;

    //Constructor
    public Camp(String name 
    , String userGroup
    , String location
    , String description
    , LocalDate startDate
    , LocalDate endDate
    , LocalDate registerDate
    , int slot
    , Staff staffInCharge
    , Boolean ownSchool)
    {
    this.name = name;
    this.userGroup = userGroup;
    this.location = location;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.registerDate = registerDate;
    this.slot = slot-10;
    this.remainingCommittee = 10;
    this.studentList = new ArrayList<Student>();
    this.committee = new ArrayList<Student>();
    this.staffInCharge = staffInCharge;
    this.enquiries = new ArrayList<Enquiry>();
    this.suggestions = new ArrayList<Suggestion>(); 
    this.ownSchool = ownSchool;
    this.visibility = true;
    }
    //Overloaded
    public Camp(String name 
    , String userGroup
    , String location
    , String description
    , LocalDate startDate
    , LocalDate endDate
    , LocalDate registerDate
    , int slot
    , int remainingCommittee
    , ArrayList<Student> studentList
    , ArrayList<Student> committee
    , ArrayList<Enquiry> enquiries
    , ArrayList<Suggestion> suggestions
    , Staff staffInCharge
    , Boolean ownSchool
    , Boolean visibility)
    {
    this.name = name;
    this.userGroup = userGroup;
    this.location = location;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.registerDate = registerDate;
    this.slot = slot;
    this.remainingCommittee = remainingCommittee;
    this.studentList = studentList;
    this.committee = committee;
    this.staffInCharge = staffInCharge;
    this.enquiries = enquiries;
    this.suggestions = suggestions; 
    this.ownSchool = ownSchool;
    this.visibility = visibility;
    }

    public String getName(){
        return this.name;
    }
    public void setname(String name){
        this.name = name;
    }

    public String getUserGroup(){
        return this.userGroup;
    }
    //no change usergroups
    public Staff getStaffinCharge(){
        return this.staffInCharge;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getLocation(){
        return this.location;
    }
    public LocalDate getStarDate(){
        return this.startDate;
    }
    // no change startdate

    public LocalDate getEndDate(){
        return this.endDate;
    }
    // no change enddate

    public LocalDate getRegisterDate(){
        return this.registerDate;
    }  
    // no change registerdate

    public int getSlots(){
        return this.slot;
    }

    public int getRemainingCommittee(){
        return this.remainingCommittee;
    }

    public ArrayList<Student> getCommitteeList(){
        return this.committee;
    }
    public ArrayList<Student> getStudentList(){
        return this.studentList;
    }
    public ArrayList<Enquiry> getEnquiries(){
        return this.enquiries;
    }
    public ArrayList<Suggestion> getSuggestions(){
        return this.suggestions;
    }
    public void addStudent(Student student){
        this.studentList.add(student);
        this.slot--;
    }
    public void removeStudent(Student student){
        this.studentList.remove(student);
        this.slot++;
    }

    public void addCommittee(Student student){
        this.committee.add(student);
        this.remainingCommittee--;
    }
    //cannot remove committee
    
    public Boolean getOwnSchool(){
        return this.ownSchool;
    }
    public void addEnquiry(Enquiry enquiry){
       this.enquiries.add(enquiry);
    }
    
    public void removeEnquiry(Enquiry enquiry){
        this.enquiries.remove(enquiry);
    }

    public void addSuggestion(Suggestion suggestion){
        this.suggestions.add(suggestion);
    }
    public void removeSuggestion(Suggestion suggestion){
        this.suggestions.remove(suggestion);
    }

    public Boolean getVisibility(){
        return this.visibility;
    }
    
}



