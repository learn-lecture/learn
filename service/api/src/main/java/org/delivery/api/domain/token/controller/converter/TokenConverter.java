package org.delivery.api.domain.token.controller.converter;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.exception.TokenExceptionType;
import org.delivery.api.domain.token.model.TokenDto;
import org.delivery.common.exception.model.BadRequestException;

@RequiredArgsConstructor
@Converter
public class TokenConverter {

    public TokenResponse toResponse(final TokenDto accessToken, final TokenDto refreshToken) {
        Objects.requireNonNull(accessToken, () -> {
            throw new BadRequestException(TokenExceptionType.REFRESH_TOKEN_NULL_EXCEPTION);
        });
        Objects.requireNonNull(refreshToken, () -> {
            throw new BadRequestException(TokenExceptionType.REFRESH_TOKEN_NULL_EXCEPTION);
        });

        return TokenResponse.of(accessToken, refreshToken);
    }

}
