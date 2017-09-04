package javaexercise.spring4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javaexercise.spring4.chapter1.*;

@Configuration
public class KnightConfig
{
    @Bean
    public Knight knight()
    {
        return new BraveKnight(quest());
    }

    @Bean
    public Quest quest()
    {
        return new SlayDragonQuest(System.out);
    }
}
