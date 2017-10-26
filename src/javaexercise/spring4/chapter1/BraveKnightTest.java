package javaexercise.spring4.chapter1;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Test;

public class BraveKnightTest
{
    @Test
    public void knightShouldEmbarkOnQuest()
    {

        // 创建mock Quest
        Quest mockQuest = mock(Quest.class);
        Minstrel mockMinstrel = mock(Minstrel.class);
        
        //注入 mock Quest
        BraveKnight knight = new BraveKnight(mockQuest, mockMinstrel);
        knight.embarkOnQuest();
        //verify(mockQuest, times(1)).embark();
        
        
    }
}
