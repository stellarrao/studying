package javaexercise.interview.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ApplicationStartupUtil
{
    public abstract class BaseHealthChecker implements Runnable
    {

        private CountDownLatch latch;

        private String serviceName;

        private boolean serviceUp;

        public BaseHealthChecker(CountDownLatch latch, String serviceName)
        {
            super();
            this.latch = latch;
            this.serviceName = serviceName;
        }

        @Override
        public void run()
        {
            try
            {
                verifyService();
                serviceUp = true;
            } catch (Throwable t)
            {
                t.printStackTrace(System.err);
                serviceUp = false;
            } finally
            {
                if (latch != null)
                {
                    latch.countDown();
                }
            }
        }

        public String getServiceName()
        {
            return serviceName;
        }

        public boolean isServiceUp()
        {
            return serviceUp;
        }

        public abstract void verifyService();
    }

    public class NetworkHealthChecker extends BaseHealthChecker
    {

        public NetworkHealthChecker(CountDownLatch latch)
        {
            super(latch, "Network Service");

        }

        @Override
        public void verifyService()
        {
            System.out.println("Checking " + this.getServiceName());
            try
            {
                Thread.sleep(60*1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(this.getServiceName() + " is UP");
        }

    }
    public class CacheHealthChecker extends BaseHealthChecker
    {
        
        public CacheHealthChecker(CountDownLatch latch)
        {
            super(latch, "Cache Service");
            
        }
        
        @Override
        public void verifyService()
        {
            System.out.println("Checking " + this.getServiceName());
            try
            {
                Thread.sleep(2*60*1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(this.getServiceName() + " is UP");
        }
        
    }
    public class DatabaseHealthChecker extends BaseHealthChecker
    {
        
        public DatabaseHealthChecker(CountDownLatch latch)
        {
            super(latch, "Database Service");
            
        }
        
        @Override
        public void verifyService()
        {
            System.out.println("Checking " + this.getServiceName());
            try
            {
                Thread.sleep(3*60*1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(this.getServiceName() + " is UP");
        }
        
    }

    private static List<BaseHealthChecker> services;

    private static CountDownLatch latch;

    private static final ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();

    public ApplicationStartupUtil()
    {
    }

    public static ApplicationStartupUtil getInstance()
    {
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws Exception
    {
        latch = new CountDownLatch(3);

        services = new ArrayList<BaseHealthChecker>();
        
        services.add(getInstance().new CacheHealthChecker(latch));
        services.add(getInstance().new NetworkHealthChecker(latch));
        services.add(getInstance().new DatabaseHealthChecker(latch));

        Executor executor = Executors.newFixedThreadPool(services.size());

        for (final BaseHealthChecker c : services)
        {
            executor.execute(c);
        }

        latch.await(10*1000, TimeUnit.MILLISECONDS);

        for (final BaseHealthChecker c : services)
        {
            if (!c.isServiceUp())
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        boolean result = false;
        try
        {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("External service validation completed! Result was: " + result);
    }
}
