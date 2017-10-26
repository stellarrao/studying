package javaexercise.interview.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadWait
{
    public static void main(String[] args)
    {
        clientInvoke();
    }

    private static void clientInvoke()
    {
        for (int index = 0; index < 1; index++)
        {
            ExecutorService executorService = Executors.newFixedThreadPool(8);
            CountDownLatch latch = new CountDownLatch(3);

            Boss boss = new Boss(latch);
            executorService.execute(boss);

            for (int i = 0; i < 3; i++)
            {
                Employee employee = new Employee("employee" + i, latch);
                executorService.execute(employee);
            }

            executorService.shutdown();

            try
            {
                executorService.awaitTermination(10000, TimeUnit.DAYS);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Employee implements Runnable
{

    private String name;

    private CountDownLatch latch;

    public Employee(String name, CountDownLatch latch)
    {
        this.name = name;
        this.latch = latch;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread() + name + " 员工正在处理");
        try
        {
            Thread.sleep(300 + (int) (Math.random() * 2000));

            System.out.println(Thread.currentThread() + name + " 员工处理结束");

            latch.countDown();

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

class Boss implements Runnable
{

    private CountDownLatch latch;

    public Boss(CountDownLatch latch)
    {
        this.latch = latch;
    }

    @Override
    public void run()
    {
        long t = 0;
        try
        {
            //Thread.sleep(300 + (int) (Math.random() * 2000));
            Thread.sleep(60*60*1000);
            long start = System.currentTimeMillis();

            latch.await();
            t = System.currentTimeMillis() - start;
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Boss开始处理...wait:" + t);
    }

}