package javaexercise.interview.algorithm;

import java.util.Arrays;

public class InsertionSort
{
    private static void insertionSort(int[] unsorted)
    {
        for (int i = 1; i < unsorted.length; i++)
        {
            if (unsorted[i - 1] > unsorted[i])
            {
                int temp = unsorted[i];
                int j = i;
                while (j > 0 && unsorted[j - 1] > temp)
                {
                    unsorted[j] = unsorted[j - 1];
                    j--;
                }

                unsorted[j] = temp;
            }
        }
    }

    private static void insertionSort1(int[] unsorted) {
        for (int i = 0, j = i; i < unsorted.length; j = ++i) {
            int ai = unsorted[i + 1];
            while (ai < unsorted[j]) {
                unsorted[j + 1] = unsorted[j];
                if (j-- == 0) {
                    break;
                }
            }
            unsorted[j + 1] = ai;
        }
    }
    
    public static void main(String[] args)
    {

        int[] unsorted = new int[] { 7,1 };

        insertionSort1(unsorted);

        System.out.println(Arrays.toString(unsorted));
    }
}
