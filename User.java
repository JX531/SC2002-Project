import java.util.ArrayList;

public abstract class User {
    private String Name;
    private String userID;
    private String password;
    private String faculty;

    public User(String name,String userID, String faculty) {
        this.Name = name;
        this.userID = userID;
        this.faculty = faculty;
        this.password = "password";
    }
    //Overloaded
    public User(String name,String userID, String faculty, String password) {
        this.Name = name;
        this.userID = userID;
        this.faculty = faculty;
        this.password = password;
    }


    public String getID() {
        return this.userID;
    }

    public String getName(){
        return this.Name;
    }
    
    public String getPassword() {
		return this.password;
	}
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean checkPassword(String password) {
        return (this.password.equals(password));
    }

    public String getFaculty() {
        return this.faculty;
    }

    public abstract void menu(ArrayList<Camp> CampMasterList);
}
