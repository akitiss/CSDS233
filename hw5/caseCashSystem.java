public class caseCashSystem{
    private List<Student> students; 

    /*
    Function used to run the simulation provided by the commands. This function should parse the
    commands and call their respective helper function to complete a task. More details regarding
    what inputs look like are on the next page. Note: Every call to runSimulation() should clear the
    previous list of students (starts clean).
     */
    public List<String> runSimulation(List<String> commands){
        return null;
    }   

    /*
    Initializes a student with a name and an initial account balance. This should return true if the
    student has not been created already, and return false if a student with this name already exists.
    InitialBalance cannot be negative. Corresponds to “INIT, name, initialBalance”.
    */
    public boolean init(String name, int initialBalance){
        return false;
    } 

    //return baalnce of given student 
    public int getBalance(String name){
        return name.getBalance();
    }

    /*
    Deposit money from a student account. Return true if deposit is successful, and return false if
    deposit fails (negative input). If false, the balance of the student account should not be changed at
    all. Corresponds to “DEPOSIT, studentA, amount”.
    */
    public boolean deposit(Student student, int amount){
        return false;
    }

    /*
    Transfers the amount from studentA account to studentB account. This function should return true
    if transferring is successful, and return false if transferring money from A to B will result in a
    negative balance. The parameter amount cannot be negative. If false, the balance of account A
    and B should not be changed at all. Corresponds to “TRANSFER, studentA, studentB,
    amount”.
    */
    public boolean transfer(Student studentA, Student studentB, int amount){
        return false;
    }

    /*
    Returns a list of student names in alphabetical order. You are not allowed to use the Java sorting
    functions, and should write your own. This function should utilize merge sort. If merge sort is
    not implemented, significant points will be deducted. Corresponds to “SORT, name”.
    */
    public List<String> sortName(){
        return null;
    }

    /*
    Returns a list of student names in the order of smallest balance to largest balance in their account.
    You are not allowed to use the Java sorting functions, and should write your own. This function
    should utilize quick sort. If quick sort is not implemented, significant points will be deducted.
    Corresponds to “SORT, balance”
    */
    public List<String> sortBalance(){
        return null;
    }

    /*
    Remove money from a student account. Return true if remove is successful, and return false if
    removing will result in a negative balance. If false, the balance of the student account should not
    be changed at all. Corresponds to “WITHDRAWAL, studentA, amount”.
    */
    public boolean withdrawal(Student student, int amount){
        return false;
    }





    public static void main(String[] args) {
        
    }
}