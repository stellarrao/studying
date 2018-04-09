package javaexercise.spring4.messaging17;

public class SpittleAlertHandler
{
    public void handleSpittleAlert(Spittle spittle)
    {
        System.out.println(spittle.getMessage());
    }

}
