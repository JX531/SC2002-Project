/**
 * Class used to manage suggestions
 */
public class SuggestionManager {
    /**Submit a suggestion
     * @param user the user that is submitting the suggestion
     */
    //For committee members (Student class)
    public static void submitSuggestion(Student user){
        //get camp that user is committee in
        Camp camp = user.getCommitteeOf();
        //create new suggestion with input
        Suggestion new_suggestion = new Suggestion(Helper.readString("Input suggestion : "), user, user.getCommitteeOf());
        //add new suggestion to camp's suggestion list
        camp.addSuggestion(new_suggestion);
        //add new suggestion to user's suggestions made list
        user.addSuggestionsMade(new_suggestion);
    }

    /**View suggestions made by user
     * @param user user that is viewing their suggestions made
     */
    //For committee members (Student class)
    public static void viewSuggestionsMade(Student user){
        System.out.printf("Suggestions made\n");
        System.out.printf("---------------------------------------------------------\n");
        int i = 1;
        //for each suggestion in user's suggestions made list
        for (Suggestion eachSuggestion:user.getSuggestionsMade()){
            //print index and suggestion
            System.out.printf("%d. %s\n", i, eachSuggestion.getSuggestion());
            //print status of procssed
            System.out.printf("Processed : %s\n", eachSuggestion.getProcessed());
            //print status of approved
            System.out.printf("Approved : %s\n", eachSuggestion.getApproved());
            //increment index
            i++;
        }
    }

    /**Edit a suggestion made by user
     * @param user user that is trying to edit their suggestion
     */
    //For committee members (Student class)
    public static void editSuggestion(Student user){
        //get index of suggestion from user's suggestion index list to edit 
        int suggestionIndex = Helper.readInt("Select suggestion to edit : ");
        System.out.printf("---------------------------------------------------------\n");
        //check if given index is in valid range
        if (suggestionIndex > 0 && suggestionIndex <= user.getSuggestionsMade().size()){
            //select target suggestion for easier readability
            Suggestion target = user.getSuggestionsMade().get(suggestionIndex-1);
            //check if target suggestion has already been approved
            if (target.getProcessed() == true){
                System.out.printf("Suggestion already processed, unable to edit\n");
            }
            else{
                //set target suggestions' suggestion as new input
                target.setSuggestion(Helper.readString("Input new suggestion : "));
                System.out.printf("Successfully editted suggestion\n");
            }
        }
        //index invalid 
        else{System.out.println("Invalid choice");}
    }

    /**Delete a suggestion made by user
     * @param user user that is trying to delete their suggestion
     */
    //For committee members (Student class)
    public static void deleteSuggestion(Student user){
        //get index of suggestion from user's suggestion index list to edit 
        int suggestionIndex = Helper.readInt("Select suggestion to edit : ");
        System.out.printf("---------------------------------------------------------\n");
        //check if given index is in valid range
        if (suggestionIndex > 0 && suggestionIndex <= user.getSuggestionsMade().size()){
            //select target suggestion for easier readability
            Suggestion target = user.getSuggestionsMade().get(suggestionIndex-1);
            //check if target suggestion has already been approved
            if (target.getProcessed() == true){
                System.out.printf("Suggestion already processed, unable to delete\n");
            }
            else{
                //remove target suggestion from the suggestion list of the camp the user is committee in
                user.getCommitteeOf().removeSuggestion(target);
                //remove target suggestion from the user's suggestions made list
                user.removeSuggestionsMade(target);
                System.out.printf("Successfully deleted suggestion\n");
            }
        }
        //index invalid
        else{System.out.println("Invalid choice");}
    }

    
    public static void viewSuggestionsofCamp(Camp camp){
        // Check if there are suggestions for the selected camp
        if (camp.getSuggestions().isEmpty()) {
            System.out.println("There are no suggestions for this camp.");
            return;
        }

        // Display suggestions for the selected camp
        System.out.println("Suggestions for " + camp.getName() + ":");
        int i = 1;
        for (Suggestion suggestion : camp.getSuggestions()) {
            System.out.println("---------------------------------------------------------");
            System.out.printf("%d, Suggestion: %s\n",i, suggestion.getSuggestion());
            System.out.println("Created by: " + suggestion.getCreatedby().getName());
            System.out.println("Status: " + (suggestion.getProcessed() ? "Processed" : "Unprocessed") + ", " +
                               (suggestion.getApproved() ? "Approved" : "Not Approved"));
        }
    }
    public static void processSuggestionsofCamp(Camp camp){
        int suggestionIndex = Helper.readInt("Select a suggestion to process")-1;
        if (suggestionIndex < 0 || suggestionIndex >= camp.getSuggestions().size()) {
            System.out.println("Invalid suggestion number. Please try again.");
            return;
        }
        Suggestion target = camp.getSuggestions().get(suggestionIndex);
        if (target.getProcessed() == true){
            System.out.println("Suggestion already processed. Please try again.");
            return;
        }
        System.out.println("Approve suggestion?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = Helper.readInt("> ");
        if (choice <0 || choice >2){
            System.out.println("Invalid choice");
            return;
        }
        if (choice == 1){
            target.approve();
            target.getCreatedby().addPoint();
        }
        else{
            target.disapprove();
        }
    }
}
