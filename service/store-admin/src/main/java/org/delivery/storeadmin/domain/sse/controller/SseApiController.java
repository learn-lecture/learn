package org.delivery.storeadmin.domain.sse.controller;

import io.swagger.v3.oas.annotations.Parameter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sse")
@Slf4j
public class SseApiController {

    private static final Map<String, SseEmitter> userConnection = new ConcurrentHashMap<>();

    @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(
        @Parameter(hidden = true)
        @AuthenticationPrincipal UserSession userSession
    ) {
        log.info("login user {}", userSession);
        SseEmitter emitter = new SseEmitter(1000L * 60);
        userConnection.put(userSession.getUserId().toString(), emitter);

        emitter.onTimeout(() -> {
            log.info("on timeout");
            emitter.complete();
        });

        emitter.onCompletion(() -> {
            log.info("on completion");
            userConnection.remove(userSession.getUserId().toString());
        });

        sendInitConnectionMessage(emitter);
        return emitter;
    }

    private void sendInitConnectionMessage(SseEmitter emitter) {
        SseEventBuilder event = SseEmitter.event().name("onopen");
        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
    }

    @GetMapping("/push-event")
    public void pushEvent(
        @Parameter(hidden = true)
        @AuthenticationPrincipal UserSession userSession
    ) {
        SseEmitter emitter = userConnection.get(userSession.getUserId().toString());
        SseEventBuilder event = SseEmitter.event()
            .data("hello");

        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

    }

}
