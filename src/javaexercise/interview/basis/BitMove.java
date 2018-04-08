package javaexercise.interview.basis;

import java.util.Arrays;

import javaexercise.ownutil.BitUtils;

public class BitMove
{
    public static void main(String[] args)
    {
        /*
         * 因为将一个数左移n位，就相当于乘以了2的n次方，那么，一个数乘以8只要将其左移3位即可，而位运算cpu直接支持的，效率最高，所以，2乘以8等於几的最效率的方法是2 << 3。
         * 右移相当于除以2的n次方
         */
        /*
        int b = 8 >> 1;
        int c = 4 << 8;
        System.out.println(b);
        System.out.println(c);

        System.out.println(Integer.toBinaryString(-1 >>> 1));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));

        System.out.println("1 << 13 : " + (1 << 13));
        System.out.println(~(1 << 31));
        System.out.println((1 << -1) - 1);
        System.out.println(~(1 << -1));

        System.out.println(((101 & 1) == 1 ? "奇数" : "偶数"));
        
        int [] array = new int [] {2,1,7,9};
        int [] array1 = reverse(array);
        System.out.println(Arrays.toString(array1));
        
        int a = 100;
        int d = -100;
        System.out.println((a^(a>>31))-(a>>31));
        System.out.println((d^(d>>31))-(d>>31));
        */
        testBitOperation();
    }
    
    private static  int [] reverse(int [] nums) {
        int i = 0;
        int j = nums.length - 1;
        while(j>i) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[j] ^ nums[i];
            nums[i] = nums[i] ^ nums[j];
            j--;
            i++;
        }
        
        return nums;
    }

    private static void testBitOperation() {
        int num  = 15; 
        byte bValue = BitUtils.getBitValue((byte)num, 3);
        
        System.out.println("value: " + bValue);
    }
}
