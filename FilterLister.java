import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FilterLister {
    private static void sortCampsLocation(ArrayList<Camp> camplist){
        Comparator<Camp> locationComparator = Comparator.comparing(Camp::getLocation);
        Collections.sort(camplist, locationComparator);
    }
    private static void sortCampsName(ArrayList<Camp> camplist){
        Comparator<Camp> nameComparator = Comparator.comparing(Camp::getName);
        Collections.sort(camplist, nameComparator);
    }
    private static void sortCampsDate(ArrayList<Camp> camplist){
        Comparator<Camp> dateComparator = Comparator.comparing(Camp::getStarDate);
        Collections.sort(camplist, dateComparator);
    }

    private static void campFilterMenu(ArrayList<Camp> camps){
        System.out.printf("---------------------------------------------------------\n");
        System.out.println("Would you like to apply a filter to the camp list?");
        System.out.println("1. Sort by Location");
        System.out.println("2. Sort by Start Date");
        System.out.println("3. No");
        int choice = Helper.readInt("> \n");
        if (choice > 0 && choice <=3){
            switch (choice){
                case 1:
                sortCampsLocation(camps);
                System.out.println("Sorted by Location \n");
                break;
                case 2:
                sortCampsDate(camps);
                System.out.println("Sorted by Start Date \n");
                break;
                default:
                sortCampsName(camps);
                break;
            }
        }
        else{System.out.println("Invalid Choice");}
    }
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

    // Lists camps with different filter options
    public static void listCamp(ArrayList<Camp> camps, Boolean filter) {
        if (filter){campFilterMenu(camps);}
        else{sortCampsName(camps);}
        int i = 1;
        System.out.printf("---------------------------------------------------------\n");
        System.out.printf("%s Camps\n");
        for (Camp eachcamp:camps){
            printCampDetails(i,eachcamp);
            i++;
        }
    }

    //overload to allow prefix
    public static void listCamp(ArrayList<Camp> camps, Boolean filter,String prefix) {
        if (filter){campFilterMenu(camps);}
        else{sortCampsName(camps);}
        int i = 1;
        System.out.printf("---------------------------------------------------------\n");
        System.out.printf("%s Camps\n",prefix);
        for (Camp eachcamp:camps){
            printCampDetails(i,eachcamp);
            i++;
        }
    }

    private static void sortStudentName(ArrayList<Student> studentList){
        Comparator<Student> nameComparator = Comparator.comparing(Student::getID);
        Collections.sort(studentList, nameComparator);
    }
    private static void sortCommitteePoint(ArrayList<Student> committeeList){
        Comparator<Student> poinComparator = Comparator.comparing(Student::getPoint);
        Collections.sort(committeeList, poinComparator);
    }

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
    public static void generateCampReport(Camp camp){
        System.out.printf("---------------------------------------------------------\n");
        System.out.println("Generate report for \n");
        System.out.println("1. All participants \n");
        System.out.println("2. Attendees only \n");
        System.out.println("3. Committee only\n");
        int choice = Helper.readInt("> \n");
        sortStudentName(camp.getCommitteeList());
        sortStudentName(camp.getStudentList());
        if (choice > 0 && choice <=3){
            switch (choice){
                case 1:
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("All Participants\n");
                int i = 1;
                for (Student eachStudent:camp.getCommitteeList()){
                    printStudentDetails(i, eachStudent);
                    i++;
                }
                for (Student eachStudent:camp.getStudentList()){
                    printStudentDetails(i, eachStudent);
                    i++;
                }
                break;
                case 2:
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("All Attendees \n");
                i = 1;
                for (Student eachStudent:camp.getStudentList()){
                    printStudentDetails(i, eachStudent);
                    i++;
                }
                break;
                case 3:
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("Sort by points? \n");
                System.out.println("1. Yes\n");
                System.out.println("2. No\n");
                int pointSort = Helper.readInt("> \n");
                System.out.printf("---------------------------------------------------------\n");
                System.out.println("Committee Members \n");
                if (pointSort>0 && pointSort<=2){
                    if (pointSort == 1){
                        sortCommitteePoint(camp.getCommitteeList());
                        System.out.println("Sorted by Points \n");
                    }
                    i = 1;
                    for (Student eachStudent:camp.getCommitteeList()){
                        printStudentDetails(i, eachStudent);
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
