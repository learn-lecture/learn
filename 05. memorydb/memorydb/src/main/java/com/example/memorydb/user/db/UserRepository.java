package com.example.memorydb.user.db;

import com.example.memorydb.db.SimpleDataRepository;
import com.example.memorydb.user.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {
// custom Repository이므로 Bean을 생성하지 않고 Service처리 하면 됨.
    public List<UserEntity> highScore(int score) {
        return this.findAll().stream()
                .filter(it -> {
                    return (it.getScore() >= score);
                })
                .collect(Collectors.toList());
    }
}
