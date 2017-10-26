package javaexercise.interview.thread;

class Producer extends Thread
{

    @Override
    public void run()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println("I am Producer : Produced Item " + i);
             Thread.yield();
        }
    }

}

class Consumer extends Thread
{

    @Override
    public void run()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println("I am Consumer : Consumer Item " + i);
            Thread.yield();
        }
       
    }

}

public class YieldJoinUsage
{
    private static void testYieldUsage()
    {
        Thread producer = new Producer();
        Thread consumer = new Consumer();

        producer.setPriority(Thread.MIN_PRIORITY);
        consumer.setPriority(Thread.MAX_PRIORITY);

        producer.start();
        consumer.start();
    }

    private static void testJoinUsage() throws InterruptedException
    {

        Thread t = new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                System.out.println("First task started");
                System.out.println("Sleeping for 5 seconds");
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    System.out.println("InterruptedException: \n " + e);
                    //e.printStackTrace();
                }
                System.out.println("First task completed");
            }

        });
        
        Thread t1 = new Thread(new Runnable()
        {
            
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                
                System.out.println("Second task completed");
            }
            
        });
        
        t.start();
        t.join();
        t1.start();
    }

    public static void main(String[] args)
    {
        testYieldUsage();
        
        /*try
        {
            testJoinUsage();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }*/
    }
}
