package javaexercise.spring4.test;

import static org.mockito.Mockito.*;
import org.junit.Test;

import javaexercise.spring4.wiringbeans2.BlankDisc;

public class BlankDiscTest
{
    @Test
    public void createBlankDisc()
    {
        // BlankDisc blank = new BlankDisc();
        BlankDisc blank = mock(BlankDisc.class);
        blank.play();
    }
}
