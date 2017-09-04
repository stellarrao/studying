package javaexercise.spring4.chapter1;

import static org.mockito.Mockito.*;
import org.junit.Test;

public class BraveKnightTest
{
    @Test
    public void knightShouldEmbarkOnQuest()
    {

        // 创建mock Quest
        Quest mockQuest = mock(Quest.class);
        
        //注入 mock Quest
        BraveKnight knight = new BraveKnight(mockQuest);
        knight.embarkOnQuest();
        verify(mockQuest, times(1)).embark();
    }
}
