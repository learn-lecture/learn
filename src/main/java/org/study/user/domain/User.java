package org.study.user.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.study.common.domain.PositiveIntegerCounter;

@Builder
@AllArgsConstructor
@Getter
public class User {

    private Long id;
    private UserInfo info;
    private PositiveIntegerCounter followingCount;
    private PositiveIntegerCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.info = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }

        followingCount.increment();
        targetUser.incrementFollowerCount();
    }

    public void unfollow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }

        followingCount.decrement();
        targetUser.decrementFollowerCount();
    }

    private void incrementFollowerCount() {
        this.followerCount.increment();
    }

    private void decrementFollowerCount() {
        this.followerCount.decrement();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        User user = (User) object;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int followerCount() {
        return followerCount.getCount();
    }

    public int followingCount() {
        return followingCount.getCount();
    }

    public String getName() {
        return this.info.getName();
    }

    public String getProfileImage() {
        return this.info.getProfileImageUrl();
    }

}