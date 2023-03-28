package bg.beadpuzzle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BeadpuzzleApplication {

    @Autowired
    MyConfiguration myConfiguration;

    @Autowired
    MyBean anAutowiredBean;

    public static void main(String[] args) {
        SpringApplication.run(BeadpuzzleApplication.class, args);
    }

    @PostConstruct
    public void postConstructor(){
        anAutowiredBean.sayHello();
        MyBean myOtherBean=myConfiguration.getAnotherBean();
        myOtherBean.sayHello();

        System.out.println(anAutowiredBean.hashCode());
        System.out.println(myOtherBean.hashCode());
        System.out.println(myOtherBean == anAutowiredBean);
    }

}

@Configuration
class MyConfiguration{
    @Bean
    public MyBean getMyBean(){
        return new MyBean();
    }
    public MyBean getAnotherBean(){
        return getMyBean();
    }
}

class MyBean{

    public MyBean() {
        System.out.println("In the constructor of my bean!"+this.hashCode());
    }

    public void sayHello(){
        System.out.println("Hello, from me! My hash code is "+this.hashCode());
    }
}
