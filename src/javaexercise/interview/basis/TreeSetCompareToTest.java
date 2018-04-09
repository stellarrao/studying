package javaexercise.interview.basis;

import java.util.TreeSet;

public class TreeSetCompareToTest
{
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        TreeSet set = new TreeSet();
        set.add(new Parent(3));
        set.add(new Child());
        set.add(new Parent(4));
        System.out.println(set.size());
    }
}

 class Parent implements Comparable
{
    private int age = 0;

    public Parent(int age)
    {
        this.age = age;
    }

    public int compareTo(Object o)
    {
        // TODO Auto-generated method stub
        System.out.println("method of parent");
        Parent o1 = (Parent) o;
        return age > o1.age ? 1 : age < o1.age ? -1 : 0;
    }
}

 class Child extends Parent
{
    public Child()
    {
        super(3);
    }

    public int compareTo(Object o)
    {
        // TODO Auto-generated method stub
        System.out.println("method of child");
        //Child o1 = (Child) o;
        return 1;
    }
}
