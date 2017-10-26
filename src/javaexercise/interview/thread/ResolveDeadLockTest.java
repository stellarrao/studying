package javaexercise.interview.thread;

/**
 * 在按F11多次调试后会报错
 * In block 1
In block 2 
ERROR: JDWP Unable to get JNI 1.2 environment, jvm->GetEnv() return code = -2
JDWP exit error AGENT_ERROR_NO_JNI_ENV(183):  [util.c:840]
 * @author rxh
 */
public class ResolveDeadLockTest
{
    public static void main(String[] args)
    {

        ResolveDeadLockTest test = new ResolveDeadLockTest();

        final A a = test.new A();
        final B b = test.new B();

        Runnable block1 = new Runnable()
        {
            public void run()
            {
                synchronized (a)
                {
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    synchronized (b)
                    {
                        System.out.println("In block 1");
                    }
                }

            }
        };

        Runnable block2 = new Runnable()
        {
            public void run()
            {
                synchronized (a)
                {
                    synchronized (b)
                    {
                        System.out.println("In block 2");
                    }
                }
            }
        };

        new Thread(block1).start();
        new Thread(block2).start();
    }

    private class A
    {
        private int i = 10;

        public int getI()
        {
            return i;
        }

        public void setI(int i)
        {
            this.i = i;
        }

    }

    private class B
    {
        private int i = 20;

        public int getI()
        {
            return i;
        }

        public void setI(int i)
        {
            this.i = i;
        }

    }
}
