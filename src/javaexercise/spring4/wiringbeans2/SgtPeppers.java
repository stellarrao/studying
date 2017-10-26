package javaexercise.spring4.wiringbeans2;

import org.springframework.stereotype.Component;

//spring会根据类名指定一个默认的bean ID,也就是将类名的第一个字母变为小写,如果要更改bean ID可以在括号中指定
//@Component("lonelyHeartsClub")
@Component
public class SgtPeppers implements CompactDisc
{
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";

    private String artist = "The Beatles";

    @Override
    public void play()
    {
        System.out.println("Playing " + title + " by " + artist);
    }

}
