package bg.softuni.exam.models.binding;

import bg.softuni.exam.models.enities.Category;
import bg.softuni.exam.models.enities.NameType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductRegisterBindingModel {
    @Size(min = 3,max = 20,message = "Name length must be between 3 and 20 characters")
    private String name;

    @Size(min = 5,message = "Description minimum length must be minimum 5 characters!")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "Invalid date!")
    private LocalDateTime neededBefore;

    @DecimalMin(value = "0.0",inclusive = false,message = "Minimum price needs to be more than 0.0!")
    private BigDecimal price;

    @NotNull(message = "cannot be null!")
    private NameType nameType;
}
