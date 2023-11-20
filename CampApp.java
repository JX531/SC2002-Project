import java.util.ArrayList;


public class CampApp {
	
	public static ArrayList<Student> read_student(){
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("BOB", "SCSE"));
		
		return students;
	}
	
	public static ArrayList<Staff> read_staff(){
		ArrayList<Staff> staff = new ArrayList<Staff>();
		
		return staff;
		
	}
	
	public static void main(String[] args) {
		ArrayList<Camp> camps = new ArrayList<Camp>();
		ArrayList<Staff> staff = read_staff();
		ArrayList<Student> students = read_student();
		while (true) {
			students.get(0).menu();
		}
	}
}
