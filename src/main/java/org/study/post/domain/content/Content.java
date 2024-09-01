package org.study.post.domain.content;

import org.study.post.domain.common.DatetimeInfo;

public abstract class Content {

    private String contentText;
    private final DatetimeInfo datetimeInfo;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.datetimeInfo = new DatetimeInfo();
    }

    public void updateContentText(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.datetimeInfo.updateEditDateTime();
    }

    protected abstract void checkText(String contentText);

    public String getContentText() {
        return this.contentText;
    }

}
