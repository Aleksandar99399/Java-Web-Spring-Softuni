package prep.models.binding;

import org.hibernate.validator.constraints.Length;
import prep.models.entities.Class;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class HeroAddBindingModel {
    private String heroName;
    private Class heroClass;
    private int level;

    public HeroAddBindingModel() {
    }

    @Length(min = 3,message = "Hero name must be more 3 characters")
    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    @NotNull(message = "Enter valid category name")
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
