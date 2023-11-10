public class Student extends User {
    private ArrayList<Camp> registeredCamps = new ArrayList<>();
    private ArrayList<Camp> withdrawnCamps = new ArrayList<>();
    private Camp committeeOf;

    public Student(String userID, String password, String faculty) {
        super(userID, password, faculty);
    }

    public void addRegisteredCamp(Camp camp) {
        registeredCamps.add(camp);
    }

    public void removeRegisteredCamp(int index) {
        if (index >= 0 && index < registeredCamps.size()) {
            registeredCamps.remove(index);
        }
    }

    public void addWithdrawnCamp(Camp camp) {
        withdrawnCamps.add(camp);
    }

    public void setCommitteeOf(Camp camp) {
        committeeOf = camp;
    }

    public void viewRegisteredCamps() {
        for (Camp camp : registeredCamps) {
            System.out.println(camp);
        }
    }

    @Override
    public void menu() {
    }
}
