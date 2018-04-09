package javaexercise.interview.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * https://howtodoinjava.com/core-java/multi-threading/how-to-work-with-wait-notify-and-notifyall-in-java/
 * 
 * @author rxh
 */
class WaitNotifyProducer implements Runnable
{

    private final List<Integer> taskQueue;

    private final int MAX_CAPACITY;

    public WaitNotifyProducer(List<Integer> sharedQueue, int size)
    {
        this.taskQueue = sharedQueue;
        MAX_CAPACITY = size;
    }

    @Override
    public void run()
    {
        int counter = 0;
        while (true)
        {
            try
            {
                produce(counter++);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void produce(int i) throws InterruptedException
    {
        synchronized (taskQueue)
        {
            while (taskQueue.size() == MAX_CAPACITY)
            {
                System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }

            Thread.sleep(1000);
            taskQueue.add(i);
            System.out.println("Produced: " + i);
            taskQueue.notifyAll();
        }
    }
}

class WaitNotifyConsumer implements Runnable
{
    private final List<Integer> taskQueue;

    public WaitNotifyConsumer(List<Integer> sharedQueue)
    {
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                consume();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException
    {
        synchronized (taskQueue)
        {
            while (taskQueue.isEmpty())
            {
                System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting , size: " + taskQueue.size());
                taskQueue.wait();
            }

            Thread.sleep(500);
            int i = (Integer) taskQueue.remove(0);
            System.out.println("Consumed: " + i);
            taskQueue.notifyAll();
        }
    }
}

public class WaitNotifyTest
{
    public static void main(String[] args)
    {
        List<Integer> taskQueue = new ArrayList<Integer>();

        int MAX_CAPACITY = 5;

        Thread tProducer = new Thread(new WaitNotifyProducer(taskQueue, MAX_CAPACITY), "Producer");
        Thread tConsumer = new Thread(new WaitNotifyConsumer(taskQueue), "Consumer");

        tProducer.start();
        tConsumer.start();
    }
}
