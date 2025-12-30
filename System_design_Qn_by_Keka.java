// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
class Employee{
  int id =1;
  String name ;
  String attend;

  Employee(int id ,String name ,String attend){
    this.id = id;
    this.name  = name;
    this.attend = attend;
  }

  public String toString(){
    return ("id "+this.id+" name :"+name+" status :"+attend);
  }

}
public class Main {
    static int id = 1;
    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer , Employee> empdetails ;
    static HashSet<String> leave ;
  public static void main(String[] args) {

    boolean exit = false;
    empdetails = new HashMap();
    leave = new HashSet<>();
     while(!exit){
         System.out.println();
      System.out.println("1. Add a employee");
      System.out.println("2. Marks Attendance");
      System.out.println("3. apply for a leave");
      System.out.println("4. View Data");
      System.out.println("5. Exit");
      System.out.println("Enter the choice (1-5):");
      int choice = sc.nextInt();

      switch(choice){
        case 1: 
              handleAddEmp();
              break;
        case 2: 
              handleMarkAttend();
              break;
        case 3: 
              handleApplyLeave();
              break;
        case 4: 
              handleViewData();
              break;
        case 5: 
              exit = true;
              break;
        default :
            System.out.println("Wrong choice , Enter valid option Number.");
            break;
      } 

     }
  }

     public static void handleAddEmp(){
      System.out.println("Enter the emp Name: ");
      String name = sc.next();
      Employee emp = new Employee(id++, name , "not_marked");
      empdetails.put(emp.id, emp);
      
      System.out.println("Employee added successfully \n");
      

     }

    public static void handleViewData(){
        while(true){
          System.out.println("1.view all employees current status");
          System.out.println("2.view Attendance today");
          System.out.println("3.view on Leave");
          System.out.println("Enter the choice(1-3)");
          String choice = sc.next();
          if(choice.equals("1")){
              
              for(int id : empdetails.keySet()){
                 Employee emp = empdetails.get(id);
                  System.out.println(emp);
              }
            break;
          }else if(choice.equals("2")){
              for(int id : empdetails.keySet()){
                 Employee emp = empdetails.get(id);
                 if(!(emp.attend).equals("not_marked")){
                  System.out.println(emp);
                 }
              }
            break;
          }else if(choice.equals("3")){
              if(leave.size()==0){
                  System.out.println("No leave records");
                  break;
              }
            for(String emp : leave){
              System.out.println(emp);
            }
            break;
          }else{
            System.out.println("Enter the valid choice");
          }

        }
    }
     public static void handleMarkAttend(){
      System.out.println("Enter Employee id");
        int empid = sc.nextInt();
        if(!empdetails.containsKey(empid)){
          System.out.println("Invalid empid");
          return;
        }
        Employee emp = empdetails.get(empid);
        String status = emp.attend;

        while(true){
          System.out.println("1.Clockin");
          System.out.println("2.Clockout");
          System.out.println("Enter the option(1-2)");


          int choice = sc.nextInt();
          if(choice==1){
            String curr_date = "29-12-2025";
            String hash = empid+"_"+curr_date;
            if(leave.contains(hash)){
                System.out.println("You are on leave today");
                return;
            }
            
            if(status.equals("clock_in")){
              System.out.println("Attendance already marked");
              return;
            }

            emp.attend = "clock_in";
            System.out.println("clock_in successfull");
            break;

          }else if(choice==2){
                if(status.equals("clock_in")){
                  emp.attend = "present";
                  System.out.println("marked present");
                }else{
                  System.out.println("Not logged in");
                }

            break;
          }else{
            System.out.println("Enter the correct option(1-2)");
          }
        }
     }
     public static void  handleApplyLeave(){
      System.out.println("Enter the emp id: ");
      int empid = sc.nextInt();
      System.out.println("Enter the date want the leave (dd-mm-yyyy)");
      String date = sc.next();

      String hash = empid+"_"+date;
      //already leave taken
      if(leave.contains(hash)){
        System.out.println("You already taken on that date");
        return;
      }

      String status = empdetails.get(empid).attend;
      if(status.equals("not_marked")){
        leave.add(hash);
        System.out.println("Leave granted successfully");
        return;
      }else{
        System.out.println("Leave rejected");
        System.out.println("Attendance marked");
      }

     
      

     }
}