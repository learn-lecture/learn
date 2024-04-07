package com.study.simpleboard.post.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
	Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(final Long id, final String status);
}
