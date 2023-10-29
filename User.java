public class User{
    private String ID;
    private String Password;
    private String Faculty;

    public User(String ID, String Faculty){
        this.ID = ID;
        this.Password = "Password";
        this.Faculty = Faculty;
    }
    public String getID(){
        return this.ID;
    }
    public String getFaculty(){
        return this.Faculty;
    }
    public String getPassword(){
        return this.Password;
    }
    
    public void ChangePassword(String NewPassword){
        this.Password = NewPassword;
    }
}
