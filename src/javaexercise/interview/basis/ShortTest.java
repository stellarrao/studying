package javaexercise.interview.basis;

/**
 * 对于short s1 = 1; s1 = s1 + 1; 由于s1+1运算时会自动提升表达式的类型，所以结果是int型，再赋值给short类型s1时，编译器将报告
需要强制转换类型的错误。对于short s1 = 1; s1 += 1;由于 += 是java语言规定的运算符，java编译器会对它进行特殊处理，因此可以正
确编译。 
 * @author rxh
 */
public class ShortTest
{
public static void main(String [] args) {
    short s1 = 1;
    //s1 = s1 + 1;
    s1 += 1;
}
}
