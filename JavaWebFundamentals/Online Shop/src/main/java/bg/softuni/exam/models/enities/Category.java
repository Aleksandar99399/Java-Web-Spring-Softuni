package bg.softuni.exam.models.enities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
@RequiredArgsConstructor
public class Category extends BaseEntity {

    @NonNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private NameType name;

    @Column
    @NonNull
    private String description;


}
