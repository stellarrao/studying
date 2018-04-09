package javaexercise.interview.basis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

public class VectorRidRepetition
{
    public static void main(String[] args)
    {
//        method1();
         method2();

    }

    private static void method1()
    {
        Vector vector = new Vector();
        vector.add("rxh1");
        vector.add("rxh2");
        vector.add("rxh2");
        vector.add("rxh3");
        vector.add("rxh4");

        Vector newVector = new Vector();
        for (int i = 0; i < vector.size(); i++)
        {
            Object obj = vector.get(i);
            if (!newVector.contains(obj))
            {
                newVector.add(obj);
            }
        }

        for(int i = 0; i<newVector.size(); i++) {
            System.out.println(newVector.get(i));
        }
    }

    private static void method2()
    {
        //HashSet set = new HashSet(vector);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.remove(1);
        System.out.println(list);
        
        Vector<Integer> ve = new Vector<Integer>();
        ve.addElement(2);
        ve.add(2);
        ve.addElement(2);
        ve.addElement(2);
        ve.add(3);
        ve.addAll(2, list);
        System.out.println(ve);
        
        HashSet hashSet = new HashSet(ve);
        System.out.println(hashSet);
        
        Vector ve2 = new Vector(hashSet);
        System.out.println(ve2);
    }
}
