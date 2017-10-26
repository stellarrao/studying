package javaexercise.interview.basis;

/**
 * JDK1.7以前switch表达式的值只能为int、byte、short、或char，不允许为long或double等长整型值，也不允许为String，JDK1.7支持String类型的了
 * @author rxh
 */
public class SwitchString
{
    public static void main(String[] args)
    {
        String strFlag = "A";
        switch (strFlag)
        {
        case "A":
            System.out.println("A");
            break;
        case "B":
            System.out.println("B");
            break;
        default:
            System.out.println("default");
            break;
        }

        /*long lFlag = 100L;
        switch (lFlag)
        {
        case 10L:
            System.out.println("A");
            break;
        case 100L:
            System.out.println("B");
            break;
        default:
            System.out.println("default");
            break;
        }*/
    }
}
