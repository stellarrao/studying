package javaexercise.interview.algorithm;

import java.util.Arrays;

public class ArraysTestSort
{
    private static void sortIntTest() {
        int [] arrInt = new int [] {1,3,2,9,5};
        System.out.println(Arrays.toString(arrInt));
        Arrays.sort(arrInt);
        System.out.println(Arrays.toString(arrInt));
    }
public static void main(String [] args) {
    sortIntTest();
}
}
