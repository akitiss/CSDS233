import java.util.List;
import java.util.ArrayList;

public class caseCashSystem{
    private List<Student> students; 

    /*
    Function used to run the simulation provided by the commands. This function should parse the
    commands and call their respective helper function to complete a task. More details regarding
    what inputs look like are on the next page. Note: Every call to runSimulation() should clear the
    previous list of students (starts clean).
     */
    public List<String> runSimulation(List<String> commands){
        List<String> output = new ArrayList<>(); 
        students = new ArrayList<>(); //create new set of students each time this is run 

        for (int i = 0; i<commands.size(); i++){ //loop through each command 
            String c = commands.get(i);
            String[] cArray = c.split(", "); //split commands into an array with each component 

            String command = cArray[0]; 

            //reads what command there is an runs it 
            if (command.equals("INIT")){
                String name = cArray[1];
                int initialBalance = Integer.parseInt(cArray[2]);

                boolean result = init(name, initialBalance); 
                output.add("" + result);
            } else if (command.equals("GET")){
                String name = cArray[1];

                int result = getBalance(name); 
                output.add("" + result);
            } else if (command.equals("TRANSFER")){
                String nameA = cArray[1]; //make sure input is in the right terms 
                Student stuA = getStudent(nameA);  //don't have to account for student name not being there 
                String nameB = cArray[2];
                Student stuB = getStudent(nameB); 
                int amount = Integer.parseInt(cArray[3]);

                boolean result = transfer(stuA, stuB, amount);
                output.add("" + result);
            } else if (command.equals("WITHDRAWAL")){
                String name = cArray[1];
                Student stu = getStudent(name);
                int amount = Integer.parseInt(cArray[2]); 

                boolean result = withdrawal(stu, amount); 
                output.add("" + result);
            } else if (command.equals("DEPOSIT")){
                String name = cArray[1];
                Student stu = getStudent(name);
                int amount = Integer.parseInt(cArray[2]); 

                boolean result = deposit(stu, amount);
                output.add("" + result);
            } else if (command.equals("SORT")){  
                List<String> sortedList = new ArrayList<>();

                if (cArray[1].equals("name")){ //check which sort it is
                    sortedList = (sortName());
                    
                } else if (cArray[1].equals("balance")){
                    sortedList = (sortBalance());
                }

                String sortedString = "[";
                    for (int j = 0; j < sortedList.size(); j++){ //loop through the list and add to a string 
                        sortedString += sortedList.get(j);
                        if (j < sortedList.size()-1){ //if not the last element 
                            sortedString += ", ";
                        }
                    }
                sortedString += "]";

                output.add(sortedString);

            }
        }

        return output;
    }   

    //return Student with name 
    private Student getStudent(String name){
        for (Student student : students) { //loop through list to search for if student name exists
            if (student.getName().equals(name)) {
                return student;
            }
        }

        return null;

    }

    //prints all current students
    public void printStudents(){
        System.out.print("[ ");
        for (Student element: students) { //loops through each student 
            System.out.print(element.getName());
            System.out.print(" ");
        }
        System.out.print("]");
    }

    //prints out all elements in a list
    private void printList(List<String> l){
        System.out.print("[ ");
        for (String element: l) { //loops through each student 
            System.out.print(element);
            System.out.print(" ");
        }
        System.out.print("]");    }

    /*
    Initializes a student with a name and an initial account balance. This should return true if the
    student has not been created already, and return false if a student with this name already exists.
    InitialBalance cannot be negative. Corresponds to "INIT, name, initialBalance".
    */
    public boolean init(String name, int initialBalance){
        if (getStudent(name) == null){ //if equals null, then that name doesn't exist yet 
            Student stu = new Student(name, initialBalance); //add student
            students.add(stu); 
            return true;
        } 
        return false; //return false since that means name exists 
    } 


    //return balnce of given student 
    public int getBalance(String name){
        Student stu = getStudent(name);

        if (stu == null){ //if student doesnt exist
            return -1;
        }
        return stu.getBalance();
    }

