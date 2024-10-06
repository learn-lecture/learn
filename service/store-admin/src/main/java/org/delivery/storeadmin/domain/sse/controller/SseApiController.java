package org.delivery.storeadmin.domain.sse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sse")
@Slf4j
public class SseApiController {

    private final ConnectionPoolIfs<String, UserSseConnection> sseConnectionPool;
    private final ObjectMapper objectMapper;

    @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(
        @Parameter(hidden = true)
        @AuthenticationPrincipal UserSession userSession
    ) {
        log.info("login user {}", userSession);

        UserSseConnection connection = UserSseConnection.connect(
            userSession.getStoreId().toString(),
            sseConnectionPool,
            objectMapper
        );

        sseConnectionPool.addSession(connection.getUniqueKey(), connection);

        return connection.getSseEmitter();
    }

    @GetMapping("/push-event")
    public void pushEvent(
        @Parameter(hidden = true)
        @AuthenticationPrincipal UserSession userSession
    ) {
        UserSseConnection connection = sseConnectionPool.getConnection(userSession.getStoreId().toString());
        Optional.ofNullable(connection)
            .ifPresent(it -> it.sendMessage("hello world"));
    }

}
