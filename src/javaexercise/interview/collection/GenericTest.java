package javaexercise.interview.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 上限:<? extends T> 只能使用T类型或者T类型的子类对象。
 * 下限:<? super T> 只能使用T类型或者T的父类及接口实例对象
 * http://blog.csdn.net/qinqinnibaobaoni/article/details/8565914?locationNum=15&fps=1
 * @author rxh
 */
public class GenericTest
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("c++");
        list.add("php");

        Iterator<String> iterator = list.iterator();
        String strValue = null;
        while (iterator.hasNext())
        {
            strValue = iterator.next();
            System.out.println(strValue);
        }
    }

    private static void updownTest()
    {
        List<? extends Object> li = new ArrayList<String>();
        //li.add("abc");

        List<? super String> lia = new ArrayList<String>();
        lia.add("123");

        // ArrayList lib = new ArrayList<? extends Object>();
        List<?> list1 = new ArrayList();
        List<?> list2 = new ArrayList<String>();
        List<? extends Object> pList = new ArrayList<Object>();
        List<? super String> sList = new ArrayList<String>();
        List<?> list3 = sList;
        list2.isEmpty();

    }

    private static void fun(List<? extends Object> list, String str)
    {
        System.out.println(list.get(0));
    }
}
