package bg.softuni.exam.models.enities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(unique = true)
    @Size(min = 3,max = 20,message = "Name length must be between 3 and 20 characters")
    private String name;

    @Column
    @Size(min = 5,message = "Description minimum length must be minimum 5 characters!")
    private String description;


    @Column
    @DecimalMin(value = "0.0",inclusive = false,message = "Minimum price is more than 0.0!")
    private BigDecimal price;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "Invalid date!")
    private LocalDateTime neededBefore;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    @NotNull
    private Category category;
}
