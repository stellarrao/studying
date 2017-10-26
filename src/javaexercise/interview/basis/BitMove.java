package javaexercise.interview.basis;

public class BitMove
{
public static void main(String [] args) {
    /*
     因为将一个数左移n位，就相当于乘以了2的n次方，那么，一个数乘以8只要将其左移3位即可，而位运算cpu直接支持的，效率最高，所以，2乘以8等於几的最效率的方法是2 << 3。
   */
    int b = 2 >> 3;
    System.out.println(b);
}
}
