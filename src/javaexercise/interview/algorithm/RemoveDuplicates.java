package javaexercise.interview.algorithm;

public class RemoveDuplicates
{
    public static void main(String[] args)
    {

        int[] nums = new int[] { 1, 1, 2 };

        int length = removeDuplicates(nums);
        System.out.println("The length of array is : " + length);
    }

    private static int removeDuplicates(int[] nums)
    {
        for (int i = 0; i < nums.length; i++)
        {
            for (int j = i + 1; j < nums.length; j++)
            {
                if (nums[i] != nums[j])
                {
                    for (int k = j; k < nums.length; k++)
                    {
                        nums[i + k - j + 1] = nums[k];
                    }

                    i = j;
                    break;
                }
            }
        }

        return nums.length;
    }
}
