package javaexercise.interview.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class TwoSum
{

    public int[] sumTotal2(int[] nums, int target)
    {

        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i + 1;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i + 1);
        }
        return result;
    }

    
    public int[] sumTotal1(int[] nums, int target)
    {

        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < nums.length; i++)
        {
            if (map.containsKey(nums[i]))
            {
                map.get(nums[i]).add(i);
            } else
            {
                List<Integer> listValue = new ArrayList<Integer>();
                listValue.add(i);

                map.put(nums[i], listValue);
            }
        }

        List<Integer> listResult = new ArrayList<Integer>();

        for (Entry<Integer, List<Integer>> entry : map.entrySet())
        {
            
            if (map.containsKey(target - entry.getKey()))
            {
                if (IsContains(listResult, entry.getValue())) continue;
                
                if (entry.getKey().equals(target - entry.getKey()))
                {
                    if (entry.getValue().size() >= 2)
                    {

                        listResult.add(entry.getValue().get(0));
                        listResult.add(entry.getValue().get(1));
                    }
                } else
                {

                    int minNumber = entry.getValue().size() >= map.get(target - entry.getKey()).size() ? map.get(target - entry.getKey()).size()
                                                                                                       : entry.getValue().size();

                    for (int i = 0; i < minNumber; i++)
                    {
                        listResult.add(entry.getValue().get(i));
                        listResult.add(map.get(target - entry.getKey()).get(i));
                    }
                }
            }
        }

        return listResult.stream().mapToInt(Integer::intValue).toArray();

    }

    private boolean IsContains(List<Integer> listResult, List<Integer> listCondition)
    {

        boolean bValue = false;

        for (Integer result : listResult)
        {
            if (listCondition.contains(result))
            {
                bValue = true;
                break;
            }
        }

        return bValue;
    }

    public int[] sumTotal(int[] nums, int target)
    {

        List<Integer> listResult = new ArrayList<Integer>();

        for (int i = 0; i < nums.length; i++)
        {
            for (int j = 0; j < nums.length; j++)
            {
                if (i != j && (nums[i] + nums[j] == target))
                {
                    if (listResult.contains(i))
                    {
                        break;
                    }

                    if (listResult.contains(j))
                    {
                        continue;
                    }

                    listResult.add(i);
                    listResult.add(j);
                    break;
                }
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
        int[] result = twoSum.sumTotal2(number, target);
        System.out.println(Arrays.toString(result));

    }
}
