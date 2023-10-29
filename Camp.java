import java.time.LocalDate;

public class Camp {
    private String Name;
    private LocalDate StartDate; // YYYY-MM-DD 
    private LocalDate EndDate; // YYYY-MM-DD 
    private LocalDate RegistrationEnd; // YYYY-MM-DD 

    private String Availability; // School or ALL
    private String Location; 
    private String Description;

    private int TotalSlots; 
    private int FreeCommittee;
    private int RemainingSlots;
    private User[] StudentList; // Max TotalSlots
    private User[] Committee; // Max 10
    private User StaffInCharge; // Auto set using CurrentUser from main?

    private Boolean Visible;

    public Camp(String Name, LocalDate StartDate, LocalDate EndDate, LocalDate RegistrationEnd, int TotalSlots){
        this.Name = Name;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.RegistrationEnd = RegistrationEnd;

        this.TotalSlots = TotalSlots;
        this.RemainingSlots = this.TotalSlots;
        this.StudentList = new User[TotalSlots];
        for (int i = 0; i < TotalSlots; i++){ // Init as nulls
            this.StudentList[i] = null;
        }

        this.FreeCommittee = 10; // Remaining Committee Slots
        this.Committee = new User[10];
        for (int i = 0; i < 10; i++){ // Init as nulls
            this.Committee[i] = null;
        }


        this.Visible = false; // Initially not visible

        this.Availability = "TBD"; // To be determined
        this.Location = "TBD";
        this.Description = "TBD";
    }

    public void changeCampName(String Name){
        this.Name = Name;
    }
    public void changeStartDate(LocalDate StartDate){
        this.StartDate = StartDate;
    }
    public void changeEndDate(LocalDate EndDate){
        this.EndDate = EndDate;
    }
    public void changeRegistrationEnd(LocalDate RegistrationEnd){
        this.RegistrationEnd = RegistrationEnd;
    }
    public void changeAvailability(String Availability){
        this.Availability = Availability;
    }
    public void changeLocation(String Location){
        this.Location = Location;
    }
    public void changeDescription(String Description){
        this.Description = Description;
    }
    public void changeTotalSlots(int TotalSlots){
        this.TotalSlots = TotalSlots;
    }
    public void setRemainingSlots(int i){
        this.RemainingSlots += i;
    }
    public void setFreeCommittee(int i){
        this.FreeCommittee +=i;
    }
    public void toggleVisibility(boolean toggle){
        this.Visible = toggle;
    }


    

    
    public String getName(){
        return this.Name;
    }
    public LocalDate getStartDate(){
        return StartDate;
    }
    public LocalDate getEndDate(){
        return EndDate;
    }
    public LocalDate getRegistrationEnd(){
        return RegistrationEnd;
    }
    public String getAvailability(){
        return this.Availability;
    }
    public String getLocation(){
        return this.Location;
    }
    public String getDescription(){
        return this.Description;
    }
    public int getTotalSlots(){
        return this.TotalSlots;
    }
    public int getRemainingSlots(){
        return this.RemainingSlots;
    } 
    public int getFreeCommittee(){
        return this.FreeCommittee;
    }
    public Boolean isVisible(){
        return this.Visible;
    }

    public User[] getStudentList(){
        return this.StudentList;
    }
    public User[] getCommittee(){
        return this.Committee;
    }
}