    /*
    Deposit money from a student account. Return true if deposit is successful, and return false if
    deposit fails (negative input). If false, the balance of the student account should not be changed at
    all. Corresponds to "DEPOSIT, studentA, amount".
    */
    public boolean deposit(Student student, int amount){
        if (amount < 0){ //negative input
            return false;
        } else { //positive input
            int newBalance = student.getBalance() + amount; //add current amouht + deposit amount 
            student.updateBalance(newBalance);
            return true;
        }
    }

    /*
    Transfers the amount from studentA account to studentB account. This function should return true
    if transferring is successful, and return false if transferring money from A to B will result in a
    negative balance. The parameter amount cannot be negative. If false, the balance of account A
    and B should not be changed at all. Corresponds to "TRANSFER, studentA, studentB,
    amount".
    */
    public boolean transfer(Student studentA, Student studentB, int amount){
        if (amount < 0){ //if negative 
            return false;
        } else if (studentA.getBalance() - amount < 0){ //see if studentA has enough in their account to transfer 
            return false;
        }
        //if can transfer 
        int balanceA = studentA.getBalance() - amount; //subtract amount from a  
        int balanceB = studentB.getBalance() + amount; //add amount to b 
        studentA.updateBalance(balanceA);
        studentB.updateBalance(balanceB);
        return true;
    }

    /*
    Returns a list of student names in alphabetical order. You are not allowed to use the Java sorting
    functions, and should write your own. This function should utilize merge sort. If merge sort is
    not implemented, significant points will be deducted. Corresponds to "SORT, name".
    */
    public List<String> sortName(){
        List<String> arr = new ArrayList<>(); //create new arraylist 
        for (Student stu : students){ //copy all values over to list since it says to return a list, not modify the current one 
            arr.add(stu.getName());
        }
        mergeSort(arr, 0, students.size()-1);

        return arr; //return sorted array 
    }

    //helper function to do the merge sort recursivly 
    private void mergeSort(List<String> arr, int start, int end){
        if (start >= end){ //base case 
            return;
        }
        int middle = (start+end)/2; //get middle

        mergeSort(arr, start, middle); //split in half 
        mergeSort(arr, middle+1, end);

        merge(arr, start, middle, middle+1, end); //merge when done splitting 

    }

    //merges the lists in order 
    private void merge(List<String> arr, int leftStart, int leftEnd, int rightStart, int rightEnd){

        int l = leftStart; // left sublist index
        int r = rightStart; // right sublist index

        List<String> temp = new ArrayList<>(); //create temp list 

        while (l <= leftEnd && r <= rightEnd) { //before reaches end of sublists
            String nameL = arr.get(l);
            String nameR = arr.get(r);

            if (nameL.compareTo(nameR) <= 0){ //check if left sublist element is less than or equal to right sublist element 
                temp.add(nameL); //add nameL  
                l++;
            } else { //if right sublist element is less than 
                temp.add(nameR);
                r++;
            }
        }

        while (l <= leftEnd){ //copy rest of element on left sublist 
            temp.add(arr.get(l));
            l++;
        }

        while (r <= rightEnd){ //copy rest of element on right sublist 
            temp.add(arr.get(r));
            r++;
        }

        int t = 0; 
        for (String name : temp) { //copy all values from temp list to arr  
            arr.set(leftStart + t, name);
            t++;
        }

        
    }

    /*
    Returns a list of student names in the order of smallest balance to largest balance in their account.
    You are not allowed to use the Java sorting functions, and should write your own. This function
    should utilize quick sort. If quick sort is not implemented, significant points will be deducted.
    Corresponds to "SORT, balance"
    */
    public List<String> sortBalance(){
        List<Student> arr = new ArrayList<>(); //create new arraylist 
        arr = students; 

        quickSort(arr, 0, arr.size() - 1);

        List<String> newArr = new ArrayList<>(); //convert to a String List
        for (Student element: arr){
            newArr.add(element.getName());
        }
        return newArr; //return sorted array 

    }

    static void quickSort(List<Student> arr, int first, int last) {
        if (first >= last){ //base case
            return;
        } 

        int split = partition(arr, first, last); //get index of pivot  
        
        quickSort(arr, first, split - 1); //left and right
        quickSort(arr, split + 1, last);

    }

