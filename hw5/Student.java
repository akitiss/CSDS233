public class Student{
    //private vars since don't want this information to be easily accessible
    private String name; //student name 
    private int balance; //current balance in the students account

    //constructor of student 
    public Student(String name, int balance){
        this.name = name; 
        this.balance = balance; 
    }

    //get balance of student 
    public int getBalance(){ 
        return this.balance; 
    }

    //update balance of student 
    public void updateBalance(int newAmount){ 
        this.balance = newAmount; 
    }    

    //get name of student
    public String getName(){
        return this.name;
    }

}