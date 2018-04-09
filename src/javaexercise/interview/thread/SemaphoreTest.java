package javaexercise.interview.thread;

import java.util.Date;
import java.util.concurrent.Semaphore;

class SemaphorePrintingJob implements Runnable
{
    private SemaphorePrinterQueue printerQueue;

    public SemaphorePrintingJob(SemaphorePrinterQueue printerQueue)
    {
        this.printerQueue = printerQueue;
    }

    @Override
    public void run()
    {
        System.out.printf("%s: Going to print a document\n", Thread.currentThread().getName());
        printerQueue.printJob(new Object());
    }

}

class SemaphorePrinterQueue
{
    private final Semaphore semaphore;

    public SemaphorePrinterQueue()
    {
        this.semaphore = new Semaphore(1);
        
    }

    public void printJob(Object document)
    {

        try
        {
            semaphore.acquire();
            Long duration = (long) (Math.random() * 10000);
            System.out.println(
                    Thread.currentThread().getName() + ": PrintQueue: Printing a Job during " + (duration / 1000) + " seconds :: Time - "
                            + new Date());
            Thread.sleep(duration);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
            semaphore.release();
        }
    }
}

public class SemaphoreTest
{
    public static void main(String[] args)
    {
        SemaphorePrinterQueue printerQueue = new SemaphorePrinterQueue();
        Thread[] thread = new Thread[10];
        for (int i = 0; i < 10; i++)
        {
            thread[i] = new Thread(new SemaphorePrintingJob(printerQueue), "Thread " + i);
        }

        for (int i = 0; i < 10; i++)
        {
            thread[i].start();
        }
    }
}
