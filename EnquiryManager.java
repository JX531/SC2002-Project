import java.util.ArrayList;

/**
 * Class used to manage enquiries
 */
public class EnquiryManager {

    /** Submit an enquiry
     * @param user user that is submitting the enquiry
     * @param CampMasterList camp list that shows all the camps
     */
    //For normal students
    //Need to see all camps so pass in both the user and CampMasterList
    public static void submitEnquiry(Student user, ArrayList<Camp> CampMasterList){ 
        //Ask for the index of the camp in master list to send enquiry to
        int campIndex = Helper.readInt("Select Camp to send Enquiry to : ");
        System.out.printf("---------------------------------------------------------\n");
        //check if given index is within valid range
        if (campIndex >0 && campIndex <= CampMasterList.size()){
            //select target camp for easier readability
            Camp target = CampMasterList.get(campIndex-1);
            //If user is not committee member OR NOT trying to send enquiry to their own camp
            if (user.getCommitteeOf()== null || target != user.getCommitteeOf()){
                //create new enquiry
                Enquiry new_enquiry = new Enquiry(Helper.readString("Input Enquiry : "), user, target);
                //add the enquiry to the target camp's enquiry list
                target.addEnquiry(new_enquiry);
                //add the enquiry to the user's enquiries made list
                user.addEnquiriesMade(new_enquiry);
                System.out.printf("Successfully sent Enquiry to : %s\n", target.getName());
            }
            //user is committee member and trying to send enquiry to their own camp
            else{
                System.out.printf("You cannot send enquiries to the camp you are a committee member in\n");
            }
        }
        //index not in valid range
        else{System.out.println("Invalid choice");}
    }

    /** View enquiries made by user
     * @param user user that is viewing their enquiries
     */
    //For normal students
    public static void viewEnquiriesMade(Student user){
        System.out.printf("Enquiries Made\n");
        System.out.printf("---------------------------------------------------------\n");
        //for each enquiry in user's enquiries made list
        int i = 1;
        for (Enquiry eachEnquiry : user.getEnquiriesMade()){
            //print the index
            System.out.printf("%d.\n",i);
            //print the camp it was sent to
            System.out.printf("Sent to  : %s\n", eachEnquiry.getSentTo().getName());
            //print question
            System.out.printf("Question : %s\n", eachEnquiry.getQuestion());
            //print answer 
            System.out.println("Answer: " + (eachEnquiry.getAnswer() != null ? eachEnquiry.getAnswer() : "No response yet."));;
            //increment index
            i++;
        }
    }

    /** Edit an enquiry made by user
     * @param user user that is trying to edit their enquiry
     */
    //For normal students
    public static void editEnquiry(Student user){    
        //Ask for index of enquiry in user's enquiries made list to edit   
        int enquiryIndex = Helper.readInt("Select Enquiry to edit : ");
        System.out.printf("---------------------------------------------------------\n");
        //check if given index is in valid range
        if (enquiryIndex > 0 && enquiryIndex <=user.getEnquiriesMade().size()){
            //select the target enquiry for easier readability
            Enquiry target = user.getEnquiriesMade().get(enquiryIndex-1);
            //check if enquiry was already answered
            if (target.getAnswer() != null){
                System.out.printf("Enquiry has already be answered, unable to edit\n");
            }
            else{
                //get new question
                String new_question = Helper.readString("Input new question : ");
                //replace target enquiry's question with new question
                target.setQuestion(new_question);
                System.out.printf("Successfully editted enquiry\n");
            }
        }
        //index not in valid range
        else{System.out.println("Invalid choice");}
    }

    /** Delete an enquiry made by user
     * @param user user that is trying to delete their enquiry
     */
    //For normal students
    public static void deleteEnquiry(Student user){
        //Ask for index of enquiry in user's enquiries made list to delete   
        int enquiryIndex = Helper.readInt("Select Enquiry to delete : ");
        System.out.printf("---------------------------------------------------------\n");
        //check if given index is in valid range
        if (enquiryIndex > 0 && enquiryIndex <=user.getEnquiriesMade().size()){
            //select the target enquiry for easier readability
            Enquiry target = user.getEnquiriesMade().get(enquiryIndex-1);
            //check if enquiry was already answered
            if (target.getAnswer() != null){
                System.out.printf("Enquiry has already been answered, unable to delete\n");
            }
            else{
                //remove target enquiry from the enquiry list of the camp it was sent to
                target.getSentTo().removeEnquiry(target);
                //remove target enquiry from the user's enquiries made list
                user.removeEnquiriesMade(target);
                System.out.printf("Enquiry successfully deleted\n");
            }
        }
        //index not in valid range
        else{System.out.println("Invalid choice");}
    }

    /** View enquiries for a camp
     * @param camp camp that is selected to view enquiries
     */
    //For both committee members and staff
    public static void viewCampEnquiries(Camp camp){
        //Enquiries made to the camp
        System.out.printf("Enquiries made to : %s\n", camp.getName());
        System.out.printf("---------------------------------------------------------\n");
        int i = 1;
        //for each enquiry in the enquiry list of the camp that user is committee member in
        for (Enquiry eachEnquiry : camp.getEnquiries()){
            //print index and question
            System.out.printf("%d. %s\n",i, eachEnquiry.getQuestion());
            //print answer
            System.out.println("Answer: " + (eachEnquiry.getAnswer() != null ? eachEnquiry.getAnswer() : "No response yet."));;
            //increment index
            i++;
        }
    }


    /** Answer an enquiry made
     * @param camp camp that is selected to answer enquiries
     */
    //For both committee members and staff
    public static void answerEnquiry(Camp camp){
            //get index of enquiry from the enquiry index of the camp user is a committee member in
            int enquiryIndex = Helper.readInt("Select Enquiry to answer : ");
            System.out.printf("---------------------------------------------------------\n");
            //check if given index is in valid range
            if ( enquiryIndex > 0 && enquiryIndex <= camp.getEnquiries().size()){
                //select target enquiry for easier readability
                Enquiry target = camp.getEnquiries().get(enquiryIndex-1);
                //check if target enquiry already answered
                if (target.getAnswer() != null){
                    System.out.printf("Enquiry has already been answered\n");
                }
                else{
                    //get input and set target enquiry's answer as input
                    target.setAnswer(Helper.readString("Input answer to enquiry : "));
                    System.out.printf("---------------------------------------------------------\n");
                    System.out.printf("Successfully answered enquiry : %s\n",target.getQuestion());
                }
            }
            //index not in valid range
            else{System.out.println("Invalid choice");}

    }
}
