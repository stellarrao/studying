package javaexercise.interview.thread;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MultiSemaphorePrintingJob implements Runnable
{
    private MultiSemaphorePrinterQueue printerQueue;

    public MultiSemaphorePrintingJob(MultiSemaphorePrinterQueue printerQueue)
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

class MultiSemaphorePrinterQueue
{
    // This Semaphore will keep track of no. of printers used at any point of time.
    private final Semaphore semaphore;

    // While checking/acquiring a free printer out of three available printers, we will use this lock.
    private final Lock printerLock;

    // This array represents the pool of free printers.
    private boolean[] freePrinters;

    public MultiSemaphorePrinterQueue()
    {
        this.semaphore = new Semaphore(3);

        freePrinters = new boolean[3];
        for (int i = 0; i < freePrinters.length; i++)
        {
            freePrinters[i] = true;
        }

        printerLock = new ReentrantLock();
    }

    public void printJob(Object document)
    {

        try
        {
            // Decrease the semaphore counter to mark a printer busy
            semaphore.acquire();

            // Get the free printer
            int assignedPrinter = getPrinter();

            Long duration = (long) (Math.random() * 10000);
            System.out.println(
                    Thread.currentThread().getName() + " : Printer " + assignedPrinter + " : PrintQueue: Printing a Job during " + (duration / 1000)
                            + " seconds :: Time - " + new Date());
            Thread.sleep(duration);
            // Printing is done; Free the printer to be used by other threads.

            releasePrinter(assignedPrinter);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());

            // Increase the semaphore counter back
            semaphore.release();
        }
    }

    // Acquire a free printer for printing a job
    private int getPrinter()
    {
        int foundPrinter = -1;
        try
        {
            // Get a lock here so that only one thread can go beyond this at a time
            printerLock.lock();
            
            // Check which printer is free
            for (int i = 0; i < freePrinters.length; i++)
            {
                // If free printer found then mark it busy
                if (freePrinters[i])
                {
                    foundPrinter = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            // Allow other threads to check for free priniter
            printerLock.unlock();
        }

        return foundPrinter;
    }

    // Release the printer
    private void releasePrinter(int i)
    {
        printerLock.lock();
        
        // Mark the printer free
        freePrinters[i] = true;
        
        printerLock.unlock();
    }
}

public class MultiSemaphoreTest
{
    public static void main(String[] args)
    {
        MultiSemaphorePrinterQueue printerQueue = new MultiSemaphorePrinterQueue();
        Thread[] thread = new Thread[10];
        for (int i = 0; i < 10; i++)
        {
            thread[i] = new Thread(new MultiSemaphorePrintingJob(printerQueue), "Thread " + i);
        }

        for (int i = 0; i < 10; i++)
        {
            thread[i].start();
        }
    }
}
