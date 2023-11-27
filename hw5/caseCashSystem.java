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
                        sortedString += sortedList.get(i);
                        if (!(j < sortedList.size()-1)){ //if not the last element 
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
    public Student getStudent(String name){
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
        return false;
    }

    /*
    Returns a list of student names in alphabetical order. You are not allowed to use the Java sorting
    functions, and should write your own. This function should utilize merge sort. If merge sort is
    not implemented, significant points will be deducted. Corresponds to "SORT, name".
    */
    public List<String> sortName(){
        return null;
    }

    /*
    Returns a list of student names in the order of smallest balance to largest balance in their account.
    You are not allowed to use the Java sorting functions, and should write your own. This function
    should utilize quick sort. If quick sort is not implemented, significant points will be deducted.
    Corresponds to "SORT, balance"
    */
    public List<String> sortBalance(){
        return null;
    }

    /*
    Remove money from a student account. Return true if remove is successful, and return false if
    removing will result in a negative balance. If false, the balance of the student account should not
    be changed at all. Corresponds to "WITHDRAWAL, studentA, amount".
    */
    public boolean withdrawal(Student student, int amount){
        return false;
    }


    public static void main(String[] args) {
        caseCashSystem caseCase = new caseCashSystem(); //initlialize system and students

        List<String> inputs = List.of("INIT, Sanji, 200",
                                        "INIT, Zoro, 0",
                                        "INIT, Zoro, 400",
                                        "INIT, Law, 400",
                                        "GET, Sanji",
                                        "GET, Zoro",
                                        "GET, Law",
                                        "DEPOSIT, Zoro, 1",
                                        "DEPOSIT, Zoro, -1",
                                        "DEPOSIT, Zoro, 0",
                                        "GET, Zoro"
                                        
                                        );

        List<String> outputs = caseCase.runSimulation(inputs);
        System.out.println(outputs);
        caseCase.printStudents();
         
    }
}