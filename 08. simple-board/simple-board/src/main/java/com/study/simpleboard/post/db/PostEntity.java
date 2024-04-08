package com.study.simpleboard.post.db;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.simpleboard.board.db.BoardEntity;
import com.study.simpleboard.reply.db.ReplyEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "post")
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JsonIgnore
	@ToString.Exclude
	private BoardEntity board;

	private String userName;

	private String password;

	private String email;

	@Setter
	private String status;

	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime postedAt;

	@OneToMany(
		mappedBy = "post"
	)
	@Builder.Default
	private List<ReplyEntity> replies = List.of();

}
