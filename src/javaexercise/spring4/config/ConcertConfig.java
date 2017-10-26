package javaexercise.spring4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javaexercise.spring4.aspectoriented4.Audience;
import javaexercise.spring4.aspectoriented4.ConcertPerformance;
import javaexercise.spring4.aspectoriented4.DefaultEncoreable;
import javaexercise.spring4.aspectoriented4.Performance;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses= {ConcertPerformance.class, DefaultEncoreable.class})
public class ConcertConfig
{
//    @Bean
//    public Audience audience()
//    {
//        return new Audience();
//    }
    
    @Bean
    public Performance performance()
    {
        return new ConcertPerformance();
    }
}
