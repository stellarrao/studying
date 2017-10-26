package javaexercise.interview.thread;

public class PriorityThreadTest implements Runnable
{

    @Override
    public void run()
    {
        for (int i = 0; i < 100000; i++)
        {
            Math.hypot(Math.pow(924526789, i), Math.cos(i));
        }

        System.out.println(" Thread priority: " + Thread.currentThread().getPriority());
    }

    public void start(int _priority)
    {

        Thread t = new Thread(this);
        // 设置线程优先级
        t.setPriority(_priority);
        t.start();
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 20; i++)
        {
            new PriorityThreadTest().start(i % 10 + 1);
        }
    }
}
