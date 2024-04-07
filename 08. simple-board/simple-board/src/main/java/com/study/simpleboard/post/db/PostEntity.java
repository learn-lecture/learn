package com.study.simpleboard.post.db;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

	private Long boardId;

	private String userName;

	private String password;

	private String email;

	private String status;

	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime postedAt;

	public void setStatus(final String status) {
		this.status = status;
	}

}
