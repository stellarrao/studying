package javaexercise.interview.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

class PipeReaderThread implements Runnable
{
    PipedReader pr;

    String name = null;

    public PipeReaderThread(PipedReader pr, String name)
    {
        this.pr = pr;
        this.name = name;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                char c = (char) pr.read();
                if (c != -1)
                {
                    System.out.println(c);
                }
            }
        } catch (Exception e)
        {
            System.out.println(" PipeThread Exception: " + e);
        }
    }

}

class PipeWriterThread implements Runnable
{
    PipedWriter pw;

    String name = null;

    public PipeWriterThread(PipedWriter pw, String name)
    {
        this.pw = pw;
        this.name = name;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                pw.write("Testing data written ...n");
                pw.flush();
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        } catch (IOException e)
        {
            System.out.println("PipeThread Exception:" + e);
        }
    }

}

public class PipedCommunication
{
    public static void main(String[] args)
    {
        try
        {
            PipedReader pr = new PipedReader();
            PipedWriter pw = new PipedWriter();
            pw.connect(pr);

            Thread thread1 = new Thread(new PipeReaderThread(pr, "ReaderThread"));
            Thread thread2 = new Thread(new PipeWriterThread(pw, "WriterThread"));
            thread1.start();
            thread2.start();
            
        } catch (IOException e)
        {
            System.out.println("PipeThread Exception: " + e);
        }
    }
}
