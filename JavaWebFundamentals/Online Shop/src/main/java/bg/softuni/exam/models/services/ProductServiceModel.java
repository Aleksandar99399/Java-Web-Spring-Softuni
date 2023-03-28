package bg.softuni.exam.models.services;

import bg.softuni.exam.models.enities.Category;
import bg.softuni.exam.models.enities.NameType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductServiceModel {


    private String name;


    private String description;


    private BigDecimal price;

    private LocalDateTime neededBefore;

    private NameType nameType;
}
