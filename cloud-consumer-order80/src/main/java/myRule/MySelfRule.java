package myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //不能被扫描到
public class MySelfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();//自定义为随机
    }
}
