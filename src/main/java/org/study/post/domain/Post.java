package org.study.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.study.common.domain.PositiveIntegerCounter;
import org.study.post.domain.content.Content;
import org.study.post.domain.content.PostContent;
import org.study.post.domain.content.PostPublicationState;
import org.study.user.domain.User;

@Getter
@Builder
@AllArgsConstructor
public class Post {

    private final Long id;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;

    public static Post createPost(Long id, User author, String content, PostPublicationState state) {
        return new Post(id, author, new PostContent(content), state);
    }

    public static Post createDefalutPost(Long id, User author, String content) {
        return new Post(id, author, new PostContent(content));
    }

    protected Post(Long id, User author, Content content) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    protected Post(Long id, User author, Content content, PostPublicationState state) {
        if (author == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
    }

    public void like(User user) {
        if (this.author.equals(user)) {
            throw new IllegalArgumentException();
        }
        likeCount.increment();
    }

    public void unlike() {
        likeCount.decrement();
    }

    public void updatePost(User user, String content, PostPublicationState state) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.state = state;
        this.content.updateContent(content);
    }

    public int getLikeCount() {
        return this.likeCount.getCount();
    }

    public String getContent() {
        return this.content.getContentText();
    }

}
