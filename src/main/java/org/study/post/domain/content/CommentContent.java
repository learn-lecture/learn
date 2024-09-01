package org.study.post.domain.content;

public class CommentContent extends Content {

    private static final int NAX_COMMENT_LENGTH = 100;

    protected CommentContent(String contentText) {
        super(contentText);
    }

    @Override
    protected void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (contentText.length() > NAX_COMMENT_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}
