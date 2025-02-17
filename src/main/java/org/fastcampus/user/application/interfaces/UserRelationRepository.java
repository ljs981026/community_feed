package org.fastcampus.user.application.interfaces;

import org.fastcampus.user.domain.User;

public interface UserRelationRepository {
  boolean isAlreadyFollow(User user, User targetUser);
  // 팔로우 여부 저장
  void save(User user, User targetUser);
  // 언팔로우 삭제
  void delete(User user, User targetUser);
}
