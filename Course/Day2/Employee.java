import java.util.*;
/*
Declare requirements
Employee: name - firstName, lastName
          salary
          cellphone
*/
public class Employee {
//  public String getFirstName() {
//    return firstName;
//  }
//
//  public void setFirstName(String firstName) {
//    this.firstName = firstName;
//  }

  public Employee(String firstName, String lastName, List<String> phoneNumber, double salary, List<Task> tasks) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.salary = salary;
    this.tasks = tasks;
  }

  //give the minimum access level - private
  private final String firstName;
  private final String lastName;
  private final List<String> phoneNumber;
  private final double salary;
  private final List<Task> tasks;

  // constructor, getter, setter
  // Getter: for External user to access read/write
  // Setter: for external user to set

  // hashcode + equals

  public List<Task> getTasks(){
    List<Tasks> dummy = new ArrayList();

    for(Task t : tasks){
      Task dummyTask = new Task();
      dummytask.set(t.getTaskId());
      dummy.add(dummytask);
    }
  }


  public static void main(String[] args){
    //return reference created in a heap
    //Getter return the reference point to " new ArrayList(List.of("1","2")));" object
    Employee employee = new Employee("David","L", new ArrayList(List.of("1","2")));
    List<String> phones = employee.getPhoneNumber();
    phone.add("3");
    //deep copy -> dummy referenced object in getter -> List<Task>
  }
}
