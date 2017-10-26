package javaexercise.concurrencypractice.chapter7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class PrimeGenerator implements Runnable
{
    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<BigInteger>();

    private volatile boolean cancelled;

    @Override
    public void run()
    {
        BigInteger p = BigInteger.ONE;
        while (!cancelled)
        {
            p = p.nextProbablePrime();
            synchronized (this)
            {
                primes.add(p);
            }
        }
    }

    public void cancel()
    {
        cancelled = true;
    }

    public synchronized List<BigInteger> get()
    {
        return new ArrayList<BigInteger>(primes);
    }

    public List<BigInteger> aSecondOfPrimes() throws InterruptedException
    {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();

        try
        {
            TimeUnit.SECONDS.sleep(1);
        } finally
        {
            generator.cancel();
        }

        return generator.get();
    }

    public static void main(String[] args)
    {

        /*try
        {
            List<BigInteger> bigInteger = new PrimeGenerator().aSecondOfPrimes();
            for (BigInteger primeNumber : bigInteger)
            {

                System.out.println(primeNumber);
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }*/
        
        BigInteger b = BigInteger.ONE;
        b = b.nextProbablePrime();
        while(b.compareTo(BigInteger.valueOf(100)) == -1) {
            System.out.println(b);
            b = b.nextProbablePrime();
        }
    }
}
