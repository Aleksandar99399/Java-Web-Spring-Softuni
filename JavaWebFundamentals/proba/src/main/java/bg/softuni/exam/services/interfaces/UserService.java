package bg.softuni.exam.services.interfaces;

import bg.softuni.exam.models.services.UserServiceModel;

public interface UserService {

    void register(UserServiceModel userServiceModel);

    boolean isUsernameFree(String username);

    boolean areValidLoginCredentials(String username,String password);
}
