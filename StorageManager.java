import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Manager class that manages operations for storage and loading of data to/from txt files for data persistancy
 */
public class StorageManager {

	/**Loads exiting data from files into CAMS
	 * @param camps master list of camps to load data into
	 * @param staff master list of staff to load data into
	 * @param students master list of students to load data into
	 */
	public static void loadData(ArrayList<Camp> camps, ArrayList<Staff> staff, ArrayList<Student> students) {
		ArrayList<String> studentData = readData("data/studentData.txt");
		ArrayList<String> staffData = readData("data/staffData.txt");
		ArrayList<String> campData = readData("data/campData.txt");
		ArrayList<String> suggestionData = readData("data/suggestionData.txt");
		ArrayList<String> enquiresData = readData("data/enquiresData.txt");
		
		ArrayList<String[]> studentRaw = new ArrayList<String[]>();
		while (studentData.size() != 0) {
			String[] temp = new String[8];
			
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
		
		ArrayList<String[]> staffRaw = new ArrayList<String[]>();
		while (staffData.size() != 0) {
			String[] temp = new String[5];
			
			temp[0] = staffData.remove(0); // name
			temp[1] = staffData.remove(0); // userID
			temp[2] = staffData.remove(0); // password
			temp[3] = staffData.remove(0); // faculty
			temp[4] = staffData.remove(0); //ownedCampSTR
			staffData.remove(0);
			staffRaw.add(temp);
		}
		for (String[] i : staffRaw) {
			Staff tempStaff = new Staff(i[0], i[1], i[3]);
			tempStaff.setPassword(i[2]);
			staff.add(tempStaff);
		}
		
		ArrayList<String[]> campRaw = new ArrayList<String[]>();
		while (campData.size() != 0) {
			String[] temp = new String[14];
			
			temp[0] = campData.remove(0); //name
			temp[1] = campData.remove(0); //userGroup
			temp[2] = campData.remove(0); //location
			temp[3] = campData.remove(0); //description
			temp[4] = campData.remove(0); //startDate
			temp[5] = campData.remove(0); //endDate
			temp[6] = campData.remove(0); //registerDate
			temp[7] = campData.remove(0); //slot
			temp[8] = campData.remove(0); //remainingCommittee
			temp[9] = campData.remove(0); //studentList
			temp[10] = campData.remove(0); //committee
			temp[11] = campData.remove(0); //staffInCharge
			temp[12] = campData.remove(0); //ownSchool
			temp[13] = campData.remove(0); //visibility
			campData.remove(0);
			campRaw.add(temp);
		}
		for (String[] i : campRaw) {
			ArrayList<Student> studentList = new ArrayList<Student>();
			for (String i2 : i[9].split(";")) {
				if (!(i2.equals(""))) studentList.add(searchStudent(students, i2));
			}
			
			ArrayList<Student> committee = new ArrayList<Student>();
			for (String i2 : i[10].split(";")) {
				if (!(i2.equals(""))) studentList.add(searchStudent(students, i2));
			}
			
			Staff inCharge = searchStaff(staff, i[11]);
			
			Camp tempCamp = new Camp(i[0], i[1], i[2], i[3], parseDate(i[4]), parseDate(i[5]), parseDate(i[6]), Integer.parseInt(i[7]), Integer.parseInt(i[8]), studentList, committee, new ArrayList<Enquiry>(), new ArrayList<Suggestion>(), inCharge, Boolean.valueOf(i[12]), Boolean.valueOf(i[13]));
			camps.add(tempCamp);
		}
		
		for (String[] i : studentRaw) {
			Student curr = searchStudent(students, i[0]);
			for (String i2 : i[4].split(";")) {
				if (!(i2.equals(""))) curr.getRegisteredCamps().add(searchCamp(camps, i2));
			}
			for (String i2 : i[5].split(";")) {
				if (!(i2.equals(""))) curr.getWithdrawnCamps().add(searchCamp(camps, i2));
			}
			curr.setCommitteeOf(searchCamp(camps, i[6]));
		}
		
		for (String[] i : staffRaw) {
			Staff curr = searchStaff(staff, i[0]);
			for (String i2: i[4].split(";")) {
				if (!(i2.equals(""))) curr.getCampsOwned().add(searchCamp(camps, i2));
			}
		}
		
		ArrayList<String[]> suggestionRaw = new ArrayList<String[]>();
		while (suggestionData.size() != 0) {
			String[] temp = new String[5];
			
			temp[0] = suggestionData.remove(0); //suggestion
			temp[1] = suggestionData.remove(0); //approved
			temp[2] = suggestionData.remove(0); //processed
			temp[3] = suggestionData.remove(0); //createdBy
			temp[4] = suggestionData.remove(0); //sentTo
			suggestionData.remove(0);
			suggestionRaw.add(temp);
		}
		for (String[] i : suggestionRaw) {
			Student createdBy = searchStudent(students, i[3]);
			Camp sentTo = searchCamp(camps, i[4]);
			Suggestion curr = new Suggestion(i[0], createdBy, sentTo, Boolean.valueOf(i[2]), Boolean.valueOf(i[1]));
			createdBy.getSuggestionsMade().add(curr);
			sentTo.getSuggestions().add(curr);
		}
		
		ArrayList<String[]> enquiresRaw = new ArrayList<String[]>();
		while (enquiresData.size() != 0) {
			String[] temp = new String[4];
			
			temp[0] = enquiresData.remove(0); //question
			temp[1] = enquiresData.remove(0); //answer
			temp[2] = enquiresData.remove(0); //sentTo
			temp[3] = enquiresData.remove(0); //createdBy
			enquiresData.remove(0);
			enquiresRaw.add(temp);
		}
		for(String[] i : enquiresRaw) {
			Student createdBy = searchStudent(students, i[3]);
			Camp sentTo = searchCamp(camps, i[2]);
			Enquiry curr = new Enquiry(i[0], i[1], createdBy, sentTo);
			createdBy.getEnquiriesMade().add(curr);
			sentTo.getEnquiries().add(curr);
		}
		
	}

	/**Saves existing data from CAMs into .txt files
	 * @param camps masterlist of camps to save data from
	 * @param staff masterlist of staff to save data from
	 * @param students masterlist of students to save data from
	 */
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
			
			dataStr = "";
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
		writeData("data/studentData.txt", data);
		data.clear();
		
		//data.add("name");
		//data.add("userID");
		//data.add("password");
		//data.add("faculty");
		//data.add("campsOwned");[]
		//data.add("");
		for (Staff i : staff) {
			data.add(i.getName());
			data.add(i.getID());
			data.add(i.getPassword());
			data.add(i.getFaculty());;
			
			String dataStr = "";
			for (Camp i2 : i.getCampsOwned()) {
				dataStr += i2.getName();
				dataStr += ";";
			}
			data.add(dataStr);
			
			data.add("");
		}
		writeData("data/staffData.txt", data);
		data.clear();
		
		//data.add("name");
		//data.add("userGroup");
		//data.add("location");
		//data.add("description");
		//data.add("startDate");
		//data.add("endDate");
		//data.add("registerDate");
		//data.add("slot");
		//data.add("remainingCommittee");
		//data.add("studentList");[]
		//data.add("committee");[]
		//data.add("staffInCharge");
		//data.add("ownSchool");
		//data.add("visibility");
		//data.add("");
		for (Camp i : camps) {
			data.add(i.getName());
			data.add(i.getUserGroup());
			data.add(i.getLocation());
			data.add(i.getDescription());
			data.add(encodeDate(i.getStartDate()));
			data.add(encodeDate(i.getEndDate()));
			data.add(encodeDate(i.getRegisterDate()));
			data.add(Integer.toString(i.getSlots()));
			data.add(Integer.toString(i.getRemainingCommittee()));
			
			String dataStr = "";
			for (Student i2: i.getStudentList()) {
				dataStr += i2.getName();
				dataStr += ";";
			}
			data.add(dataStr);
			
			dataStr = "";
			for (Student i2: i.getCommitteeList()) {
				dataStr += i2.getName();
				dataStr += ";";
			}
			data.add(dataStr);
			
			data.add(i.getStaffinCharge().getName());
			data.add(i.getOwnSchool().toString());
			data.add(i.getVisibility().toString());
			data.add("");
		}
		writeData("data/campData.txt", data);
		data.clear();
		
		for (Camp i : camps) {
			for (Suggestion i2: i.getSuggestions()) {
				//data.add("suggestion");
				//data.add("approved");
				//data.add("processed");
				//data.add("createdby");
				//data.add("sentTo");
				//data.add("");
				data.add(i2.getSuggestion());
				data.add(i2.getApproved().toString());
				data.add(i2.getProcessed().toString());
				data.add(i2.getCreatedby().getName());
				data.add(i2.getSentTo().getName());
				data.add("");
			}
			
		}
		writeData("data/suggestionData.txt", data);
		data.clear();
		
		for (Camp i : camps) {
			for (Enquiry i2: i.getEnquiries()) {
				//data.add("question");
				//data.add("answer");
				//data.add("sentTo");
				//data.add("createdBy");
				//data.add("");
				data.add(i2.getQuestion());
				if (i2.getAnswer() == null) data.add("");
				else data.add(i2.getAnswer());
				data.add(i2.getSentTo().getName());
				data.add(i2.getCreatedBy().getName());
				data.add("");
			}
		}
		writeData("data/enquiresData.txt", data);
		data.clear();
	}

