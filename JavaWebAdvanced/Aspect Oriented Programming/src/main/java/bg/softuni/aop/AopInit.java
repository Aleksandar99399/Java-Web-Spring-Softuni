package bg.softuni.aop;

import bg.softuni.aop.models.Student;
import bg.softuni.aop.sla.SLOsConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AopInit implements CommandLineRunner {

    private final SLOsConfig slOsConfig;

    public AopInit(SLOsConfig slOsConfig) {
        this.slOsConfig = slOsConfig;

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(slOsConfig);
    }


}
