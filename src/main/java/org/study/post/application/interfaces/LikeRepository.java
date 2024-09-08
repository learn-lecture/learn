package org.study.post.application.interfaces;

import org.study.post.domain.Post;
import org.study.user.domain.User;

public interface LikeRepository {

    boolean checkLike(Post post, User user);
    void like(Post post, User user);
    void unlike(Post post, User user);

}
