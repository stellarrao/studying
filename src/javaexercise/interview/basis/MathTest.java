package javaexercise.interview.basis;

public class MathTest
{
public static void main(String [] args) {
    testCeil();
    testFloor();
    testRound();
}

private static void testCeil()
{
    System.out.println(Math.ceil(11.3));
    System.out.println(Math.ceil(-11.3));
    }

private static void testFloor()
{
    System.out.println(Math.floor(11.6));
    System.out.println(Math.floor(-11.6));
}

private static void testRound()
{
    System.out.println(Math.round(11.5));
    System.out.println(Math.round(-11.5));
}

}
