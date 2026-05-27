import java.util.*;

public class Employee implements Comparable {
  String firstName;
  String lastName;
  List<String> callPhones;
  Double salary;

  //constructor, getter, setter

  public static void main(String[] args){
    List<Integer> list1 = new ArrayList<>();
    int a = 1;
    list1.add(a);//autoboxing/unboxing: internally wrap int -> Integer store to list1

    list1.stream().forEach(System.out::print);
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }
}