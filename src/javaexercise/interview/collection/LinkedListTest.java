package javaexercise.interview.collection;

import java.util.LinkedList;

public class LinkedListTest
{
    public static void main(String[] args)
    {
        LinkedList li = new LinkedList();
        li.push("张三");
        li.push("李四");
        li.push("王五");

        IQueue mi = new MyIQueue(li);
        while (!mi.mpty())
        {
            System.out.println(mi.op());
        }
    }
}

interface IQueue
{
    void ph(Object obj);

    boolean mpty();

    Object op();
}

class MyIQueue implements IQueue
{

    private LinkedList lk = new LinkedList();

    public MyIQueue(LinkedList lk)
    {
        this.lk = lk;
    }

    @Override
    public void ph(Object obj)
    {
        lk.push(obj);
    }

    @Override
    public boolean mpty()
    {
        return lk.isEmpty();
    }

    @Override
    public Object op()
    {
        return lk.pop();
    }

}