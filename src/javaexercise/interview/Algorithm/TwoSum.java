package javaexercise.interview.Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TwoSum
{

    public int[] sumTotal(int[] nums, int target)
    {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++)
        {
            map.put(nums[i], i);
        }

        List<Integer> listResult = new ArrayList<Integer>();

        for (int i = 0; i < (target / 2 + 1); i++)
        {
            if (i != (target - i) && map.containsKey(i) && map.containsKey(target - i))
            {
                listResult.add(map.get(i));
                listResult.add(map.get(target - i));
            }
        }

        return listResult.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args)
    {

        System.out.println("输入一个初始数组维数：");
        Scanner input = new Scanner(System.in);
        int[] number = new int[input.nextInt()];

        /*
         * while (!input.hasNext("e")) { int n = input.nextInt(); number = new int[n]; for (int i = 0; i < n; i++) { number[i] = input.nextInt(); } }
         */

        for (int i = 0; i < number.length; i++)
        {
            number[i] = input.nextInt();
        }
        
        System.out.println("输入一个目标数字：");
        int target = input.nextInt();
        input.close();
        TwoSum twoSum = new TwoSum();
        int [] result = twoSum.sumTotal(number, target);
        System.out.println(Arrays.toString(result));

    }
}
