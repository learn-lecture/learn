package org.study.post.application.interfaces;

import java.util.Optional;
import org.study.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);

}
