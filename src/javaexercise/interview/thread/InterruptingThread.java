package javaexercise.interview.thread;

import java.io.IOException;
import java.net.ServerSocket;

class InterruptingThread1 extends Thread
{
    @Override
    public void run()
    {
        try
        {
            
            //System.out.println("before interrupted status : " + Thread.currentThread().isInterrupted());
            Thread.sleep(1000);
            
            System.out.println("task");
        } catch (InterruptedException e)
        {
            System.out.println("interrupted status : " + Thread.currentThread().isInterrupted());
            //throw new RuntimeException("Thread interrupted... \n" + e);
        }
    }
}

class InterruptingThread2 extends Thread
{
    @Override
    public void run()
    {
        try
        {
            System.out.println("CurrentThread Name : " + Thread.currentThread().getName());
            Thread.sleep(2 * 1000);
            System.out.println("task end");
        } catch (InterruptedException e)
        {
            System.out.println("Interrupt status : " + Thread.currentThread().isInterrupted());
            System.out.println("CurrentThread Name : " + Thread.currentThread().getName());
            Thread.currentThread().interrupt();
            System.out.println("Exception handled \n" + e);
        }

        System.out.println("Interrupt status : " + Thread.currentThread().isInterrupted());
        System.out.println("thread is running...");
    }
}

class InterruptingThread3 extends Thread
{

    @Override
    public void run()
    {
        for (int i = 1; i <= 5; i++)
        {
            System.out.println(i);
        }
    }

}

class InterruptingThread4 extends Thread
{

    @Override
    public void run()
    {
        for (int i = 1; i <= 2; i++)
        {
            if (Thread.interrupted())
            {
                System.out.println("code for interrupted thread " + Thread.currentThread().getName());
            } else
            {
                System.out.println("code for normal thread " + Thread.currentThread().getName());
            }
        }
    }

}

