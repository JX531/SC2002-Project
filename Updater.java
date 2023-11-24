import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Updater {
    //Update Paths here
    private static String Camp_Student_Path = "C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\Relations\\\\Camp_Student.csv";
    private static String Camp_Staff_Path = "C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\Relations\\\\Camp_Staff.csv";
    private static String StudentList_Path = "C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\Relations\\\\StudentList.csv";
    private static String StaffList_Path = "C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\Relations\\\\StaffList.csv";
    private static String CampList_Path = "C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\Relations\\\\CampList.csv";
    private static String EnquiryList_Path = "C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\Relations\\\\EnquiryList.csv";
    private static String SuggestionList_Path = "C:\\\\Users\\\\Admin\\\\OneDrive\\\\Desktop\\\\Relations\\\\SuggestionList.csv";

    private static String escapeField(String field) {
        // If the field is null, return an empty string
        if (field == null) {
            return "";
        }
    
        // If the field contains a comma or double quote, enclose it in double quotes
        if (field.contains(",") || field.contains("\"")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        } else {
            return field;
        }
    }

    public static void updateCamp_Student(ArrayList<Student> StudentMasterList){
        try{
            Path path  = Path.of(Camp_Student_Path);
            String header = "Relation, Camp Name, StudentID, Points\n";
            Files.writeString(path, header, StandardOpenOption.TRUNCATE_EXISTING);
            //for each student in StudentMasterList
            for (Student eachStudent:StudentMasterList){
                //Write Committee relation
                if(eachStudent.getCommitteeOf() != null){
                    String comitteeDataRow = "2," + eachStudent.getCommitteeOf().getName() + "," + eachStudent.getID() + "," + eachStudent.getPoint() + "\n";
                    Files.writeString(path, comitteeDataRow, StandardOpenOption.APPEND);
                }
                //iterate through registered list
                for (Camp eachRegistered:eachStudent.getRegisteredCamps()){
                    String registeredDataRow = "1," + eachRegistered.getName() + "," + eachStudent.getID() + "," + eachStudent.getPoint() + "\n";
                    Files.writeString(path, registeredDataRow, StandardOpenOption.APPEND);
                }

                //iterate through withdrawn list
                for (Camp eachWithdrawn:eachStudent.getWithdrawnCamps()){
                    String withdrawnDataRow = "0," + eachWithdrawn.getName() + "," + eachStudent.getID() + "," + eachStudent.getPoint() + "\n";
                    Files.writeString(path, withdrawnDataRow, StandardOpenOption.APPEND);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateCamp_Staff(ArrayList<Staff> StaffMasterList){
        try{
            Path path  = Path.of(Camp_Staff_Path);
            String header = "Camp Name, Staff ID\n";
            Files.writeString(path, header, StandardOpenOption.TRUNCATE_EXISTING);
            //for each staff
            for (Staff eachStaff:StaffMasterList){
                //iterate through their created camp list
                for (Camp eachCreated:eachStaff.getCampsOwned()){
                    String DataRow = eachCreated.getName() + "," + eachStaff.getID() + "\n";
                    Files.writeString(path, DataRow, StandardOpenOption.APPEND);
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudents(ArrayList<Student> StudentMasterList){
        try{
            Path path  = Path.of(StudentList_Path);
            String header = "Name, Email, Faculty, Password\n";
            Files.writeString(path, header, StandardOpenOption.TRUNCATE_EXISTING);
            //for each student
            for (Student eachStudent:StudentMasterList){
                String DataRow = eachStudent.getName() + "," + (eachStudent.getID() +"@e.ntu.edu.sg") + "," + eachStudent.getFaculty() + "," + eachStudent.getPassword() + "\n";
                Files.writeString(path, DataRow, StandardOpenOption.APPEND);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateStaff(ArrayList<Staff> StaffMasterList){
        try{
            Path path  = Path.of(StaffList_Path);
            String header = "Name, Email, Faculty, Password\n";
            Files.writeString(path, header, StandardOpenOption.TRUNCATE_EXISTING);
            //for each student
            for (Staff eachStaff:StaffMasterList){
                String DataRow = eachStaff.getName() + "," + (eachStaff.getID() +"@E.NTU.EDU.SG") + "," + eachStaff.getFaculty() + "," + eachStaff.getPassword() + "\n";
                Files.writeString(path, DataRow, StandardOpenOption.APPEND);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateCamps(ArrayList<Camp> CampMasterList){
        try{
            Path path  = Path.of(CampList_Path);
            String header = "Name, User Group, Location, Description, Start Date, End Date, Register Date, Slot, Remaining Committee, Staff in Charge, OwnSchool, Visibility \n";
            Files.writeString(path, header, StandardOpenOption.TRUNCATE_EXISTING);
            for (Camp eachCamp:CampMasterList){
                String DataRow = eachCamp.getName() +","+ eachCamp.getUserGroup() +","+ eachCamp.getLocation() +","+ escapeField(eachCamp.getDescription()) +","+ eachCamp.getStarDate() +","+ eachCamp.getEndDate() +","+ eachCamp.getRegisterDate() +","+ eachCamp.getSlots() +","+ eachCamp.getRemainingCommittee() +","+ eachCamp.getStaffinCharge().getID() +","+ eachCamp.getOwnSchool() +","+ eachCamp.getVisibility() +"\n";
                Files.writeString(path, DataRow, StandardOpenOption.APPEND);
            }
            
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateEnquiries(ArrayList<Camp> CampMasterList){
        try{
            Path path  = Path.of(EnquiryList_Path);
            String header = "SentTo, MadeBy, Question, Answer\n";
            Files.writeString(path, header, StandardOpenOption.TRUNCATE_EXISTING);
            for (Camp eachCamp:CampMasterList){
                for (Enquiry eachEnquiry:eachCamp.getEnquiries()){
                    String DataRow = eachEnquiry.getSentTo().getName() +","+ eachEnquiry.getCreatedBy().getID() +","+ escapeField(eachEnquiry.getQuestion()) +","+ escapeField(eachEnquiry.getAnswer()) +"\n";
                    Files.writeString(path, DataRow, StandardOpenOption.APPEND);
                }
            }
            
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateSuggestions(ArrayList<Camp> CampMasterList){
        try{
            Path path  = Path.of(SuggestionList_Path);
            String header = "SentTo, CreatedBy, Suggestion, Approval, Processed\n";
            Files.writeString(path, header, StandardOpenOption.TRUNCATE_EXISTING);
            for (Camp eachCamp:CampMasterList){
                for (Suggestion eachSuggestion:eachCamp.getSuggestions()){
                    String DataRow = eachSuggestion.getSentTo().getName() +","+ eachSuggestion.getCreatedby().getID() +","+ escapeField(eachSuggestion.getSuggestion()) +","+ eachSuggestion.getApproved() +","+ eachSuggestion.getProcessed() +"\n";
                    Files.writeString(path, DataRow, StandardOpenOption.APPEND);
                }
            }
            
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
