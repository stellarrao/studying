package javaexercise.interview.basis;
import java.util.Date;

public class SupperGetClass extends Date
{
    public void test()
    {
        // getClass 在 Object 中被定义成了final,不能被子类覆
        System.out.println(super.getClass().getName());
        System.out.println(this.getClass().getName());
        // 才能得到父类的名字
        System.out.println(getClass().getSuperclass().getName());
    }

    public static void main(String[] args)
    {
        new SupperGetClass().test();
    }
}
