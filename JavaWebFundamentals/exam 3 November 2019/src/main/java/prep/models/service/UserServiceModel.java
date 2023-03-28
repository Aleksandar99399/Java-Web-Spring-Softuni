package prep.models.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class UserServiceModel extends BaseServiceModel {
    private String username;
    private String password;
    private String email;
    private String country;

    public UserServiceModel() {
    }

    @Length(min = 2,message = "Username must be more 2 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 2,message = "Password must be more 2 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 2,message = "Enter valid country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
