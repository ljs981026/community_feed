package org.fastcampus.user.domain;

import java.util.Objects;

public class User {
  private final Long id;
  private final UserInfo info;
  private final UserRelationCounter followingCounter;
  private final UserRelationCounter followerCounter;
//  private final String name;
//  private final String profileImageUrl;

  public User(Long id, UserInfo userInfo) {
    this.id = id;
    this.info = userInfo;
    this.followingCounter = new UserRelationCounter();
    this.followerCounter = new UserRelationCounter();
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

  public Long getId() {
    return id;
  }
}
