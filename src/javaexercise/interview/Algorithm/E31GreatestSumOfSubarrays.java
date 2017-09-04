package javaexercise.interview.Algorithm;

public class E31GreatestSumOfSubarrays
{
    public Integer findGreatestSum(int[] arr)
    {

        if (arr.length == 0)
        {
            return null;
        }

        int greatest = 0x80000000;
        int curSum = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (curSum <= 0)
            {
                curSum = arr[i];
            } else
            {
                curSum += arr[i];
            }

            if (curSum > greatest)
            {
                greatest = curSum;
            }
        }

        return greatest;
    }

    public static void main(String[] args)
    {
        int[] arr = { 2,4,-7,5,2,-1,2,-4,3 };
        E31GreatestSumOfSubarrays subArrays = new E31GreatestSumOfSubarrays();
        System.out.println(subArrays.findGreatestSum(arr));
    }
}
