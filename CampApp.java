import java.util.Scanner;

public class CampApp {
   
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        User CurrentUser = null;
        User[] UserList; // Read from files to make user database first
        //INSERT CODE HERE//
        UserList = new User[5]; // TEMPORARY LIST
        Camp[] camplist = new Camp[100];
        while(true){

            while(CurrentUser == null){//While not logged in, make u log in
                System.out.println("Input Username");
                String Username = scan.nextLine();
                System.out.println("Input Password");
                String Password = scan.nextLine();

                for (User eachUser: UserList){ // Check each user
                    if (eachUser.getID() == Username){// Found User
                        if (eachUser.getPassword() == Password){//Password Match
                            CurrentUser = eachUser;
                        }
                    }
                }
            }//ENDWHILE NOT LOGGED IN

            while(CurrentUser instanceof Student){//Logged in as Student

            }

            


        }
    }
}
