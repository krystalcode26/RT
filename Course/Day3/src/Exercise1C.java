import java.util.*;
/*
given an integer array and target, return all the pairs sum to the target, each element can only be used once
input: [1, 2, 3, 4] target = 5, return [[1, 4],[2, 3]]
take screenshot and prove your solution works
 */
public class Exercise1C {
  public List<int[]> pair (int[] arr, int target){
    List<int[]> ls = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();

    for(int num : arr){
      map.put(num,map.getOrDefault(num,0)+1);
    }

    for(int num : arr){
      int curr = target - num;
      if (!map.containsKey(num) || !map.containsKey(curr)) continue;
      if (map.get(num) <= 0 || map.get(curr) <= 0) continue;
      if(curr == num && map.get(num)<2) continue;
      ls.add(new int[]{num,curr});

      map.put(num, map.get(num)-1);
      map.put(curr, map.get(curr)-1);
    }
    return ls;
  }

  public static void main(String[] args){
    Exercise1C sol = new Exercise1C();
    int[] arr = {1,2,3,4};
    int target = 5;

    List<int[]> res = sol.pair(arr,target);

    for(int[] p : res){
      System.out.println(Arrays.toString(p));
    }
  }
}
