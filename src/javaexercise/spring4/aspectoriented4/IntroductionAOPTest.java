package javaexercise.spring4.aspectoriented4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javaexercise.spring4.config.ConcertConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/concert.xml")
public class IntroductionAOPTest
{
    @Autowired
    private Performance performance;
    
    @Test
    public void run() {
        ((Encoreable)performance).performEncore();
    }
}
