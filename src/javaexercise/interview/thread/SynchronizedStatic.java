package javaexercise.interview.thread;

class Shared
{
    static int x;

    static synchronized void showStatic(String s, int a)
    {
        x = a;
        System.out.println("Starting in showStatic " + s + " " + x);
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
        }
        System.out.println("Ending from showStatic " + s + " " + x);
    }

    static synchronized void showStatic1(String s, int a)
    {
        x = a;
        System.out.println("Starting in showStatic1 " + s + " " + x);
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
        }
        System.out.println("Ending from showStatic1 " + s + " " + x);
    }

    synchronized void show(String s, int a)
    {
        x = a;
        System.out.println("Starting show " + s);
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
        }
        System.out.println("Ending from show " + s);
    }

    synchronized void show1(String s, int a)
    {
        x = a;
        System.out.println("Starting show1 " + s);
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
        }
        System.out.println("Ending from show1 " + s);
    }

    public synchronized void synchronizedMethod()
    {
        System.out.println("begin calling synchronizedMethod...");
        try
        {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("finish calling synchronizedMethod...");
    }

    public synchronized static void generalMethod()
    {
        System.out.println("call generalMethod...");
    }
}
 
class CustomThreadStatic extends Thread
{
    Shared s;

    public CustomThreadStatic(Shared s, String str)
    {
        super(str);
        this.s = s;
        start();
    }

    @Override
    public void run()
    {
        Shared.showStatic(Thread.currentThread().getName(), 10);
    }
}

class CustomThreadStatic1 extends Thread
{
    Shared s;

    public CustomThreadStatic1(Shared s, String str)
    {
        super(str);
        this.s = s;
        start();
    }

    @Override
    public void run()
    {
        Shared.showStatic1(Thread.currentThread().getName(), 10);
    }
}

class CustomThread1 extends Thread
{
    Shared s;

    public CustomThread1(Shared s, String str)
    {
        super(str);
        this.s = s;
        start();
    }

    @Override
    public void run()
    {
        s.show(Thread.currentThread().getName(), 20);
    }
}

class CustomThread2 extends Thread
{
    Shared s;

    public CustomThread2(Shared s, String str)
    {
        super(str);
        this.s = s;
        start();
    }

    @Override
    public void run()
    {
        s.show1(Thread.currentThread().getName(), 20);
    }
}

class Foo implements Runnable
{

    @Override
    public void run()
    {
        Lock();
    }

    public void Lock()
    {
        System.out.println(Thread.currentThread().getName());
        // synchronized (this)
        synchronized (Foo.class)
        {
            System.out.println("in block " + Thread.currentThread().getName());
            System.out.println("in block " + Thread.currentThread().getName() + " end");
        }
    }
}

public class SynchronizedStatic
{
    private static void testShared()
    {
        Shared sh = new Shared();
        Shared sh1 = new Shared();

        CustomThreadStatic th = new CustomThreadStatic(sh, "one");
        CustomThreadStatic1 th1 = new CustomThreadStatic1(sh, "two");

        // CustomThread2 t2 = new CustomThread2(sh, "one");
        // CustomThread2 t1 = new CustomThread2(sh1, "two");
        // CustomThread1 t1 = new CustomThread1(sh1, "two");

    }

    private static void testFoo()
    {
        Foo f1 = new Foo();
        Thread t1 = new Thread(f1);
        Thread t2 = new Thread(f1);

        Foo f2 = new Foo();
        Thread t3 = new Thread(f2);
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();

    }

    private static void testSynchronizedGeneralMethod()
    {
        final Shared shared = new Shared();

        Thread t1 = new Thread()
        {
            public void run()
            {
                shared.synchronizedMethod();
            }
        };

        Thread t2 = new Thread()
        {
            public void run()
            {
                shared.generalMethod();
            }
        };

        t1.start();
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        t2.start();
    }

    public static void main(String[] args)
    {
        // testShared();
        // testFoo();
        testSynchronizedGeneralMethod();
    }
}
