package org.study.user.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.post.repository.postqueue.UserPostQueueCommandRepository;
import org.study.user.application.interfaces.UserRelationRepository;
import org.study.user.domain.User;
import org.study.user.repository.entity.UserEntity;
import org.study.user.repository.entity.UserRelationEntity;
import org.study.user.repository.entity.UserRelationId;
import org.study.user.repository.jpa.JpaUserRelationRepository;
import org.study.user.repository.jpa.JpaUserRepository;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository commandRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationId id = new UserRelationId(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        commandRepository.saveFollowPost(user.getId(), targetUser.getId());
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationId id = new UserRelationId(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        commandRepository.deleteUnFollowPost(user.getId(), targetUser.getId());
    }

}