    static int partition(List<Student> arr, int first, int last){
        int pivot = arr.get((first + last)/2).getBalance(); //set pivot as the middle index 

        int i = first ; // left index
        int j = last ; // right index 
        
        while (i <= j){ //keep going until they cross 
            while (arr.get(i).getBalance() < pivot){ //keep going until you find a value that is greater than the pivot
                i++;
            } 
            while (arr.get(j).getBalance() > pivot){ //keep going until you find a value that is less than the pivot 
                j--;
            }

            if (i <= j){
                Student temp = arr.get(i); //swap the values 
                arr.set(i, arr.get(j));
                arr.set(j, temp);
                i++;
                j--;
            }
        
        }

        return i;
    }


    /*
    Remove money from a student account. Return true if remove is successful, and return false if
    removing will result in a negative balance. If false, the balance of the student account should not
    be changed at all. Corresponds to "WITHDRAWAL, studentA, amount".
    */
    public boolean withdrawal(Student student, int amount){
        if (amount < 0){ //check if withdrawl amount is negative
            return false;
        } else if (student.getBalance() - amount < 0){ //check if student has enough money in account  
            return false;
        } else {
            int newBalance = student.getBalance() - amount; //update balance 
            student.updateBalance(newBalance);
            return true;
        }
    }


    public static void main(String[] args) {
        caseCashSystem caseCase = new caseCashSystem(); //initlialize system and students

        List<String> inputs = List.of("INIT, Sanji, 200", //true
                                        "INIT, Law, 400", //true
                                        "INIT, Zoro, 0", //true
                                        "INIT, Buggy, 0", //true
                                        "INIT, Zoro, 400", //false
                                        "INIT, Luffy, 500", //true
                                        "GET, Sanji", //200
                                        "GET, Zoro", //0
                                        "GET, Law", //400
                                        "DEPOSIT, Zoro, 1", //true
                                        "DEPOSIT, Zoro, -1", //false
                                        "DEPOSIT, Zoro, 0", //true
                                        "GET, Zoro", //1
                                        "TRANSFER, Zoro, Sanji, 10", //false
                                        "TRANSFER, Zoro, Sanji, -10", //false
                                        "GET, Sanji", //200
                                        "GET, Zoro", //1
                                        "TRANSFER, Sanji, Zoro, 100", //true
                                        "GET, Sanji", //100
                                        "GET, Zoro", //101
                                        "WITHDRAWAL, Zoro, 1", //true
                                        "GET, Zoro", //100
                                        "WITHDRAWAL, Zoro, 200", //false
                                        "WITHDRAWAL, Zoro, 0", //true
                                        "GET, Zoro", //100
                                        "WITHDRAWAL, Zoro, -10", //false
                                        "GET, Zoro" //100
                                        );

        List<String> outputs = caseCase.runSimulation(inputs);
        System.out.println(outputs);

        inputs = List.of("SORT, name", //[]
                            "INIT, Sanji, 200", //true
                            "SORT, name", //["Sanji"]
                            "INIT, Law, 400", //true
                            "SORT, name", //["Law, Sanji"]
                            "INIT, Zoro, 0", //true
                            "INIT, Buggy, 0", //true
                            "INIT, Luffy, 500", //true
                            "SORT, name" //["Buggy, Law, Luffy, Sanji, Zoro"]
                            );

        outputs = caseCase.runSimulation(inputs);
        System.out.println(outputs);

        inputs = List.of("SORT, balance", //[]
                            "INIT, Sanji, 200", //true
                            "SORT, balance", //["Sanji"]
                            "INIT, Law, 500", //true
                            "SORT, balance", //["Sanji, Law"]
                            "INIT, Zoro, 0", //true
                            "INIT, Buggy, 300", //true
                            "INIT, Luffy, 100", //true
                            "SORT, balance" //["Zoro, Luffy, Sanji, Buggy, Law"]
                            );

        outputs = caseCase.runSimulation(inputs);
        System.out.println(outputs);

        inputs = List.of("INIT, Tammy, 200",
                            "INIT, Kim, 300",
                            "INIT, Quyen, 400",
                            "SORT, name",
                            "SORT, balance",
                            "TRANSFER, Kim, Tammy, 100",
                            "SORT, name",
                            "SORT balance");


        outputs = caseCase.runSimulation(inputs);
        System.out.println(outputs);

    }
}