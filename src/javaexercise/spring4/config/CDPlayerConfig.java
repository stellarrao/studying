package javaexercise.spring4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javaexercise.spring4.wiringbeans2.CDPlayer;
import javaexercise.spring4.wiringbeans2.CompactDisc;
import javaexercise.spring4.wiringbeans2.SgtPeppers;

@Configuration
/*
 * 按照默认规则，它会以配置类所在的包作为基础包来扫描组件 如果要更改基础包需在@ComponentScan的value属性中指明包的名称
 */
// @ComponentScan("javaexercise.spring4.wiringbeans2")
// @ComponentScan(basePackageClasses= {SgtPeppers.class})
public class CDPlayerConfig
{
    @Bean
    //@Bean(name="lonelyHeartsClubBand")
    public CompactDisc sgtPeppers()
    {
        return new SgtPeppers();
    }
    
    @Bean
    public CompactDisc randomBeatlesCD() {
        int choice = (int)Math.floor(Math.random() * 4);
        
        if (choice == 0)
        {
            return new SgtPeppers();
        }
        else
        {
            return new SgtPeppers();
        }
    }
    
    @Bean
    public CDPlayer cdPlayer() {
        return new CDPlayer(sgtPeppers());
    }
    
    @Bean
    public CDPlayer anotherCDPlayer() {
        return new CDPlayer(sgtPeppers());
    }
    
    @Bean
    public CDPlayer cdPlayer(CompactDisc compactDisc) {
        return new CDPlayer(compactDisc);
    }
}
