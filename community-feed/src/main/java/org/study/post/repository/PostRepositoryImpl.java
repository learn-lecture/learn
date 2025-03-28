package org.study.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.post.application.interfaces.PostRepository;
import org.study.post.domain.Post;
import org.study.post.repository.entity.post.PostEntity;
import org.study.post.repository.jpa.JpaPostRepository;
import org.study.post.repository.postqueue.UserPostQueueCommandRepository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository commandRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        if (postEntity.getId() != null) {
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();
        }

        postEntity = jpaPostRepository.save(postEntity);
        commandRepository.publishPost(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        return jpaPostRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new)
            .toPost();
    }

}
