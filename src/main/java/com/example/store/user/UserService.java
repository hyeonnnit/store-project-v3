package com.example.store.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponse.LoginDTO login(UserRequest.LoginDTO reqDTO){
        User user = userRepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword());
        return new UserResponse.LoginDTO(user);
    }
    @Transactional
    public UserResponse.JoinDTO join(UserRequest.JoinDTO reqDTO) {
        User user = userRepository.save(reqDTO.toEntity());
        return new UserResponse.JoinDTO(user);
    }

    public User getUser(int id){
        User user = userRepository.findById(id);
        return user;
    }
}
