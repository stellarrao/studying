package javaexercise.interview.collection;

import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

public class VectorTest
{
    public static void main(String[] args)
    {
        List li = new ArrayList();
        li.add("zhangsan");
        li.add("lisi");
        li.add("wangwu");

        Iterator it = li.iterator();
        Enumeration em = new MyTransformer(it);
        print(em);
    }

    public static void print(Enumeration em)
    {
        String strValue = null;
        while (em.hasMoreElements())
        {
            strValue = (String) em.nextElement();
            System.out.println(strValue);
        }
    }
}

class MyTransformer implements Enumeration
{
    private Iterator it;

    public MyTransformer(Iterator it)
    {
        this.it = it;
    }

    @Override
    public boolean hasMoreElements()
    {
        return it.hasNext();
    }

    @Override
    public Object nextElement()
    {
        return it.next();
    }
}