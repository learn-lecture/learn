package org.study.post.repository.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.study.post.domain.Post;
import org.study.post.repository.entity.post.PostEntity;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Modifying
    @Query(value = """
        update PostEntity p set p.content = :#{#postEntity.getContent()},
        p.state = :#{#postEntity.getState()}, p.updDt = :#{#postEntity.getUpdDt()}
        where p.id = :#{#postEntity.getId()}
    """)
    void updatePostEntity(PostEntity postEntity);

}
