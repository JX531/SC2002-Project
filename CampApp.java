import java.util.ArrayList;;

public class CampApp {
	
	public static void main(String[] args) {
		ArrayList<Camp> camps = new ArrayList<Camp>();
		ArrayList<Staff> staff = new ArrayList<Staff>();
		ArrayList<Student> students = new ArrayList<Student>();
		StorageManager.loadData(camps, staff, students);
		// students.add(new Student("CHERN","YCHERN@e.ntu.edu.sg","SCSE"));
		// students.add(new Student("KOH", "KOH1@e.ntu.edu.sg", "ADM"));
		// students.add(new Student("BRANDON", "BR015@e.ntu.edu.sg", "EEE"));
		// students.add(new Student("CALVIN", "CT113@e.ntu.edu.sg", "SCSE"));
		// students.add(new Student("CHAN", "YCN019@e.ntu.edu.sg", "NBS"));
		// students.add(new Student("DENISE", "DL007@e.ntu.edu.sg", "SCSE"));
		// students.add(new Student("DONG", "DON84@e.ntu.edu.sg", "ADM"));
		// students.add(new Student("ERNEST", "ELI34@e.ntu.edu.sg", "EEE"));
		// students.add(new Student("LEE", "LE51@e.ntu.edu.sg", "SCSE"));
		// students.add(new Student("LIU", "SL22@e.ntu.edu.sg", "NBS"));
		// students.add(new Student("RAWALL", "AKY013@e.ntu.edu.sg", "SSS"));
		// students.add(new Student("SCSE Student","SCSE_student","SCSE"));
		// students.add(new Student("EEE Student","EEE_Student","EEE"));

		// staff.add(new Staff("Madhukumar", "HUKUMAR@NTU.EDU.SG", "SCSE"));
		// staff.add(new Staff("Alexei", "OURIN@ntu.edu.sg", "ADM"));
		// staff.add(new Staff("Chattopadhyay", "UPAM@NTU.EDU.SG", "EEE"));
		// staff.add(new Staff("Datta", "ANWIT@NTU.EDU.SG", "SCSE"));
		// staff.add(new Staff("Arvind", "ARVI@NTU.EDU.SG", "NBS"));

		// staff.add(new Staff("SCSE Staff", "scse_staff", "SCSE"));
		// staff.add(new Staff("EEE_staff", "EEE_staff", "EEE"));
		
		
		while (true) {
			StorageManager.saveData(camps, staff, students);
			
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
			
		}
	}
}
