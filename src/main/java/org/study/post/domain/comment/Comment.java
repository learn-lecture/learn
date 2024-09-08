package org.study.post.domain.comment;

import org.study.common.domain.PositiveIntegerCounter;
import org.study.post.domain.Post;
import org.study.post.domain.content.CommentContent;
import org.study.post.domain.content.Content;
import org.study.user.domain.User;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public static Comment createComment(Long id, Post post, User user, String content) {
        return new Comment(id, post, user, new CommentContent(content));
    }

    protected Comment(Long id, Post post, User author, Content content) {
        if (author == null) {
            throw new IllegalArgumentException();
        }
        if (post == null) {
            throw new IllegalArgumentException();
        }
        if (content == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
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

    public void updateComment(User user, String content) {
        if (!this.author.equals(user)) {
            throw new IllegalArgumentException();
        }

        this.content.updateContent(content);
    }

    public int getLikeCount() {
        return this.likeCount.getCount();
    }

    public String getContent() {
        return this.content.getContentText();
    }

    public Long getId() {
        return this.id;
    }

    public Post getPost() {
        return this.post;
    }

    public User getAuthor() {
        return this.author;
    }

}
