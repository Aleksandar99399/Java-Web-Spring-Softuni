package bg.softuni.aop.sla;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class SlaController {

    @GetMapping("/operation1")
    public String operation1(){
        return "operation1";
    }

    @GetMapping("/operation2")
    public String operation2() throws InterruptedException {
        Thread.sleep(new Random().nextInt(1000));
        return "operation2";
    }
}
