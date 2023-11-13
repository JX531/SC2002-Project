public abstract class User {
    private String userID;
    private String password;
    private String faculty;

    public User(String userID, String password, String faculty) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
    }

    public String getID() {
        return this.userID;
    }

    public boolean checkPassword(String password) {
        return (this.password == password);
    }

    public String getFaulty() {
        return this.faculty;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void menu() {
        
    }
}