import java.util.Scanner;
import java.time.LocalDate;
public class Student extends User {
    Scanner scan = new Scanner(System.in);
    private Camp[] registered;
    private Camp[] withdrawn;
    private Camp CommitteeOf;

    public Student(String ID, String Faculty) {
        super(ID, Faculty); // Call the superclass constructor
        this.registered = new Camp[1000];
        this.withdrawn = new Camp[1000];
        this.CommitteeOf = null;
    }
    public boolean isAvailable(Camp camp){
        if (camp.getAvailability() != "ALL" && camp.getAvailability() != this.getFaculty()){ // If NOT open to ALL AND NOT match own faculty
            return false;
        }
        return true;
    }

    public boolean isNotFullAttendee(Camp camp){
        if (camp.getRemainingSlots() == 0){ 
            return false;
        }
        return true;
    }
    public boolean isNotFullCommittee(Camp camp){
        if (camp.getFreeCommittee() == 0){ 
            return false;
        }
        return true;
    }

    public boolean isOpen(Camp camp){
        if (camp.getRegistrationEnd().compareTo(LocalDate.now()) >0){ // if Registration End date is BEFORE current date
            return false;
        }
        return true;
    }

    public boolean noClash(Camp camp){
        for(Camp eachCamp : this.registered){
            if (eachCamp != null){
                if (camp.getStartDate().isAfter(eachCamp.getEndDate().plusDays(1)) || camp.getEndDate().plusDays(1).isBefore(eachCamp.getStartDate())){ //For each registered camp, ensure the camp ur registering for starts after enddate or ends before startdate
                //Do nothing here//
                } 
                else{
                    return false;
                }
            }
        }
        return true;
    }
    public boolean NotWithdrawnbefore(Camp camp){
        for (Camp eachCamp : this.withdrawn){ // check withdrawn list
            if (eachCamp == null){
                break; 
            }
            if (eachCamp == camp){ // withdrawn from camp before, cant register again
                return false;
            }
        }
        return true;
    }

    public void ViewCamps(Camp[] camplist){ // Camplist from Main
        int count = 1;
        System.out.println("No.\tCamp Name\tAvailability\tStarts\tEnds\tAttendee Slots remaining\tCommittee Slots remaining\tRegister By");
        for (Camp eachCamp : camplist){
            if (eachCamp != null){ // for every camp in camp list
                if (eachCamp.isVisible() && isAvailable(eachCamp)){ // check if its visible AND available
                    System.out.printf("%d\t%s\t%s\t%s\t%s\t%d\t%d\t%s\n",count, eachCamp.getName(), eachCamp.getAvailability(), eachCamp.getStartDate(), eachCamp.getEndDate(), eachCamp.getRemainingSlots(), eachCamp.getFreeCommittee(), eachCamp.getRegistrationEnd());
                    count++;
                }
            }
        }
    }

    
    public void Register(Camp camp){
        if (camp.isVisible() && isAvailable(camp) && isOpen(camp) && noClash(camp) && NotWithdrawnbefore(camp)){// Check if visible, available, still open, no clash and not withdrawn before first
            System.out.println("Register as 1. Attendee or 2. Committee?");
            int ans = scan.nextInt();

            if (ans == 1){ // Register as Attendee
                if (isNotFullAttendee(camp)){ // Not full Attendee
                    User[] list = camp.getStudentList();
                    for (int i = 0; i < camp.getTotalSlots() && list[i] != null; i++){ // until first null spot
                        list[i] = this; // registered in camp
                        camp.AddRemainingSlots(-1);
                        for (int j = 0; j < 1000 && this.registered[j] != null; j++){ // until first null spot
                            this.registered[j] = camp; // add camp to registered list
                        }
                    }
                }
            }
        

            if (ans == 2){ // Register as Committee
                if (isNotFullCommittee(camp) && this.CommitteeOf == null){ // Not full Committee AND Student not in committee
                    User[] list = camp.getCommittee();
                    for (int i = 0; i < 10 && list[i] != null; i++){ // until first null spot
                        list[i] = this; // registered in camp committee
                        camp.AddFreeCommittee(-1);
                        this.CommitteeOf = camp; // Is now a committee member of a camp
                    }
                }   
            }
        }    
    }
}

