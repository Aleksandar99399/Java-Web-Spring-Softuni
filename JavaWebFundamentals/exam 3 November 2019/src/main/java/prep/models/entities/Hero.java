package prep.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {

    private String name;
    private Class heroClass;
    private int level;

    public Hero() {
    }

    @Length(min = 2,message = "Name must be more 2 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated
    public Class getaHeroClass() {
        return heroClass;
    }

    public void setaHeroClass(Class aClass) {
        this.heroClass = aClass;
    }

    @Min(value = 0,message = "Level cannot be less 0")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
