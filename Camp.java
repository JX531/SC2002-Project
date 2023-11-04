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
    //private ArrayList<Suggestion> suggestions;  // Suggestion probably similar to Enquiries? 
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
    this.remainingCommittee = 10;
    this.studentList = new ArrayList<>();
    this.committee = new ArrayList<>();
    this.staffInCharge = staffInCharge;
    this.enquiries = new ArrayList<>();
    //this.suggestions = new ArrayList<>() 
    this.ownSchool = ownSchool;
    this.visibility = visibility;
    }
}

//Continue code below