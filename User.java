public abstract class User {
    private String userID;
    private String password;
    private String faculty;

    public User(String userID, String faculty) {
        this.userID = userID;
        this.faculty = faculty;
        password = "password";
    }

    public String getID() {
        return this.userID;
    }

    public boolean checkPassword(String password) {
        return (this.password.equals(password));
    }

    public String getFaulty() {
        return this.faculty;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void menu(ArrayList<Camp> CampMasterList) {}
}
