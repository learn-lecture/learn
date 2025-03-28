package com.study.simpleboard.reply.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
	List<ReplyEntity> findAllByPostIdAndStatusOrderByIdDesc(final Long postId, final String status);
}
