package javaexercise.spring4.chapter1;

public class BraveKnight implements Knight
{
    private Quest quest;
private Minstrel minstrel;

    public BraveKnight(Quest quest, Minstrel minstrel)
    {
        this.quest = quest;
        this.minstrel = minstrel;
    }

    public void embarkOnQuest() //throws QuestException
    {
        minstrel.singBeforeQuest();
        quest.embark();
        minstrel.singAfterQuest();
    }
}
