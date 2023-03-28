package prep.models.service;

import org.hibernate.validator.constraints.Length;
import prep.models.entities.Class;

import javax.validation.constraints.Min;

public class HeroServiceModel {
    private String name;
    private Class heroClass;
    private int level;

    public HeroServiceModel() {
    }

    @Length(min = 2,message = "Name must be more 2 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(Class heroClass) {
        this.heroClass = heroClass;
    }

    @Min(value = 0,message = "Level cannot be less 0")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
