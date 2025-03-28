package org.study.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContext() {
        // given
        String contentText = "this is a test content";

        // when
        PostContent content = new PostContent(contentText);

        // then
        assertEquals(contentText, content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError() {
        // given
        String content = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreanContent) {
        // given
        String content = koreanContent.repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenThrowError() {
        // given
        String content = "abcd";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenThrowError(String content) {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenNotThrowError() {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when
        String newContent = "this is an other test content";
        postContent.updateContent(newContent);

        // then
        assertEquals(newContent, postContent.getContentText());
        assertTrue(postContent.datetimeInfo.isEdited());
    }

    @Test
    void givenContentLengthIsOver_whenUpdated_thenThrowError() {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when, then
        String overLimitContent = "a".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(overLimitContent));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵"})
    void givenContentLengthIsOverAndKorean_whenUpdated_thenThrowError(String korean) {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when, then
        String overLimitContent = korean.repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(overLimitContent));
    }

    @Test
    void givenContentLengthIsUnder_whenUpdated_thenThrowError() {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when, then
        String overLimitContent = "abcd".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(overLimitContent));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenUpdated_thenThrowError(String nullOrEmptyContent) {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(nullOrEmptyContent));
    }

}