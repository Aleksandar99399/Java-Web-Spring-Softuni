package bg.softuni.exam.models.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewModel {

    private String id;
    private String name;
    private BigDecimal price;
    private String categoryName;
    private String imgUrl;
}
