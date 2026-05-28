import java.util.*;
/*
given a random character array, find the char with third highest frequence
input: [a, b, b, c, c, c], output: [a]
 */
public class Exercise1A {
  public Character highest(char[] arr){
      Map<Character, Integer> map = new HashMap<>();
      PriorityQueue<Integer> pq = new PriorityQueue<>();

      for(char c : arr){
        map.put(c, map.getOrDefault(c,0)+1);
      }

    // sort by frequency descending
      List<Map.Entry<Character,Integer>> list = new ArrayList<>(map.entrySet());

      if(list.size()>=3){
        return list.get(2).getKey();
      }
      return null;
  }

  public static void main(String[] args){
    Exercise1A sol = new Exercise1A();

    char[] arr = {'a', 'b', 'b', 'c', 'c', 'c'};

    System.out.println(sol.highest(arr));

  }
}
