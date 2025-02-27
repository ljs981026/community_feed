package org.fastcampus.user.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;

@Getter
@Builder
@AllArgsConstructor
public class User {
  private Long id;

  private UserInfo info;

  private PositiveIntegerCounter followingCounter;

  private PositiveIntegerCounter followerCounter;
//  private final String name;
//  private final String profileImageUrl;

  public User(Long id, UserInfo userInfo) {
    if (userInfo == null) {
      throw new IllegalArgumentException("userInfo cannot be null");
    }

    this.id = id;
    this.info = userInfo;
    this.followingCounter = new PositiveIntegerCounter();
    this.followerCounter = new PositiveIntegerCounter();
//    this.name = name;
//    this.profileImageUrl = profileImageUrl;
  }

  public void follow(User targetUser) {
    // 본인일 때 예외 처리
    // targetUser(대상)이 this(본인)과 같은지
    if (targetUser.equals(this)) {
      throw new IllegalArgumentException();
    }
    followingCounter.increase();
    targetUser.increaseFollowerCounter();
  }

  public void unfollow(User targetUser) {
    // this(본인)이 targetUser(대상)과 같은지
    if (this.equals(targetUser)) {
      throw new IllegalArgumentException();
    }
    followingCounter.decrease();
    targetUser.decreaseFollowerCounter();
  }

  private void increaseFollowerCounter() {
    followerCounter.increase();
  }

  private void decreaseFollowerCounter() {
    followerCounter.decrease();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  public int followingCount() {
    return this.followingCounter.getCount();
  }

  public int followerCount() {
    return this.followerCounter.getCount();
  }

  public String getName() {
    return info.getName();
  }

  public String getProfileImageUrl() {
    return info.getProfileImageUrl();
  }
}
