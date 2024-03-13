package com.example.memorydb.user.db;

import com.example.memorydb.user.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 생략해도 됨.
public interface UserRepository extends JpaRepository<UserEntity, Long> {
// custom Repository이므로 Bean을 생성하지 않고 Service처리 하면 됨.
    /*public List<UserEntity> highScore(int score) {
        return this.findAll().stream()
                .filter(it -> {
                    return (it.getScore() >= score);
                })
                .collect(Collectors.toList());
    }*/
}
