package javaexercise.concurrencypractice.chapter7;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BrokenPrimeProducer extends Thread
{
private final BlockingQueue<BigInteger> queue;
private volatile boolean cancelled = false;
public BrokenPrimeProducer(BlockingQueue<BigInteger> queue)
{
    this.queue = queue;
}

public void run() {
    try
    {
        BigInteger p = BigInteger.ONE;
        while (!cancelled)
        {
            queue.put(p = p.nextProbablePrime());
        }
    } catch (InterruptedException consumed)
    {
    }
    
}

public void cancel() {
    cancelled = true;
}

void consumePrimes() throws InterruptedException {
    BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<BigInteger>();
    BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
    producer.start();
    
    try
    {
        //needMorePrimes()
        while (cancelled)
        {
            //consume(prime.take());
        }
    } finally
    {
        producer.cancel();
    }
}
}
