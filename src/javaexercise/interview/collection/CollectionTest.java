package javaexercise.interview.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CollectionTest
{
public static void main(String [] args) {
//    Collection col = new ArrayList();
    List col = new ArrayList();
    
    col.add("java");
    col.add("html");
    col.add("php");
    
//    Iterator iterator = col.iterator();
    ListIterator iterator = col.listIterator();
    String strValue = null;
    /*while(iterator.hasNext()) {
        strValue = (String)iterator.next();
        System.out.println(strValue);
    }*/

    while(iterator.hasPrevious()) {
        strValue = (String)iterator.previous();
        System.out.println(strValue);
    }
}
}
