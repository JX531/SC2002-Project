import java.util.ArrayList;

// Staff Class
public class Staff extends User {
    private ArrayList<Camp> campsOwned = new ArrayList<>();

    public Staff(String userID, String password, String faculty) {
        super(userID, password, faculty);
    }

    public void addOwnedCamp(Camp camp) {
        campsOwned.add(camp);
    }

    public void removeOwnedCamps(int index) {
        if (index >= 0 && index < campsOwned.size()) {
            campsOwned.remove(index);
        }
    }

    public void viewOwnedCamps() {
        for (Camp camp : campsOwned) {
            System.out.println(camp);
        }
    }

    @Override
    public void menu() {
        // Implement the menu here
    }
}