	/**reads a file and compiles it into an array list to be loaded into CAMs
	 * @param name path of file
	 * @return array list of loaded data
	 */
	private static ArrayList<String> readData(String name) {
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

	/**write to a txt file from CAMs to store data
	 * @param name path of the storage txt file
	 * @param data data to write
	 */
	private static void writeData(String name, ArrayList<String> data) {
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

	/**parses date data from format saved in txt into LocalDate
	 * @param date date to be parsed
	 * @return LocalDate format date
	 */
	private static LocalDate parseDate(String date) {
		String[] dateArr = date.split(";");
		return LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
	}

	/**Encodes LocalDate into a format to be stored into txt files
	 * @param date date to be encoded
	 * @return encoded date to be stored
	 */
	private static String encodeDate(LocalDate date) {
		String out = "";
		out += Integer.toString(date.getYear());
		out += ";";
		out += Integer.toString(date.getMonthValue());
		out += ";";
		out += Integer.toString(date.getDayOfMonth());
		out += ";";
		return out;
	}

	/**Find a student with the matching name from a list of students
	 * @param students list of students to search through
	 * @param name name of student being searched
	 * @return  the student object
	 */
	private static Student searchStudent(ArrayList<Student> students, String name) {
		for (Student i : students) {
			if (i.getName().equals(name)) return i; 
		}
		return null;
	}

	/**Find a staff with the matching name from a list of staff
	 * @param staff list of staff to search through
	 * @param name name of staff being searched
	 * @return  the staff object
	 */
	private static Staff searchStaff(ArrayList<Staff> staff, String name) {
		for (Staff i : staff) {
			if (i.getName().equals(name)) return i; 
		}
		return null;
	}

	/**Find a camp with the matching name from  a list of camps
	 * @param camps list of camps to search through
	 * @param name name of camp being searched
	 * @return the camp object
	 */
	private static Camp searchCamp(ArrayList<Camp> camps, String name) {
		for (Camp i : camps) {
			if (i.getName().equals(name)) return i; 
		}
		return null;
	}
}
