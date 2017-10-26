package javaexercise.spring4.aspectoriented4;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect
public class TrackCounter
{
    private Map<Integer, Integer> trackCounts = new HashMap<Integer, Integer>();

//    @Pointcut("execution(* javaexercise.spring4.aspectoriented4.Performance.playTrack(int))"
//    + " && args(trackNumber)")
    public void trackPlayed(int trackNumber)
    {

    }

//    @Before("trackPlayed(trackNumber)")
    public void countTrack(int trackNumber)
    {
        int currentCount = getPlayCount(trackNumber);
        trackCounts.put(trackNumber, currentCount + 1);
        System.out.println("countTrack....");
    }

    public int getPlayCount(int trackNumber)
    {
        return trackCounts.containsKey(trackNumber) ? trackCounts.get(trackNumber) : 0;
    }
}
