package javaexercise.interview.basis;

import java.applet.Applet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 静态嵌套类
 * 
 * @author rxh
 */
class StaticNested
{
    private static String name = "rxh";

    private String num = "X001";

    static class Person
    {
        private String address = "China";

        public String mail = "rxh@126.com";
        // private static int age = 10;

        public void diplay()
        {
            // 不能直接访问外部类的非静态成员
            // System.out.println(num);
            // 只能直接访问外部类的静态成员
            System.out.println(name);
            // 访问本内部类成员
            System.out.println("Inner " + address);
        }
    }

    public void printInfo()
    {
        Person person = new Person();
        person.diplay();

        // 不可直接访问静态嵌套类的成员变量
        // System.out.println(mail);
        // System.out.println(address);
        // System.out.println(Person.age);

        // 可以访问内部类的私有成员
        System.out.println(person.address);
        // 可以访问内部类的公有成员
        System.out.println(person.mail);
    }
}

/**
 * 在外部类中定义内部类
 * @author rxh
 */
class Outer
{
    int outer_x = 100;
    
    // 私有的内部类
    private class InnerOne
    {
        public int inner_y = 10;

        private int inner_z = 9;

        int inner_m = 5;

        public void display()
        {
            System.out.println("display outer_x:" + outer_x);
        }

        private void display2()
        {
            System.out.println("display outer_x:" + outer_x);
        }
    }

    public InnerOne getInnerOne()
    {
        // 即使是对外公开的方法，外部类也无法调用
        return new InnerOne();
    }

    class InnerTwo
    {
        InnerOne innerx = getInnerOne();

        public void show()
        {
            //不可访问InnerOne的inner_y成员
//            System.out.println(inner_y);
            //不可直接访问InnerOne的任何成员和方法  
            //System.out.println(Inner.inner_y);

            innerx.display();
            innerx.display2();

            System.out.println(innerx.inner_y);
            System.out.println(innerx.inner_z);
            System.out.println(innerx.inner_m);
        }
    }

    void test()
    {
        InnerOne inner = new InnerOne();
        inner.display();
        inner.display2();
        
        //不能访问内部内变量  
        //System.out.println("Inner y:" + inner_y);
        System.out.println("Inner y:" + inner.inner_y);
        System.out.println("Inner z:" + inner.inner_z);
        System.out.println("Inner m:" + inner.inner_m);

        InnerTwo innerTwo = new InnerTwo();
        innerTwo.show();
    }
}

/**
 * 在方法中定义内部类
 * @author rxh
 */
class FunOuter
{
    int out_x = 100;

    public void test()
    {
        class Inner
        {
            String inner_x = "x";

            void display()
            {
                System.out.println(out_x);
            }
        }

        Inner inner = new Inner();
        inner.display();
    }

    public void showStr(String str)
    {
        // public String str1 = "test Inner";
        // 不可以定义，只允许final修饰
        // static String str4 = "static Str";
        String str2 = "test Inner";
        final String str3 = "final Str";

        class InnerTwo
        {
            public void testPrint()
            {
                System.out.println(out_x);

                // 可直接访问外部类的变量
                // 不可访问本方法内部的非final变量
                System.out.println(str);

                // 不可访问本方法内部的非final变量
                System.out.println(str2);
                // 只可访问本方法的final型变量成员
                System.out.println(str3);

            }
        }

        InnerTwo innerTwo = new InnerTwo();
        innerTwo.testPrint();
    }

    public void use()
    {
        // Inner innerObj = new Inner();//此时Inner己不可见了
        // System.out.println(Inner.x);//此时Inner己不可见了
    }
}

/**
 * 匿名内部类
 * @author rxh
 */
class AnonymousClass extends Applet {

    private static final long serialVersionUID = 1L;

    public void init() {
        addMouseListener(new MouseAdapter() {
           public void mousePressed(MouseEvent me) {
               showStatus("Mouse Pressed!");
           }
        });
    }
    
   public void showStatus(String str) {
       System.out.println(str);
   }
}

 /**
 * 内部类使用的其它的问题
 * @author rxh
 */
class Layer  
{  
   // Layer类的成员变量  
   private String testStr = "testStr";  
  
   // Person类, 基类  
   class Person  
   {  
     String name;  
     Email email;  
  
     public void setName(String nameStr)  
     {  
       this.name = nameStr;  
     }  
     public String getName()  
     {  
       return this.name;  
     }  
     public void setEmail(Email emailObj)  
     {  
       this.email = emailObj;  
     }  
     public String getEmail()  
     {  
       return this.email.getMailStr();  
     }  
  
     // 内部类的内部类, 多层内部类  
     class Email  
     {  
       String mailID;  
       String mailNetAddress;  
  
       Email(String mailId, String mailNetAddress)  
       {  
         this.mailID = mailId;  
         this.mailNetAddress = mailNetAddress;  
       }  
       String getMailStr()  
       {  
         return this.mailID + "@" + this.mailNetAddress;  
       }  
     }  
   }  
  
   // 另一个内部类继承外部类本身  
   class ChildLayer extends Layer  
   {  
     void print()  
     {  
       System.out.println(super.testStr);// 访问父类的成员变量  
     }  
   }  
  
   // 另个内部类继承内部类Person  
   class OfficePerson extends Person  
   {  
     void show()  
     {  
       System.out.println(name);  
       System.out.println(getEmail());  
     }  
   }  
  
   // 外部类的测试方法  
   public void testFunction()  
   {  
     // 测试第一个内部类  
     ChildLayer childLayer = new ChildLayer();  
     childLayer.print(); // 测试第二个内部类  
     OfficePerson officePerson = new OfficePerson();  
     officePerson.setName("abner chai");  
     // 注意此处, 必须用对象.new 出来对象的子类对象  
     // 而不是Person.new Email(...)  
     // 也不是new Person.Email(...)  
     officePerson  
         .setEmail(officePerson.new Email("josserchai", "yahoo.com"));  
     officePerson.show();  
   }  
  
}  

public class StaticNestedClasses
{
    public static void staticNestedTest()
    {
        new StaticNested.Person().diplay();
        StaticNested nested = new StaticNested();
       
        nested.printInfo();
    }

    public static void outerInnerTest()
    {
        Outer outInner = new Outer();
        
        // InnerOne类是私有的,外部类不能访问, 如果Inner类是public ,则可以.
        // Outer.InnerOne i = outInner.getInnerOne();
        outInner.test();
    }

    public static void funOuterInnerTest()
    {
        FunOuter outInner = new FunOuter();

        outInner.test();
        outInner.showStr("rxh");
    }

    public static void layerOtherTest()  
    {  
      Layer layer = new Layer();  
      layer.testFunction();  
    }  
    
    public static void main(String[] args)
    {
        //staticNestedTest();
        //outerInnerTest();
        funOuterInnerTest();
        //layerOtherTest();
    }
}
