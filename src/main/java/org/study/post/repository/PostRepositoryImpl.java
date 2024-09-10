package org.study.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.study.post.application.interfaces.PostRepository;
import org.study.post.domain.Post;
import org.study.post.repository.entity.post.PostEntity;
import org.study.post.repository.jpa.JpaPostRepository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;

    @Override
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        return jpaPostRepository.save(postEntity).toPost();
    }

    @Override
    public Post findById(Long id) {
        return jpaPostRepository.findById(id)
            .orElseThrow(IllegalArgumentException::new)
            .toPost();
    }

}
