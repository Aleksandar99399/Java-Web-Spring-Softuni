package bg.softuni.validation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;

import java.util.List;

@Component
public class ValidationTest implements CommandLineRunner {

    private final SmartValidator validator;

    public ValidationTest(SmartValidator smartValidator) {
        this.validator = smartValidator;
    }

    @Override
    public void run(String... args) throws Exception {
        CityDto validCity=new CityDto().setName("Sofiq").setPostalCodes(List.of("1111","3323"));

        Errors errors = new BeanPropertyBindingResult(validCity, "valid city");
        validator.validate(validCity,errors);

        if (errors.hasErrors()){
            System.out.println("Should not happen");
        }

        CityDto invalidCity=new CityDto().setName("sda").setPostalCodes(List.of("d11","3322323","da1"));

        Errors invalidError = new BeanPropertyBindingResult(invalidCity, "invalid city");
        validator.validate(invalidCity,invalidError);

        if (invalidError.hasErrors()){
            errors.getAllErrors().forEach(System.out::println);
        }

    }
}
