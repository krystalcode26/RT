import java.util.*;
/*
reverse a string
input: “abc”, output: “cba”
 */
public class Exercise1B {
  public String reverse(String str){
    StringBuilder sb = new StringBuilder();
    Stack<Character> stack = new Stack<>();

    char[] arr = str.toCharArray();
    for(char c : arr){
      stack.push(c);
    }
    while(!stack.isEmpty()) {
     sb.append(stack.pop());
    }
    return sb.toString();
  }
  public static void main(String[] args){
    Exercise1B sol = new Exercise1B();
    String str = "abc";
    System.out.println(sol.reverse(str));
  }
}
