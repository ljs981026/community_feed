package org.fastcampus.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.reposiotry.entity.TimeBaseEntity;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity extends TimeBaseEntity {
  @Id
  // 아이디 값 자동 생성
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String profileImageUrl;
  private Integer followerCount;
  private Integer followingCount;

  public UserEntity(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.profileImageUrl = user.getProfileImageUrl();
    this.followerCount = user.followerCount();
    this.followingCount = user.followingCount();
  }

  public User toUser() {
    return User.builder()
        .id(id)
        .info(new UserInfo(name, profileImageUrl))
        .followerCounter(new PositiveIntegerCounter(followerCount))
        .followingCounter(new PositiveIntegerCounter(followingCount))
        .build();
  }
}
