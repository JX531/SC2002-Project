import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class StorageManager {

	public static void loadData(ArrayList<Camp> camps, ArrayList<Staff> staff, ArrayList<Student> students) {
		ArrayList<String> studentData = readCSV("data/studentData.txt");
		for (String i : studentData) {
			
		}
	}
	
	public static void saveData(ArrayList<Camp> camps, ArrayList<Staff> staff, ArrayList<Student> students) {
		ArrayList<String> data = new ArrayList<String>();
		for (Student i : students) {
			
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
