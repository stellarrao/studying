package javaexercise.spring4.aspectoriented4;

import org.springframework.stereotype.Component;

@Component
public class DefaultEncoreable implements Encoreable
{

    @Override
    public void performEncore()
    {
        System.out.println("Please perform again! ");
    }

}
