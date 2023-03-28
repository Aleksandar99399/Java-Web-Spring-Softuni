package bg.softuni.exam.services;

import bg.softuni.exam.models.enities.User;
import bg.softuni.exam.models.services.UserServiceModel;
import bg.softuni.exam.repositories.UserRepository;
import bg.softuni.exam.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        User user=this.modelMapper.map(userServiceModel,User.class);
        this.userRepository.save(user);
    }

    @Override
    public boolean isUsernameFree(String username) {
        return this.userRepository.findByUsername(username)==null;
    }

    @Override
    public boolean areValidLoginCredentials(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username,password)!=null;
    }
}
