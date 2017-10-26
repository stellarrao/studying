package javaexercise.spring4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javaexercise.spring4.aspectoriented4.ConcertPerformance;
import javaexercise.spring4.aspectoriented4.Performance;
import javaexercise.spring4.aspectoriented4.TrackCounter;

@Configuration
@EnableAspectJAutoProxy
public class TrackCounterConfig
{
    @Bean
    public Performance performance() {
        return new ConcertPerformance();
    }
    
    @Bean
    public TrackCounter trackCounter()
    {
        return new TrackCounter();
    }
}
