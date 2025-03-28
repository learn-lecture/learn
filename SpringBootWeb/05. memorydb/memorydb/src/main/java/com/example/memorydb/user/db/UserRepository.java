package com.example.memorydb.user.db;

import java.util.List;

import com.example.memorydb.user.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository // 생략해도 됨.
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	List<UserEntity> findAllByScoreGreaterThanEqual(int score);

	// jpql을 사용한 native query, default = false
	// ?1 first param, ?2 second param, ?n 'n'th param
	// true -> sql 쿼리문 그대로 입력해줘야 함.
	// Param anno로 binding 가능, bind시 query문의 내용을 :변수로 작성
	@Query("select u from user u where u.score >= :min and u.score <= :max")
	List<UserEntity> filterScoreMinMax(
		@Param(value = "min") int min,
		@Param(value = "max") int max
	);
}
