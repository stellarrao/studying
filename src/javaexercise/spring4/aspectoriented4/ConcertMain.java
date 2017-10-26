package javaexercise.spring4.aspectoriented4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javaexercise.spring4.config.TrackCounterConfig;

public class ConcertMain
{
    public static void main(String[] args)
    {
        wiredBasedJava();
         

    }

    private static void wiredBasedJava()
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TrackCounterConfig.class);
        Performance performance = context.getBean(Performance.class);
        //performance.perform();
        performance.playTrack(1);
    }

    private static void wiredBasedXml()
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/concert.xml");
//        Audience audience = context.getBean(Audience.class);
//        audience.applause();
        Performance performance = context.getBean(Performance.class);
        performance.perform();

    }

}
