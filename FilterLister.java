import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FilterLister {
    //Sort input camp list by location alphabetically
    private static void sortCampsLocation(ArrayList<Camp> camplist){
        //create comparator
        Comparator<Camp> locationComparator = Comparator.comparing(Camp::getLocation);
        //sort by comparator
        Collections.sort(camplist, locationComparator);
    }
    //Sort input camp list by name alphabetically
    private static void sortCampsName(ArrayList<Camp> camplist){
        //create comparator
        Comparator<Camp> nameComparator = Comparator.comparing(Camp::getName);
        //sort by comparator
        Collections.sort(camplist, nameComparator);
    }
    //Sort input camp list by start date with earliest first
    private static void sortCampsDate(ArrayList<Camp> camplist){
        //create comparator
        Comparator<Camp> dateComparator = Comparator.comparing(Camp::getStarDate);
        //sort by comparator
        Collections.sort(camplist, dateComparator);
    }

    private static void campFilterMenu(ArrayList<Camp> camps){
        //print options
        System.out.printf("---------------------------------------------------------\n");
        System.out.println("Would you like to apply a filter to the camp list?");
        System.out.println("1. Sort by Location");
        System.out.println("2. Sort by Start Date");
        System.out.println("3. No");
        //take input
        int choice = Helper.readInt("> \n");
        //if input is in valid range
        if (choice > 0 && choice <=3){
            switch (choice){
                case 1:
                //sort by location
                sortCampsLocation(camps);
                System.out.println("Sorted by Location \n");
                break;
                case 2:
                //sort by date
                sortCampsDate(camps);
                System.out.println("Sorted by Start Date \n");
                break;
                default:
                //default is sort by name alphabetically
                sortCampsName(camps);
                break;
            }
        }
        else{System.out.println("Invalid Choice");}
    }

    //print each camp and its index
    public static void printCampDetails(int index, Camp eachcamp){
        System.out.printf("---------------------------------------------------------\n");
            System.out.printf("%d.\n",index);
            System.out.printf("Name                      : %s\n",eachcamp.getName());
            System.out.printf("Faculty                   : %s\n",eachcamp.getUserGroup());
            System.out.printf("Location                  : %s\n",eachcamp.getLocation());
            System.out.printf("Attendee Slots Remaining  : %s\n",eachcamp.getSlots());
            System.out.printf("Committee Slots Remaining : %s\n",eachcamp.getRemainingCommittee());
            System.out.printf("Starts                    : %s\n",eachcamp.getStarDate());
            System.out.printf("Ends                      : %s\n",eachcamp.getEndDate());
            System.out.printf("Register before           : %s\n",eachcamp.getRegisterDate());
            System.out.printf("Description               : %s\n",eachcamp.getDescription());
    }

    //overloaded to not include index, use for single camp instead of multiple
    public static void printCampDetails(Camp eachcamp){
        System.out.printf("---------------------------------------------------------\n");
            System.out.printf("Name                      : %s\n",eachcamp.getName());
            System.out.printf("Faculty                   : %s\n",eachcamp.getUserGroup());
            System.out.printf("Location                  : %s\n",eachcamp.getLocation());
            System.out.printf("Attendee Slots Remaining  : %s\n",eachcamp.getSlots());
            System.out.printf("Committee Slots Remaining : %s\n",eachcamp.getRemainingCommittee());
            System.out.printf("Starts                    : %s\n",eachcamp.getStarDate());
            System.out.printf("Ends                      : %s\n",eachcamp.getEndDate());
            System.out.printf("Register before           : %s\n",eachcamp.getRegisterDate());
            System.out.printf("Description               : %s\n",eachcamp.getDescription());
    }

    // Lists input camp list, with option to include filters based on boolean filter
    public static void listCamp(ArrayList<Camp> camps, Boolean filter) {
        //if including filter, call filter menu
        if (filter){campFilterMenu(camps);}
        //else default sort by  names alphabetically
        else{sortCampsName(camps);}
        //for each camp in the camp list
        int i = 1;
        System.out.printf("---------------------------------------------------------\n");
        System.out.printf("%s Camps\n");
        for (Camp eachcamp:camps){
            //print camp details with index
            printCampDetails(i,eachcamp);
            //increment index
            i++;
        }
    }

    //overload to allow prefix
    public static void listCamp(ArrayList<Camp> camps, Boolean filter,String prefix) {
        //if including filter, call filter menu
        if (filter){campFilterMenu(camps);}
        //else default sort by  names alphabetically
        else{sortCampsName(camps);}
        //for each camp in the camp list
        int i = 1;
        System.out.printf("---------------------------------------------------------\n");
        System.out.printf("%s Camps\n",prefix);
        for (Camp eachcamp:camps){
            //print camp details with index
            printCampDetails(i,eachcamp);
            //increment index
            i++;
        }
    }

    //Sort input student list by name alphabetically
    private static void sortStudentName(ArrayList<Student> studentList){
        //create comparator
        Comparator<Student> nameComparator = Comparator.comparing(Student::getID);
        //sort by comparator
        Collections.sort(studentList, nameComparator);
    }
    //Sort input committee student list by point in decreasing order
    private static void sortCommitteePoint(ArrayList<Student> committeeList){
        //create comparator
        Comparator<Student> poinComparator = Comparator.comparing(Student::getPoint);
        //sort by comparator
        Collections.sort(committeeList, poinComparator);
    }
    
    //prints details of student and their index ( in a list of students )
    private static void printStudentDetails(int index, Student eachStudent){
        System.out.printf("%d.\n",index);
        System.out.printf("Name                      : %s\n",eachStudent.getID());
        System.out.printf("Faculty                   : %s\n",eachStudent.getFaculty());
        if (eachStudent.getCommitteeOf() != null){
            System.out.printf("Role                      : %s\n","Committee Member");
            System.out.printf("Points                    : %s\n",eachStudent.getPoint());
        }
        else{
            System.out.printf("Role                      : %s\n","Attendee");
        }
    }

    //generate camp report
    public static void generateCampReport(Camp camp){
        //print options to generate only for committee members / attendees only / all participants
        System.out.printf("---------------------------------------------------------\n");
        System.out.println("Generate report for \n");
        System.out.println("1. All participants \n");
        System.out.println("2. Attendees only \n");
        System.out.println("3. Committee only\n");
        //get input
        int choice = Helper.readInt("> \n");
        //default is to sort by name alphabetically
        sortStudentName(camp.getCommitteeList());
        sortStudentName(camp.getStudentList());
        //if input is in valid range
        if (choice > 0 && choice <=3){
            switch (choice){
                case 1://list all participants
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("All Participants\n");
                int i = 1;
                //prints committee members at top first
                //for each student in the committee
                for (Student eachStudent:camp.getCommitteeList()){
                    //print details
                    printStudentDetails(i, eachStudent);
                    //increment index
                    i++;
                }
                //print attendees next
                //for each student in the attendee student list
                for (Student eachStudent:camp.getStudentList()){
                    //print details
                    printStudentDetails(i, eachStudent);
                    //increment index
                    i++;
                }
                break;
                case 2://list only attendees
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("Attendees \n");
                i = 1;
                //for each student in the attendee student list
                for (Student eachStudent:camp.getStudentList()){
                    //print details
                    printStudentDetails(i, eachStudent);
                    //increment index
                    i++;
                }
                break;
                case 3://list committee members only
                //option to sort them by points in decreasing order
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("Sort by points? \n");
                System.out.println("1. Yes\n");
                System.out.println("2. No\n");
                //get input
                int pointSort = Helper.readInt("> \n");
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("Committee Members \n");
                //if input is in valid range
                if (pointSort>0 && pointSort<=2){
                    //choose to sort by points
                    if (pointSort == 1){
                        //sort list by points
                        sortCommitteePoint(camp.getCommitteeList());
                        System.out.println("Sorted by Points \n");
                    }
                    i = 1;
                    //for each member in committee list
                    for (Student eachStudent:camp.getCommitteeList()){
                        //print details
                        printStudentDetails(i, eachStudent);
                        //increment index
                        i++;
                    }
                }
                else{System.out.println("Invalid option\n");}
                break;
            }
        }
        else{System.out.println("Invalid option\n");}
    }
}
