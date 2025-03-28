package com.study.simpleboard.reply.db;

import java.time.LocalDateTime;

import com.study.simpleboard.post.db.PostEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "reply")
public class ReplyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@ToString.Exclude
	private PostEntity post;

	private String userName;

	private String password;

	@Setter
	private String status;

	private String title;

	@Column(columnDefinition = "TEXT")
	private String content;

	private LocalDateTime repliedAt;

}
