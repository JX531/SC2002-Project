public class SuggestionManager {
    //For committee members (Student class)
    public static void submitSuggestion(Student user){
        //get camp that user is committee in
        Camp camp = user.getCommitteeOf();
        //create new suggestion with input
        Suggestion new_suggestion = new Suggestion(Helper.readString("Input suggestion : "), user);
        //add new suggestion to camp's suggestion list
        camp.addSuggestion(new_suggestion);
        //add new suggestion to user's suggestions made list
        user.addSuggestionsMade(new_suggestion);
    }

    //For committee members (Student class)
    public static void viewSuggestions(Student user){
        System.out.printf("Suggestions made\n");
        System.out.printf("---------------------------------------------------------\n");
        int i = 1;
        //for each suggestion in user's suggestions made list
        for (Suggestion eachSuggestion:user.getSuggestionsMade()){
            //print index and suggestion
            System.out.printf("%d. %s\n", i, eachSuggestion.getSuggestion());
            //print status of approved
            System.out.printf("Approved : %s\n", eachSuggestion.getApproved());
            //increment index
            i++;
        }
    }

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
            if (target.getApproved() == true){
                System.out.printf("Suggestion already approved, unable to edit\n");
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
            if (target.getApproved() == true){
                System.out.printf("Suggestion already approved, unable to delete\n");
            }
            else{
                //remove target suggestion from the suggestion list of the camp the user is committee in
                user.getCommitteeOf().removeSuggestion(target);
                //remove target suggestion from the user's suggestions made list
                user.getSuggestionsMade().remove(target);
                System.out.printf("Successfully deleted suggestion\n");
            }
        }
        //index invalid
        else{System.out.println("Invalid choice");}
    }
}
