package org.delivery.api.domain.token.controller.business;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.token.controller.converter.TokenConverter;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.model.TokenDto;
import org.delivery.api.domain.token.service.TokenService;
import org.delivery.common.annotation.Business;
import org.delivery.common.api.ResultType;
import org.delivery.common.exception.model.BadRequestException;
import org.delivery.db.user.User;

@Business
@RequiredArgsConstructor
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    public ResultType temp = new ResultType() {
        @Override
        public int getCode() {
            return 0;
        }

        @Override
        public String getMessage() {
            return "임시";
        }
    };

    public TokenResponse issueToken(final User user) {
        return Optional.ofNullable(user)
            .map(it -> {
                final Long userId = it.getId();
                final TokenDto accessToken = tokenService.issueAccessToken(userId);
                final TokenDto refreshToken = tokenService.issueRefreshToken(userId);
                return tokenConverter.toResponse(accessToken, refreshToken);
            }).orElseThrow(() -> {
                throw new BadRequestException(temp);
            });
    }

    public Long validationToken(final String accessToken) {
        return tokenService.validationToken(accessToken);
    }

}
