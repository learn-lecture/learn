package org.study.message.repository;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.study.message.application.interfaces.FcmMessageRepository;
import org.study.message.repository.entity.FcmTokenEntity;
import org.study.user.domain.User;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FcmFcmMessageRepositoryImpl implements FcmMessageRepository {

    private static final String LIKE_MESSAGE_TEMPLATE = "%s님이 %s님 글에 좋아요를 눌렀습니다.";
    private static final String MESSAGE_KEY = "message";

    private final JpaFcmTokenRepository jpaFcmTokenRepository;

    @Override
    public void sendLikeMessage(User sender, User targetUser) {
        Optional<FcmTokenEntity> optional = jpaFcmTokenRepository.findById(targetUser.getId());
        if (optional.isEmpty()) {
            return;
        }

        FcmTokenEntity fcmTokenEntity = optional.get();
        Message message = Message.builder()
                .putData(MESSAGE_KEY, LIKE_MESSAGE_TEMPLATE.formatted(sender.getName(), targetUser.getName()))
                .setToken(fcmTokenEntity.getFcmToken())
                .build();

        try {
            String response = FirebaseMessaging.getInstance().sendAsync(message).get();
            log.info("FCM 메시지 전송 성공: {}", response);
        } catch (Exception e) {
            log.error("FCM 메시지 전송 실패", e);
        }
    }

}
