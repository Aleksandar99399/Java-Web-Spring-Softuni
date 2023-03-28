package bg.softuni.validation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

public class CityDto {
    private String name;
    private List<@Pattern(regexp = "^\\d{4}$") String> postalCodes;

    @NotEmpty
    public String getName() {
        return name;
    }

    public CityDto setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getPostalCodes() {
        return postalCodes;
    }

    public CityDto setPostalCodes(List<String> postalCodes) {
        this.postalCodes = postalCodes;
        return this;
    }
}
