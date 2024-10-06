package org.delivery.storeadmin.domain.sse.connection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.delivery.storeadmin.domain.sse.connection.ifs.ConnectionPoolIfs;
import org.delivery.storeadmin.domain.sse.connection.model.UserSseConnection;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SseConnectionPool implements ConnectionPoolIfs<String, UserSseConnection> {

    private static final Map<String, UserSseConnection> connections = new ConcurrentHashMap<>();

    @Override
    public void addSession(String uniqueKey, UserSseConnection userSseConnection) {
        connections.put(uniqueKey, userSseConnection);
    }

    @Override
    public UserSseConnection getConnection(String uniqueKey) {
        return connections.get(uniqueKey);
    }

    @Override
    public void onCompletionCallback(UserSseConnection session) {
        log.info("call back connection completion : {}", session);
        connections.remove(session.getUniqueKey());
    }

}
