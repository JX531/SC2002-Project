import java.util.ArrayList;
import java.util.List;

public class Staff extends User {

    private List<Camp> campsOwned;

    public Staff(String userId, String password, String faculty) {
        super(userId, password, faculty);
        this.campsOwned = new ArrayList<>();
    }

    public void addOwnedCamp(Camp camp) {
        campsOwned.add(camp);
    }

    public boolean removeOwnedCamp(Camp camp) {
        return campsOwned.remove(camp);
    }

    public List<Camp> viewOwnedCamps() {
        return new ArrayList<>(campsOwned);
    }

    public void replyToInquiry(Inquiry inquiry, String reply) {
        if (campsOwned.contains(inquiry.getCamp()) && !inquiry.isProcessed()) {
            inquiry.setReply(reply);
            inquiry.setProcessed(true);
        }
    }

    public void reviewSuggestion(Suggestion suggestion, boolean approve) {
        if (campsOwned.contains(suggestion.getCamp())) {
            suggestion.setApproved(approve);
        }
    }

    public void generateReport() {
    }


}
