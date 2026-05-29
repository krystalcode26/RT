import java.util.*;
public class Employee extends Thread {
  private final String firstName;
  private final String lastName;
  private final double salary;

//  direct search for email so useful to use Map here -- get() O(1) time complexity
//  internal - abc@gmail.com
//  external - abc2@gmail.com
//  backup - abc3@gmail.com
//  infoBoard - abc4@gmail.com
  private final Map<String, String> emailAddressInternal;

  public Employee(String firstName, String lastName, double salary, Map<String, String> emailAddressInternal) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.salary = salary;
    this.emailAddressInternal = emailAddressInternal;
  }

  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }

  public double getSalary() {
    return salary;
  }

  public Map<String, String> getEmailAddressInternal() {
    return emailAddressInternal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Double.compare(getSalary(), employee.getSalary()) == 0 && Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(getLastName(), employee.getLastName()) && Objects.equals(getEmailAddressInternal(), employee.getEmailAddressInternal());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getLastName(), getSalary(), getEmailAddressInternal());
  }

  @Override
  public String toString() {
    return "Employee{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", salary=" + salary +
            ", emailAddressInternal=" + emailAddressInternal +
            '}';
  }

  public static void main(String[] args){
    Thread t1 = new Thread(()->{
      System.out.println("Thread "+Thread.currentThread().getName());
    });
    t1.run();
    t1.start();
    System.out.println("hello world");
  }

}
