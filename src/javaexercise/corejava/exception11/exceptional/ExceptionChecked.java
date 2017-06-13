package javaexercise.corejava.exception11.exceptional;

import java.io.IOException;

@SuppressWarnings("unused")
public class ExceptionChecked
{
    public static void start()
    {
        System.out.println("Java Exception interview question Answers for Programmers");
    }

    public static void main(String args[])
    {
        try
        {
            start();
        }
        //Unreachable catch block for IOException. 
        //This exception is never thrown from the try statement body
        /*catch (IOException ioe)
        {
            ioe.printStackTrace();
        }*/
        finally{
            
        }
    }
}
