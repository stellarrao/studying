package javaexercise.interview.collection;

import java.util.ArrayList;
import java.util.List;

public class ListTest
{
    public static List fun(List list)
    {

        List newList = new ArrayList();
        for (Object object : list)
        {
            if (!newList.contains(object))
            {
                newList.add(object);
            }
        }

        return newList;
    }

    public static void main(String[] args)
    {

        List li = new ArrayList();
        li.add("java");
        li.add("mysql");
        li.add("oracle");
        li.add("android");
        li.add("java");
        li.add("java");

        System.out.println(fun(li));
    }
}
