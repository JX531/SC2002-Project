import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class StorageManager {

	public static void loadData(ArrayList<Camp> camps, ArrayList<Staff> staff, ArrayList<Student> students) {
		ArrayList<String> studentData = readCSV("data/studentData.txt");
		ArrayList<String[]> studentRaw = new ArrayList<String[]>();
		while (studentData.size() != 0) {
			String[] temp = new String[8];
			System.out.println(studentData.size());
			temp[0] = studentData.remove(0); // name
			temp[1] = studentData.remove(0); // userID
			temp[2] = studentData.remove(0); // password
			temp[3] = studentData.remove(0); // faculty
			temp[4]= studentData.remove(0); // registeredCampsSTR
			temp[5] = studentData.remove(0);// withdrawnCampsSTR
			temp[6] = studentData.remove(0);// committeeOfSTR
			temp[7] = studentData.remove(0);// pointSTR
			studentData.remove(0);
			studentRaw.add(temp);
		}
		for (String[] i : studentRaw) {
			students.add(new Student(i[0], i[1], i[3], i[2], new ArrayList<Camp>(), new ArrayList<Camp>(),new ArrayList<Enquiry>(), new ArrayList<Suggestion>(), null, Integer.parseInt(i[7])));
		}
	}
	
	public static void saveData(ArrayList<Camp> camps, ArrayList<Staff> staff, ArrayList<Student> students) {
		ArrayList<String> data = new ArrayList<String>();
		//data.add("name");
		//data.add("userID");
		//data.add("password");
		//data.add("faculty");
		//data.add("registeredCamps");[]
		//data.add("withdrawnCamps");[]
		//data.add("committeeOf");
		//data.add("point");
		//data.add("");
		for (Student i : students) {
			data.add(i.getName());
			data.add(i.getID());
			data.add(i.getPassword());
			data.add(i.getFaculty());
			
			String dataStr = "";
			for (Camp i2 : i.getRegisteredCamps()) {
				dataStr += i2.getName();
				dataStr += ";";
			}
			data.add(dataStr);
			
			for (Camp i2 : i.getWithdrawnCamps()) {
				dataStr += i2.getName();
				dataStr += ";";
			}
			data.add(dataStr);
			
			if (i.getCommitteeOf() == null) data.add("");
			else data.add(i.getCommitteeOf().getName());
			
			data.add(Integer.toString(i.getPoint()));
			
			data.add("");
		}
		writeCSV("data/studentData.txt", data);
		
	}
	
	private static ArrayList<String> readCSV(String name) {
		ArrayList<String> data = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(name));
			String line;
			while ((line = reader.readLine()) != null) {
				data.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	private static void writeCSV(String name, ArrayList<String> data) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(name, false));
			for (String i : data) {
				writer.write(i);
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
