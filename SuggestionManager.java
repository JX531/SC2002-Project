public class SuggestionManager {
    public static void submitSuggestion(Student user){
        Camp camp = user.getCommitteeOf();
        Suggestion new_suggestion = new Suggestion(Helper.readString("Input suggestion : "), user);
        camp.addSuggestion(new_suggestion);
        user.addSuggestionsMade(new_suggestion);
    }

    public static void viewSuggestions(Student user){
        System.out.printf("Suggestions made\n");
                System.out.printf("---------------------------------------------------------\n");
                int i = 1;
                for (Suggestion eachSuggestion:user.getSuggestionsMade()){
                    System.out.printf("%d. %s\n", i, eachSuggestion.getSuggestion());
                    System.out.printf("Approved : %s\n", eachSuggestion.getApproved());
                    i++;
                }
    }

    public static void editSuggestion(Student user){
        int suggestionIndex = Helper.readInt("Select suggestion to edit : ");
        System.out.printf("---------------------------------------------------------\n");
        if (suggestionIndex > 0 && suggestionIndex <= user.getSuggestionsMade().size()){
            Suggestion target = user.getSuggestionsMade().get(suggestionIndex-1);
            if (target.getApproved() == true){
                System.out.printf("Suggestion already approved, unable to edit\n");
            }
            else{
                target.setSuggestion(Helper.readString("Input new suggestion : "));
                System.out.printf("Successfully editted suggestion\n");
            }
        }
        else{System.out.println("Invalid choice");}
    }

    public static void deleteSuggestion(Student user){
        int suggestionIndex = Helper.readInt("Select suggestion to edit : ");
        System.out.printf("---------------------------------------------------------\n");
        if (suggestionIndex > 0 && suggestionIndex <= user.getSuggestionsMade().size()){
            Suggestion target = user.getSuggestionsMade().get(suggestionIndex-1);
            if (target.getApproved() == true){
                System.out.printf("Suggestion already approved, unable to delete\n");
            }
            else{
                user.getCommitteeOf().removeSuggestion(target);
                user.getSuggestionsMade().remove(target);
                System.out.printf("Successfully deleted suggestion\n");
            }
        }
        else{System.out.println("Invalid choice");}
    }
}
