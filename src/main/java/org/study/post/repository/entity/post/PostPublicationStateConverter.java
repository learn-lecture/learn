package org.study.post.repository.entity.post;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.study.post.domain.content.PostPublicationState;

@Converter
public class PostPublicationStateConverter implements AttributeConverter<PostPublicationState, String> {

    @Override
    public String convertToDatabaseColumn(PostPublicationState state) {
        return state.name();
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String dbData) {
        return PostPublicationState.valueOf(dbData);
    }

}
