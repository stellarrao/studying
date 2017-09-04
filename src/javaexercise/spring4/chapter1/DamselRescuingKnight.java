package javaexercise.spring4.chapter1;

public class DamselRescuingKnight implements Knight
{
private RescueDamselQuest quest;

public DamselRescuingKnight()
{
    this.quest = new RescueDamselQuest();
}

@Override
public void embarkOnQuest()
{
    quest.embark();
}

}
