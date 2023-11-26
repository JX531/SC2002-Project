import java.util.ArrayList;;

public class CampApp {
	
	public static void main(String[] args) {
		ArrayList<Camp> camps = new ArrayList<Camp>();
		ArrayList<Staff> staff = new ArrayList<Staff>();
		ArrayList<Student> students = new ArrayList<Student>();

		while (true) {
			StorageManager.loadData(camps, staff, students);
			String username = Helper.readString("Login(exit to exit) >");
			if (username.equalsIgnoreCase("exit")) break;
			
			for (Staff i : staff) {
				if (username.equalsIgnoreCase(i.getID())) {
					if (i.checkPassword(Helper.readString("Enter password >"))) i.menu(camps);
				}
			}
			
			for (Student i : students) {
				if (username.toLowerCase().equalsIgnoreCase(i.getID())) {
					if (i.checkPassword(Helper.readString("Enter password >"))) i.menu(camps);
				}
			}
			StorageManager.saveData(camps, staff, students);
		}
	}
}
