package javaexercise.spring4.aspectoriented4;


public class ConcertPerformance implements Performance
{

    @Override
    public void perform()
    {
        System.out.println("I'm sing ...");
        //throw new NullPointerException("null");
    }

    @Override
    public void playTrack(int trackNumber)
    {
        System.out.println("trackNumber: " + trackNumber);
        
    }

}