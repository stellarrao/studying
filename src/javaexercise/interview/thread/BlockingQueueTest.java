package javaexercise.interview.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class BlockingQueueProducer implements Runnable
{

    protected BlockingQueue<Object> queue;

    public BlockingQueueProducer(BlockingQueue<Object> theQueue)
    {
        this.queue = theQueue;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                Object justProduced = getResource();
                queue.put(justProduced);
                System.out.println("Produced resource - Queue size now = " + queue.size());
            }
        } catch (InterruptedException e)
        {
            System.out.println("Producer INTERRUPTED");
        }
    }

    private Object getResource()
    {
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            System.out.println("Producer Read INTERRUPTED");
        }

        return new Object();
    }

}

class BlockingQueueConsumer implements Runnable
{

    protected BlockingQueue<Object> queue;

    public BlockingQueueConsumer(BlockingQueue<Object> thQueue)
    {
        this.queue = thQueue;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                Object obj = queue.take();
                System.out.println("Consumed resource - Queue size now = " + queue.size());
                take(obj);
            }
        } catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void take(Object obj)
    {
        try
        {
            Thread.sleep(2*1000);
        } catch (InterruptedException e)
        {
            System.out.println("Consumer Read INTERRUPTED");
        }

        System.out.println("Consuming object " + obj);
    }

}

public class BlockingQueueTest
{
    private static void blockingQueueExample()
    {
        int numProducers = 4;
        int numConsumers = 3;

        BlockingQueue<Object> myQueue = new LinkedBlockingQueue<>(20);
        
        for (int i = 0; i < numProducers; i++)
        {
            new Thread(new BlockingQueueProducer(myQueue)).start();
        }
       
        for (int i = 0; i < numConsumers; i++)
        {
            new Thread(new BlockingQueueConsumer(myQueue)).start();
        }
        
        // Let the simulation run for, say, 10 seconds
        try
        {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        // End of simulation - shut down gracefully
        System.exit(0);
    }

    public static void main(String[] args)
    {
        blockingQueueExample();
    }
}
