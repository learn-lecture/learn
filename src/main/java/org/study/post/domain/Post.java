package org.study.post.domain;

import org.study.common.domain.PositiveIntegerCounter;
import org.study.post.domain.content.PostContent;
import org.study.post.domain.content.PostPublicationState;
import org.study.user.domain.User;

public class Post {

    private final Long id;
    private final User author;
    private final PostContent content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public Post(Long id, User author, PostContent content) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        likeCount.increment();
    }

    public void unlike(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        likeCount.decrement();
    }

    public void updatePost(User user, String content, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.state = state;
        this.content.updateContentText(content);
    }

}
