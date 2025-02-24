package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRelationServiceTest {
  private final UserService userService = FakeObjectFactory.getUserService();
  private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();

  private User user1;
  private User user2;

  private FollowUserRequestDto requestDto;

  @BeforeEach
  void init() {
    CreateUserRequestDto dto1 = new CreateUserRequestDto("test1", "");
    CreateUserRequestDto dto2 = new CreateUserRequestDto("test2", "");
    this.user1 = userService.createUser(dto1);
    this.user2 = userService.createUser(dto2);

    this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
  }

  @Test
  void givenCreateTwoUsers_whenFollow_thenUserFollowSaved() {
    // when
    userRelationService.follow(requestDto);

    // then
    assertEquals(1, user1.followingCount());
    assertEquals(1, user2.followerCount());
  }

  @Test
  void givenCreateTwoUsersFollowed_whenFollow_thenUserThrowError() {
    // given
    userRelationService.follow(requestDto);

    // when, then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
  }

  @Test
  void givenCreateOneUser_whenFollowSelf_thenUserThrowError() {
    // given
    FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

    // when, then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
  }

  @Test
  void givenTwoUsersFollowed_whenUnFollow_thenUserUnFollowedSaved() {
    // given
    userRelationService.follow(requestDto);

    // when
    userRelationService.unfollow(requestDto);

    // then
    assertEquals(0, user1.followingCount());
    assertEquals(0, user2.followerCount());
  }

  @Test
  void giveCreateTwoUsers_whenUnFollow_thenUserThrowError() {
    // when, then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
  }

  @Test
  void givenCreateOneUser_whenUnFollowSelf_thenUserThrowError() {
    // given
    FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

    // when, then
    assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));
  }
}
