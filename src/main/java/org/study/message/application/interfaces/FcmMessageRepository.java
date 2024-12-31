package org.study.message.application.interfaces;

import org.study.user.domain.User;

public interface FcmMessageRepository {

    void sendLikeMessage(User sender, User targetUser);

}
