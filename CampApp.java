import java.util.ArrayList;
import java.time.LocalDate;

public class CampApp {
	
	public static ArrayList<Student> read_student(){
		ArrayList<Student> students = new ArrayList<Student>();
		//test student
		students.add(new Student("BOB", "BOB12","EEE"));
		
		return students;
	}
	
	public static ArrayList<Staff> read_staff(){
		ArrayList<Staff> staff = new ArrayList<Staff>();
		//test staff
		staff.add(new Staff("Staff 1","STAFFID 1", "SCSE"));
		return staff;
		
	}
	
	public static void main(String[] args) {
		ArrayList<Camp> camps = new ArrayList<Camp>();
		ArrayList<Staff> staff = read_staff();
		ArrayList<Student> students = read_student();
		Updater.updateStudents(students);


		//test camps
		Camp testCamp1 = new Camp("abc", "SCSE","def","test, comma", LocalDate.of(2022, 12, 5),
    	    LocalDate.of(2022, 12, 5),
    	    LocalDate.of(2024, 11, 28), 10, staff.get(0), false);
		Camp testCamp2 = new Camp("bcd", "SCSE","abc","test", LocalDate.of(2022, 12, 2),
            LocalDate.of(2022, 12, 7),
            LocalDate.of(2024, 11, 28), 35, staff.get(0), false);    
		Camp testCamp3 = new Camp("cde", "SCSE","cde","test", LocalDate.of(2022, 12, 6),
            LocalDate.of(2022, 12, 7),
            LocalDate.of(2024, 11, 28), 35, staff.get(0), false);   
		Camp testCamp4 = new Camp("def", "SCSE","bcd","test", LocalDate.of(2022, 12, 3),
            LocalDate.of(2022, 12, 7),
            LocalDate.of(2024, 11, 28), 35, staff.get(0), false);
		
		// add test camps to masterlist
		camps.add(testCamp1);
		camps.add(testCamp2);
		camps.add(testCamp3);
		camps.add(testCamp4);
		
		//add test camps to test staff
		for (Camp eachCamp:camps){
			staff.get(0).addOwnedCamp(eachCamp);
		}


		while (true) {
			students.get(0).menu(camps);
			System.out.println(" debug logged out");
		}
	}
}
