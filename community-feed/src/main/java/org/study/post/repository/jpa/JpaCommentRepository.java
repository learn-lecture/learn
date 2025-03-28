package org.study.post.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.study.post.repository.entity.comment.CommentEntity;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query("""
        update CommentEntity c set c.content = :#{#comment.getContent()}, c.updDt = now()
        where c.id = :#{#comment.getId()}
        """)
    void updateComment(CommentEntity comment);

    @Modifying
    @Query("""
        update CommentEntity c set c.likeCount = c.likeCount + :likeCount, c.updDt = now()
        WHERE c.id = :commentId
        """)
    void updateLikeCount(Long commentId, Integer likeCount);

}
