package javaexercise.spring4.messaging17;

import java.util.Date;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

public class SpittleJmsMain
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/messaging.xml");
        AlertService alertService = context.getBean(AlertService.class);

        
      Spittle spittle = new Spittle(1L, null, "Hello 222", new Date());
      alertService.sendSpittleAlert(spittle);
        
        
      /*  try
        {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        Spittle findSpittle = alertService.retrieveSpittleAlert();
        if(findSpittle != null) {
        System.out.println("message: " + findSpittle.getMessage());
        }
        else {
        System.out.println("Spittle object is null");    
        }
        context.close();
    }
}
