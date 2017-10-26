package javaexercise.interview.basis;

import java.util.ArrayList;
import java.util.List;


public class StaticInnerClass
{
    int out_x = 0;
static int out_y  = 0;
    public void method()
    {
        Inner1 inner1 = new Inner1();
int m_1 = 1;
         /**
         * 类Inner2前智能添加final或abstract，不能加public private protected
         * @author rxh
         */
        class Inner2
        {
            public void method() {
                out_x = 3;
                System.out.println(m_1);
            }
        }

        Inner2 inner2 = new Inner2();
        
        
    }

    public static void main(String [] args) {
        StaticInnerClass outer = new StaticInnerClass();
        StaticInnerClass.Inner1 inner = outer.new Inner1();
        
        StaticInnerClass.Inner3 inner3 = new StaticInnerClass.Inner3();
        
    }
    /**
     * 内部类中不能定义静态成员
     * 内部类可以直接访问外部类中的成员变量
     * 内部类可以定义在外部类的方法外面，也可以定义在外部类的方法体中
     * @author rxh
     */
    public class Inner1
    {

//public static  String strName = "rxh" ;
        int inner_x = 0;
    public Inner1() {
        inner_x = out_x;
    }
    }
    
    public static class Inner3 {
        public void getName() {
           //System.out.println(out_x); 
        }
        
        public static void getAge() {
            
        }
    }
}

//public class StaticInnerClass
//{
//
//}
