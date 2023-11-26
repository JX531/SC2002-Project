import java.util.ArrayList;

/**
 * User class represents generic user in system
 */
public abstract class User {
    /**
     * User's Name
     */
    private String Name;
    /**
     * User's userID
     */
    private String userID;
    /**
     * User's password
     */
    private String password;
    /**
     * User's faculty
     */
    private String faculty;

    /**Constructs a new user with the following attributes
     * @param name name of the user
     * @param userID ID of the user, which is their email dropping the @e.ntu.edu.sg
     * @param faculty faculty of user
     * password defaults to 'password' for this constructor
     */
    public User(String name,String userID, String faculty) {
        this.Name = name;
        this.userID = userID;
        this.faculty = faculty;
        this.password = "password";
    }

    /**Overloaded constructor to allow for inputs for every variable
     * @param name name of user
     * @param userID ID of user
     * @param faculty faculty of user
     * @param password password of user
     */
    //Overloaded
    public User(String name,String userID, String faculty, String password) {
        this.Name = name;
        this.userID = userID;
        this.faculty = faculty;
        this.password = password;
    }


    /**getter for user's userID
     * @return user's userID
     */
    public String getID() {
        return this.userID;
    }


    /**getter for user's name
     * @return user's name
     */
    public String getName(){
        return this.Name;
    }

    /**getter for user's password
     * @return user's password
     */
    public String getPassword(){
        return this.password;
    }

    /**setter for user's password
     * @param password password to set user's password to
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**used to check if user's password is correct when logging in
     * @param password the password user inputs when logging in
     * @return true if input password matches user's correct password stored in program, false if not
     */
    public boolean checkPassword(String password) {
        return (this.password.equals(password));
    }

    /**getter for user's faculty
     * @return user's faculty
     */
    public String getFaculty() {
        return this.faculty;
    }



    /**User's Menu that is called in main to provide functionality of the app
     * @param CampMasterList master list of all camps created to be used in menu operations
     */
    public abstract void menu(ArrayList<Camp> CampMasterList);
}
