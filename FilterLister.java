import java.time.LocalDate;
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

    private static Boolean filterCheck(Object input, Object filter){
        if (input.equals(filter)){
            return true;
        }
        else{
            return false;
        }
    }
    private static void campSortMenu(ArrayList<Camp> camps){
        //print options
        System.out.printf("---------------------------------------------------------\n");
        System.out.println("Would you like to sort the camp list?");
        System.out.println("1. Sort by Location");
        System.out.println("2. Sort by Start Date");
        System.out.println("3. Sort by Name");
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
                case 3:
                //sort by name alphabetically
                sortCampsName(camps);
                System.out.println("Sorted by Name \n");
                break;
            }
        }
        else{System.out.println("Invalid Choice");}
    }

    private static int campFilterMenu(){
        //print options
        int choice = -1;
        while (choice < 1 || choice > 4){
            System.out.printf("---------------------------------------------------------\n");
            System.out.println("Would you like to filter the camp list?");
            System.out.println("1. Filter by Name");
            System.out.println("2. Filter by Location");
            System.out.println("3. Filter by Start Date");
            System.out.println("4. No");
            choice = Helper.readInt("> ");
            if (choice < 1 || choice >4){
                System.out.println("Invalid option");
            }
        }
        return choice;
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
        int filterType = -1;
        String filterString = "'wadawdwadada'";
        LocalDate filterdate = LocalDate.of(2022, 12, 2);
        if (filter){
            //filter type
            filterType = campFilterMenu();
            //if filter type is string, get filterstring
            if (filterType == 1 || filterType == 2){
                filterString = Helper.readString("Input Filter > ");
            }
            //if filter type is date, get filter date
            if (filterType == 3){
                filterdate = Helper.readDate("Input Filter >");
            }
        }
        else{
            //sort if not filtering
            campSortMenu(camps);
        }
        //for each camp in the camp list
        int i = 1;
        System.out.printf("---------------------------------------------------------\n");
        System.out.printf("%s Camps\n");
        for (Camp eachcamp:camps){
            //print camp details with index for each filter type
            if (filterType == 1){
                if(filterCheck(filterString, eachcamp.getName())){
                    printCampDetails(i,eachcamp);
                    //increment index
                    i++;
                }
            }
            if (filterType == 2){
                if(filterCheck(filterString, eachcamp.getLocation())){
                    printCampDetails(i,eachcamp);
                    i++;
                }
            }
            if (filterType == 3){
                if(filterCheck(filterdate, eachcamp.getStarDate())){
                    printCampDetails(i,eachcamp);
                    i++;
                }
            }
            if (filterType == 4){
                printCampDetails(i,eachcamp);
                i++;
            }
            
        }
    }

    //overload to allow prefix
    public static void listCamp(ArrayList<Camp> camps, Boolean filter,String prefix) {
        //if including filter, call filter menu
        int filterType = -1;
        String filterString = "'wadawdwadada'";
        LocalDate filterdate = LocalDate.of(2022, 12, 2);
        if (filter){
            //filter type
            filterType = campFilterMenu();
            if (filterType == 1 || filterType == 2){
                filterString = Helper.readString("Input Filter > ");
            }
            if (filterType == 3){
                filterdate = Helper.readDate("Input Filter >");
            }
        }
        else{
            //sort if not filtering
            campSortMenu(camps);
        }
        //for each camp in the camp list
        int i = 1;
        System.out.printf("---------------------------------------------------------\n");
        System.out.printf("%s Camps\n",prefix);
        for (Camp eachcamp:camps){
            //print camp details with index
            if (filterType == 1){if(filterCheck(filterString, eachcamp.getName())){printCampDetails(i,eachcamp);i++;}}
            if (filterType == 2){if(filterCheck(filterString, eachcamp.getLocation())){printCampDetails(i,eachcamp);i++;}}
            if (filterType == 3){if(filterCheck(filterdate, eachcamp.getStarDate())){printCampDetails(i,eachcamp);i++;}}
            if (filterType == 4){printCampDetails(i,eachcamp);i++;}
        }
    }

    //Sort input student list by name alphabetically
    private static void sortStudentName(ArrayList<Student> studentList){
        //create comparator
        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
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
        System.out.printf("Name                      : %s\n",eachStudent.getName());
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
