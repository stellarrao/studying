package javaexercise.spring4.messaging17;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsOperations;

public class JMSMain
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/messaging.xml");
        JmsOperations jms = context.getBean(JmsOperations.class);
        for (int i = 10; i < 10; i++)
        {
            jms.convertAndSend("hello.queue", "Hello");
        }
        context.close();
    }
}
