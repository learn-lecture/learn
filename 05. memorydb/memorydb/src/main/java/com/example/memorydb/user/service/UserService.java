package com.example.memorydb.user.service;

import com.example.memorydb.user.db.UserRepository;
import com.example.memorydb.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service 로직이 들어가는 Bean(DataClass)의 영역
@Service
// @Autowired를 사용하지 않을 때
//@RequiredArgsConstructor
public class UserService {

    //옛날 방식.
    @Autowired
    private UserRepository userRepository;
    // private final UserRepository userRepository;

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

 /*   public void delete(Long id) {
        userRepository.delete(id);
    }
*/
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }
/* My Code
    public List<UserEntity> highScore() {
        var allUser = userRepository.findAll();
        List<UserEntity> highScoreEntity = allUser.stream()
                .filter(it -> {
                    return (it.getScore() >= 70);
                })
                .collect(Collectors.toList());
        return highScoreEntity;
    }
*/
  /*  public List<UserEntity> highScore(int score) {
        return userRepository.highScore(score);
    }*/
}
