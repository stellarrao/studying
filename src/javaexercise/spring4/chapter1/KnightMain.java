package javaexercise.spring4.chapter1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javaexercise.spring4.config.KnightConfig;

public class KnightMain
{
    public static void main(String[] args) throws Exception
    {
//        wiredBasedXml();
        wiredBasedJava();
    }
    
    private static void wiredBasedXml()
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/knight.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
        
    }

    private static void wiredBasedJava()
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KnightConfig.class);
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
        
    }
}
