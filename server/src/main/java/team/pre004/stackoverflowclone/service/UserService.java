package team.pre004.stackoverflowclone.service;

import team.pre004.stackoverflowclone.domain.user.User;
import team.pre004.stackoverflowclone.domain.user.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createdUser(User user){
        return userRepository.save(user);
    }
}
