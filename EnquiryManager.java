import java.util.ArrayList;
public class EnquiryManager {
    public static void submitEnquiry(Student user, ArrayList<Camp> CampMasterList){
        int campIndex = Helper.readInt("Select Camp to send Enquiry to : ");
        System.out.printf("---------------------------------------------------------\n");
        if (campIndex >0 && campIndex <= CampMasterList.size()){
            Camp target = CampMasterList.get(campIndex-1);
            Enquiry new_enquiry = new Enquiry(Helper.readString("Input Enquiry : "), target);
            target.addEnquiry(new_enquiry);
            user.getEnquiriesMade().add(new_enquiry);
            System.out.printf("Successfully sent Enquiry to : %s\n", target.getName());
        }
        else{System.out.println("Invalid choice");}
    }

    public static void viewEnquiriesMade(Student user){
        int i = 1;
        System.out.printf("Enquiries Made\n");
        System.out.printf("---------------------------------------------------------\n");
        for (Enquiry eachEnquiry : user.getEnquiriesMade()){
            System.out.printf("%d.\n",i);
            System.out.printf("Sent to  : %s\n", eachEnquiry.getSentTo().getName());
            System.out.printf("Question : %s\n", eachEnquiry.getQuestion());
            System.out.printf("Answer   : %s\n", eachEnquiry.getAnswer());
            i++;
        }
    }

    public static void editEnquiry(Student user){       
        int enquiryIndex = Helper.readInt("Select Enquiry to edit : ");
        System.out.printf("---------------------------------------------------------\n");
        if (enquiryIndex > 0 && enquiryIndex <=user.getEnquiriesMade().size()){
            Enquiry target = user.getEnquiriesMade().get(enquiryIndex-1);
            if (target.getAnswer() != null){
                System.out.printf("Enquiry has already be answered, unable to edit\n");
            }
            else{
                String new_question = Helper.readString("Input new question : ");
                target.setQuestion(new_question);
                System.out.printf("Successfully editted enquiry\n");
            }
        }
        else{System.out.println("Invalid choice");}
    }

    public static void deleteEnquiry(Student user){
        int enquiryIndex = Helper.readInt("Select Enquiry to delete : ");
                System.out.printf("---------------------------------------------------------\n");
                //delete the suggestion
                if (enquiryIndex > 0 && enquiryIndex <=user.getEnquiriesMade().size()){
                    Enquiry target = user.getEnquiriesMade().get(enquiryIndex-1);
                    if (target.getAnswer() != null){
                        System.out.printf("Enquiry has already been answered, unable to delete\n");
                    }
                    else{
                        target.getSentTo().removeEnquiry(target);
                        user.getEnquiriesMade().remove(target);
                        System.out.printf("Enquiry successfully deleted\n");
                    }
                }
                else{System.out.println("Invalid choice");}
    }

    public static void viewCampEnquiries(User user){
        int i = 1;
        if (user instanceof Student){
            Student StudentUser = (Student) user;
            System.out.printf("Enquiries made to : %s\n", StudentUser.getCommitteeOf().getName());
            System.out.printf("---------------------------------------------------------\n");
            for (Enquiry eachEnquiry : StudentUser.getCommitteeOf().getEnquiries()){
                System.out.printf("%d. %s\n",i, eachEnquiry.getQuestion());
                System.out.printf("Answer   : %s\n", eachEnquiry.getAnswer());
                i++;
            }
        }
        else{
            // INSERT CODE FOR STAFF ANSWER ENQUIRY HERE
        }
    }

    public static void answerEnquiry(User user){
        if (user instanceof Student){
                Student StudentUser = (Student) user;
                int enquiryIndex = Helper.readInt("Select Enquiry to answer : ");
                System.out.printf("---------------------------------------------------------\n");
                //answer the suggestion
                if ( enquiryIndex > 0 && enquiryIndex <= StudentUser.getCommitteeOf().getEnquiries().size()){
                    Enquiry target = StudentUser.getCommitteeOf().getEnquiries().get(enquiryIndex-1);
                    if (target.getAnswer() != null){
                        System.out.printf("Enquiry has already been answered\n");
                    }
                    else{
                        target.setAnswer(Helper.readString("Input answer to enquiry : "));
                        System.out.printf("---------------------------------------------------------\n");
                        System.out.printf("Successfully answered enquiry : %s\n",target.getQuestion());
                    }
                }
                else{System.out.println("Invalid choice");}
        }
    }
}
