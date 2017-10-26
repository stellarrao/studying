package javaexercise.interview.thread;

public class MyThread 
implements Runnable 
//extends Thread
{
    private String name;
    private int ticket = 100;
    public MyThread(String name)
    {
        super();
        this.name = name;
    }

    @Override
    public void run()
    {
        //int ticket = 100;
        for (int i = 0; i < 50; i++)
        {
            
            if(ticket > 0)
//            System.out.println("线程开始: " + this.name + ",i=" + i);
            System.out.println("卖票: " + Thread.currentThread().getName() +", i="+i +", ticket "+ ticket--);
            
        }
    }

    private static void startTest() {
      MyThread thread = new MyThread("线程a");
      //第一种  
      //表明: run()和其他方法的调用没任何不同,main方法按顺序执行了它,并打印出最后一句  
      //thread.run();  
        
      //第二种  
      //表明: start()方法重新创建了一个线程,在main方法执行结束后,由于start()方法创建的线程没有运行结束,  
      //因此主线程未能退出,直到线程thread也执行完毕.这里要注意,默认创建的线程是用户线程(非守护线程)  
      //thread.start();  
        
      //第三种  
      //1、为什么没有打印出100句呢?因为我们将thread线程设置为了daemon(守护)线程,程序中只有守护线程存在的时候,是可以退出的,所以只打印了七句便退出了  
      //2、当java虚拟机中有守护线程在运行的时候，java虚拟机会关闭。当所有常规线程运行完毕以后，  
      //守护线程不管运行到哪里，虚拟机都会退出运行。所以你的守护线程最好不要写一些会影响程序的业务逻辑。否则无法预料程序到底会出现什么问题  
      //thread.setDaemon(true);  
      //thread.start();  
      //System.out.println("是否守护线程"+thread.isDaemon());
      
      //第四种  
      //用户线程可以被System.exit(0)强制kill掉,所以也只打印出七句  
//      thread.start();  
//      System.out.println("main thread is over");  
//      System.exit(1);  
    }
    
    private static void startShareTicket() {
      MyThread mt1 = new MyThread("线程a");
      MyThread mt2 = new MyThread("线程b");
      MyThread mt3 = new MyThread("线程c");

//      mt1.start();
//      mt2.start();
//      mt3.start();
        
    }
    private static void startSingleTicket() {
        MyThread mt1 = new MyThread("线程a");
        //MyThread mt2 = new MyThread("线程b");
        //MyThread mt3 = new MyThread("线程c");
        
        new Thread(mt1).start();
        new Thread(mt1).start();
        new Thread(mt1).start();
        //mt2.start();
        //mt3.start();
        
        
    }
    
    public static void main(String[] args)
    {

        //startShareTicket();
        startSingleTicket();
//        startTest();
    }
}
