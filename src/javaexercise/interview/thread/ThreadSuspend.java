package javaexercise.interview.thread;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

class ThreadExt extends Thread
{

    private volatile boolean isPark = false;

    private volatile boolean isWorkingAgain = false;

    public ThreadExt()
    {
        this.setName("Thread-ext");
        this.start();
    }

    public void suspendWorkFlow()
    {
        this.interrupt();
        this.isPark = true;
    }

    public void resumeWorkFlow()
    {
        LockSupport.unpark(this);
        this.isPark = false;
        this.isWorkingAgain = true;
    }

    @Override
    public void run()
    {
        final Thread cThread = Thread.currentThread();
        int i = 0;
        while (!cThread.isInterrupted())
        {

            try
            {
                while (isPark)
                {
                    System.out.println("i= " + ++i);
                    LockSupport.park();
                }
                work();
                System.out.println(isWorkingAgain ? "yeh, i am working again..." : "yeh, i am working...");
            } catch (InterruptedException e)
            {
                System.out.println("oh no, i was been Interrupted...");
                //this.interrupt();
                continue;
            }
        }
    }

    private void work() throws InterruptedException
    {
        Thread.sleep(1000);
    }
}

public class ThreadSuspend
{
    public static void main(String[] args) throws IOException, InterruptedException
    {

        ThreadExt threadExt = new ThreadExt();
        Thread.sleep(7000);
        System.out.println("Ready to suspend the Thread " + threadExt.toString());
        threadExt.suspendWorkFlow();

        Thread.sleep(5000);
        System.out.println("Ready to resume the Thread " + threadExt.toString());
        threadExt.resumeWorkFlow();
    }
}
