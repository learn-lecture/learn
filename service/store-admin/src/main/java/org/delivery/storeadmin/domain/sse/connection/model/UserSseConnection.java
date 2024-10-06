package org.delivery.storeadmin.domain.sse.connection.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

@ToString
@Getter
@EqualsAndHashCode
@Slf4j
public class UserSseConnection {

    private final ConnectionPoolIfs<String, UserSseConnection> connectionPool;
    private final String uniqueKey;
    private final SseEmitter sseEmitter;
    private final ObjectMapper objectMapper;

    private UserSseConnection(
        String uniqueKey,
        ConnectionPoolIfs<String, UserSseConnection> connectionPool,
        ObjectMapper objectMapper
    ) {
        this.uniqueKey = uniqueKey;
        this.sseEmitter = new SseEmitter(1000L * 60);
        this.connectionPool = connectionPool;
        this.objectMapper = objectMapper;

        this.sseEmitter.onCompletion(() -> {
            log.info("completion = {}", this);
            connectionPool.onCompletionCallback(this);
        });

        this.sseEmitter.onTimeout(() -> {
            log.info("timeout = {}", this);
            this.sseEmitter.complete();
        });

        sendMessage("onopen", "connect");
    }

    public static UserSseConnection connect(
        String uniqueKey,
        ConnectionPoolIfs<String, UserSseConnection> connectionPool,
        ObjectMapper objectMapper
    ) {
        return new UserSseConnection(uniqueKey, connectionPool, objectMapper);
    }

    public void sendMessage(String eventName, Object data) {
        try {
            String body = this.objectMapper.writeValueAsString(data);
            SseEventBuilder event = SseEmitter.event()
                .name(eventName)
                .data(body);
            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

    public void sendMessage(Object data) {
        try {
            String body = this.objectMapper.writeValueAsString(data);
            SseEventBuilder event = SseEmitter.event()
                .data(body);
            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

}
