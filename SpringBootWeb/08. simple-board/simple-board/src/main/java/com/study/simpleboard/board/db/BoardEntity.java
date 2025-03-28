package com.study.simpleboard.board.db;

import java.util.List;

import org.hibernate.annotations.SQLOrder;
import org.hibernate.annotations.SQLRestriction;

import com.study.simpleboard.post.db.PostEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Entity(name = "board")
public class BoardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String boardName;

	private String status;

	@OneToMany(
		mappedBy = "board"
	)
	@SQLRestriction("status = 'REGISTERED'")
	@Builder.Default
	@SQLOrder("id desc")
	private List<PostEntity> posts = List.of();

}