class RunnableClass
{
    Runnable runnable = new Runnable()
    {

        @Override
        public void run()
        {
            int i = 0;
            try
            {
                while (i < 1000)
                {
                    Thread.sleep(500);
                    System.out.println(i++);
                }
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    };

}

/**
 * 中断I/O操作
 * 
 * @author rxh
 */
class InterruptingThread5 extends Thread
{
    volatile ServerSocket socket;

    public void testInterruptingThread5() throws Exception
    {

        InterruptingThread5 thread = new InterruptingThread5();
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop...");
        Thread.currentThread().interrupt();
        // thread.socket.close();
        try
        {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
        }
        System.out.println("Stopping application...");
    }

    @Override
    public void run()
    {
        try
        {
            socket = new ServerSocket(8888);
        } catch (IOException e)
        {
            System.out.println("Could not create the socket...");
            return;
        }

        while (!Thread.currentThread().isInterrupted())
        {
            System.out.println("Waiting for connection...");
            try
            {
                socket.accept();
            } catch (IOException e)
            {
                System.out.println("accept() failded or interrupted... \n" + e);
                // 重新设置中断标识
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Thread exiting under request...");
    }

}

/**
 * 死锁状态线程无法被中断
 * 
 * @author rxh
 */
class InterruptingThread6 extends Thread
{
    public void testInterringThread6() throws Exception
    {
        final Object lock1 = new Object();
        final Object lock2 = new Object();

        Thread thread1 = new Thread()
        {
            public void run()
            {
                deathLock(lock1, lock2);
            }
        };

        Thread thread2 = new Thread()
        {
            public void run()
            {
                deathLock(lock2, lock1);
            }
        };

        System.out.println("Starting thread...");
        thread1.start();
        thread2.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        thread1.interrupt();
        thread2.interrupt();
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }

    void deathLock(Object lock1, Object lock2)
    {
        try
        {
            synchronized (lock1)
            {
                // 不会在这里死掉
                Thread.sleep(10);
                // 会锁在这里，虽然阻塞，但不会抛异常
                synchronized (lock2)
                {
                    System.out.println(Thread.currentThread());
                }
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

/**
 * 使用thread.interrupt()中断阻塞状态线程
 * 
 * @author rxh
 */
class InterruptingThread7 extends Thread
{

    public void testInterruptingThread7() throws Exception
    {
        InterruptingThread7 thread = new InterruptingThread7();
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop...");
        thread.interrupt();// 等中断信号量设置后再调用
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }

    public void run()
    {
        while (!Thread.currentThread().isInterrupted())
        {
            System.out.println("Thread running...");
            try
            {
                /*
                 * 如果线程阻塞，将不会去检查中断信号量stop变量，所 以thread.interrupt() 会使阻塞线程从阻塞的地方抛出异常，让阻塞线程从阻塞状态逃离出来，并 进行异常块进行 相应的处理
                 */
                Thread.sleep(1000);// 线程阻塞，如果线程收到中断操作信号将抛出异常
            } catch (InterruptedException e)
            {
                System.out.println("Thread interrupted...");
                /*
                 * 如果线程在调用 Object.wait()方法，或者该类的 join() 、sleep()方法 过程中受阻，则其中断状态将被清除
                 */
                System.out.println(this.isInterrupted());// false

                // 中不中断由自己决定，如果需要真真中断线程，则需要重新设置中断位，如果
                // 不需要，则不用调用
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread exiting under request...");
    }
}

/**
 * 使用thread.interrupt()中断非阻塞状态线程
 * 
 * @author rxh
 */
class InterruptingThread8 extends Thread
{
    public void testInterruptingThread8() throws Exception
    {
        InterruptingThread8 thread = new InterruptingThread8();
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop...");
        // 发出中断请求
        thread.interrupt();
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }

    public void run()
    {
        // 每隔一秒检测是否设置了中断标示
        while (!Thread.currentThread().isInterrupted())
        {
            System.out.println("Thread is running...");
            long time = System.currentTimeMillis();
            // 使用while循环模拟 sleep
            while ((System.currentTimeMillis() - time < 1000))
            {
            }
        }
        System.out.println("Thread exiting under request...");
    }
}

/**
 * 使用中断信号量中断非阻塞状态的线程
 * 
 * @author rxh
 */
class InterruptingThread9 extends Thread
{
    volatile boolean stop = false;// 线程中断信号量

    public void testInterruptingThread9() throws Exception
    {
        InterruptingThread9 thread = new InterruptingThread9();
        System.out.println("Starting thread...");
        thread.start();
        Thread.sleep(3000);
        System.out.println("Asking thread to stop...");
        // 设置中断信号量
        thread.stop = true;
        Thread.sleep(3000);
        System.out.println("Stopping application...");
    }

    public void run()
    {
        // 每隔一秒检测一下中断信号量
        while (!stop)
        {
            System.out.println("Thread is running...");
            long time = System.currentTimeMillis();
            /*
             * 使用while循环模拟 sleep 方法，这里不要使用sleep，否则在阻塞时会 抛 InterruptedException异常而退出循环，这样while检测stop条件就不会执行， 失去了意义。
             */
            while ((System.currentTimeMillis() - time < 1000))
            {
            }
        }
        System.out.println("Thread exiting under request...");
    }
}

public class InterruptingThread
{
    private static void TestInterruptingThread1()
    {
        InterruptingThread1 t1 = new InterruptingThread1();
        t1.setName("t1");
        t1.start();
        
        System.out.println("currentThread namne : " + Thread.currentThread().getName());
        
        Thread.currentThread().interrupt();
    }

    private static void TestInterruptingThread2()
    {
        InterruptingThread2 t2 = new InterruptingThread2();
        t2.start();

        t2.interrupt();

    }

    private static void TestInterruptingThread3()
    {
        InterruptingThread3 t3 = new InterruptingThread3();
        t3.start();
        t3.interrupt();

    }

    private static void TestInterruptingThread4()
    {

        InterruptingThread4 t41 = new InterruptingThread4();
        t41.setName("t41");
        InterruptingThread4 t42 = new InterruptingThread4();
        t42.setName("t42");

        t41.start();
        t41.interrupt();

        t42.start();

    }

    private static void TestRunnableClass()
    {
        Thread t1 = new Thread(new RunnableClass().runnable);
        System.out.println("main in TestRunnableClass method");

        t1.start();

        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        t1.interrupt();
    }

    private static void testInterruptingThread5()
    {

        InterruptingThread5 thread = new InterruptingThread5();
        try
        {
            thread.testInterruptingThread5();
        } catch (Exception e)
        {

            e.printStackTrace();
        }

    }

    private static void testInterruptingThread6()
    {

        InterruptingThread6 thread = new InterruptingThread6();
        try
        {
            thread.testInterringThread6();
        } catch (Exception e)
        {

            e.printStackTrace();
        }

    }

    private static void testInterruptingThread7()
    {

        InterruptingThread7 thread = new InterruptingThread7();
        try
        {
            thread.testInterruptingThread7();
        } catch (Exception e)
        {

            e.printStackTrace();
        }

    }

    private static void testInterruptingThread8()
    {

        InterruptingThread8 thread = new InterruptingThread8();
        try
        {
            thread.testInterruptingThread8();
        } catch (Exception e)
        {

            e.printStackTrace();
        }

    }

    private static void testInterruptingThread9()
    {
        InterruptingThread9 thread = new InterruptingThread9();
        try
        {
            thread.testInterruptingThread9();
        } catch (Exception e)
        {

            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        // TestRunnableClass();
        TestInterruptingThread1();
        // TestInterruptingThread2();
        // TestInterruptingThread3();
        // TestInterruptingThread4();
        // testInterruptingThread5();
        // testInterruptingThread6();
        // testInterruptingThread7();
        // testInterruptingThread8();
        // testInterruptingThread9();
    }
}
