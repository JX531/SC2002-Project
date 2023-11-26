import java.util.ArrayList;;

public class CampApp {
	
	public static void main(String[] args) {
		ArrayList<Camp> camps = StorageManager.readCamps();
		ArrayList<Staff> staff = StorageManager.readStaff();
		ArrayList<Student> students = StorageManager.readStudents();

		while (true) {
			students.add(new Student("Bob", "bob@e.ntu.sg", "SCSE"));
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
			StorageManager.saveCamps(camps);
			StorageManager.saveStaff(staff);
			StorageManager.saveStudents(students);
		}
	}
}
