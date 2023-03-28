package bg.softuni.exam.models.enities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(unique = true)
    @Size(min = 3,max = 20,message = "Username length must be between 3 and 20 characters")
    private String username;

    @Column
    @Size(min = 3,max = 20,message = "Password length must be between 3 and 20 characters")
    private String password;

    @Column
    @Email(message = "The email is not valid!")
    @NotNull
    private String email;
}
