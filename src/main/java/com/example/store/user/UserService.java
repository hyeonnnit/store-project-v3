package com.example.store.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User editUser(int id, UserRequest.UpdateDTO reqDTO){
        return userRepository.updateById(id, reqDTO);

    }

    public User login(UserRequest.LoginDTO reqDTO){
        return userRepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword());

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
