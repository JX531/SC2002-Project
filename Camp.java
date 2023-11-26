import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Camp class that represents a camp
 */
public class Camp {
    /**
     * the name of the camp
     */
    private String name;
    /**
     * the user group the camp is open to (faculty/NTU)
     */
    private String userGroup;
    /**
     * the location of the camp
     */
    private String location;
    /**
     * the description of the camp
     */
    private String description;
    /**
     * the start date of the camp
     */
    private LocalDate startDate;
    /**
     * the end date of the camp
     */
    private LocalDate endDate;
    /**
     * the registration closing date
     */
    private LocalDate registerDate;
    /**
     * the number of slots available for the camp
     */
    private int slot;
    /**
     * the number of committee slots left for the camp
     */
    private int remainingCommittee;
    /**
     * the list of students in the camp
     */
    private ArrayList<Student> studentList;
    /**
     * the list of committee members
     */
    private ArrayList<Student> committee;
    /**
     * the staff in charge of the camp
     */
    private Staff staffInCharge;
    /**
     * the list of enquiries made for the camp
     */
    private ArrayList<Enquiry> enquiries;
    /**
     * the list of suggestions made for the camp
     */
    private ArrayList<Suggestion> suggestions; 
    /**
     * whether the camp is in user's own school
     */
    private Boolean ownSchool;
    /**
     * visibility of camp to students
     */
    private Boolean visibility;

    /** Constructor to make a new camp
     * @param name name of camp
     * @param userGroup user group the camp is open to
     * @param location location of camp
     * @param description description of camp
     * @param startDate start date of camp
     * @param endDate end date of camp
     * @param registerDate registration closing date
     * @param slot number of slots for camp
     * @param staffInCharge staff in charge of camp
     * @param ownSchool whether camp is in users' own school
     */
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

    /** Overloaded constructor to aloow input for all atributes for new camp
     * @param name name of camp
     * @param userGroup user group the camp is open to
     * @param location location of camp
     * @param description  description of camp
     * @param startDate start date of camp
     * @param endDate end date of camp
     * @param registerDate registration closing date
     * @param slot number of slots for camp
     * @param remainingCommittee remaining number of committee members available
     * @param studentList list of students registered
     * @param committee list of committee members
     * @param enquiries enquiries made for the camp
     * @param suggestions suggestions made for the camp
     * @param staffInCharge staff in charge of camp
     * @param ownSchool whether camp is in users' own school
     * @param visibility visibility of camp to students
     */
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

    /** Getter for name
     * @return the name of camp
     */
    public String getName(){
        return this.name;
    }

    /** Setter for name
     * @param name name of the camp
     */
    public void setName(String name){
        this.name = name;
    }

    /** Getter for user group
     * @return the user group of camp
     */
    public String getUserGroup(){
        return this.userGroup;
    }

    /** Setter for user group
     * @param usergroup user group the camp is open to
     */
    public void setUserGroup(String usergroup){
        this.userGroup = usergroup;
    }

    /** Getter for staff in charge
     * @return staff in charge of camp
     */
    public Staff getStaffinCharge(){
        return this.staffInCharge;
    }

    /** Setter for staff in charge
     * @param staff staff in charge of cam
     */
    public void setStaffinCharge(Staff staff){
        this.staffInCharge = staff;
    }

    /** Getter for description
     * @return description of camp
     */
    public String getDescription(){
        return this.description;
    }

    /** Setter for description
     * @param description description of camp
     */
    public void setDescription(String description){
        this.description = description;
    }

    /** Getter for location
     * @return location of the camp
     */
    public String getLocation(){
        return this.location;
    }

    /** Setter for location
     * @param location location of the camp
     */
    public void setLocation(String location){
        this.location = location;
    }

    /** Getter for start date
     * @return start date of camp
     */
    public LocalDate getStartDate(){
        return this.startDate;
    }

    /** Setter for start date
     * @param startDate start date of camp
     */
    public void setStartDate(LocalDate startDate){
        this.startDate = startDate;
    }

    /** Getter for start date
     * @return start date of camp
     */
    public LocalDate getEndDate(){
        return this.endDate;
    }

    /** Setter for start date
     * @param endDate end date of camp
     */
    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }

    /** Getter for registration closing date
     * @return registration closing date
     */
    public LocalDate getRegisterDate(){
        return this.registerDate;
    }

    /** Setter for registration closing date
     * @param registerDate registration closing date
     */
    public void setRegisterDate(LocalDate registerDate){
        this.registerDate = registerDate;
    }

    /** Getter for slots
     * @return  number of slots for camp
     */
    public int getSlots(){
        return this.slot;
    }

    /** Setter for slots
     * @param slots number of slots for camp
     */
    public void setSlots(int slots){
        this.slot = slots;
    }

    /** Getter for remaining committee
     * @return remaining number of committee members available
     */
    public int getRemainingCommittee(){
        return this.remainingCommittee;
    }

    /** Setter for remaining committee
     * @return remaining number of committee members available
     */
    public ArrayList<Student> getCommitteeList(){
        return this.committee;
    }

    /** Get list of students
     * @return  list of students registered
     */
    public ArrayList<Student> getStudentList(){
        return this.studentList;
    }

    /** Getter for enquiries
     * @return enquiries made for the camp
     */
    public ArrayList<Enquiry> getEnquiries(){
        return this.enquiries;
    }

    /** Setter for enquiries
     * @return enquiries made for the camp
     */
    public ArrayList<Suggestion> getSuggestions(){
        return this.suggestions;
    }

    /** Add student to the list of students for camp
     * @param student student that is registering for camp
     */
    public void addStudent(Student student){
        this.studentList.add(student);
        this.slot--;
    }

    /** Remove student from the list of students for camp
     * @param student student that is removed from camp
     */
    public void removeStudent(Student student){
        this.studentList.remove(student);
        this.slot++;
    }

    /** Add committee member
     * @param student student that is registering as committee member
     */
    public void addCommittee(Student student){
        this.committee.add(student);
        this.remainingCommittee--;
    }
    //cannot remove committee

    /** Getter for own school
     * @return whether camp is in users' own school
     */
    public Boolean getOwnSchool(){
        return this.ownSchool;
    }

    /** Setter for own school
     * @param ownSchool whether camp is in users' own school
     */
    public void setOwnSchool(Boolean ownSchool){
        this.ownSchool = ownSchool;
    }

    /** Add enquiry for the camp
     * @param enquiry enquiry made about the camp
     */
    public void addEnquiry(Enquiry enquiry){
       this.enquiries.add(enquiry);
    }

    /** Remove enquiry for the camp
     * @param enquiry enquiry to be removed from the camp
     */
    public void removeEnquiry(Enquiry enquiry){
        this.enquiries.remove(enquiry);
    }

    /** Add suggestion to the camp
     * @param suggestion suggestion made about the camp
     */
    public void addSuggestion(Suggestion suggestion){
        this.suggestions.add(suggestion);
    }

    /** Remove suggestion from camp
     * @param suggestion suggestion to be removed from the camp
     */
    public void removeSuggestion(Suggestion suggestion){
        this.suggestions.remove(suggestion);
    }

    /** Getter for visibility
     * @return visibility of camp to students
     */
    public Boolean getVisibility(){
        return this.visibility;
    }

    /** Setter for visibility
     * @param visibility visibility of camp to students
     */
    public void setVisibility(Boolean visibility){
        this.visibility = visibility;
    }
    
}



