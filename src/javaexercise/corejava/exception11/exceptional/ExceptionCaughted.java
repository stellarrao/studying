package javaexercise.corejava.exception11.exceptional;

import java.io.IOException;

public class ExceptionCaughted
{
    public static void start() throws IOException, RuntimeException
    {
        throw new RuntimeException("Not able to Start");
    }

    public static void main(String args[])
    {
        try
        {
            start();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        // Unreachable catch block for RuntimeException.
        //It is already handled by the catch block for Exception
        // catch (RuntimeException re)
        // {
        // re.printStackTrace();
        // }
    }
}
