package javaexercise.interview.basis;

public class EqualsSignTest
{
public static void main(String [] args) {
    isEquals();
}

private static void isEquals() {
    String a = new String("foo");
    String b = new String("foo");
    
    System.out.println("a==b : " + (a==b));
    System.out.println("a.equals(b) : " + (a.equals(b)));
}
}
