package org.fastcampus.post.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class PostTest {
  private final UserInfo info = new UserInfo("test", "url");
  private final User user = new User(1L, info);
  private final User otherUser = new User(2L, info);

  private final Post post = new Post(1L, user, new PostContent("content"));

  @Test
  void givenPostCreated_WhenLike_thenLikeShouldBeOne() {
    // when
    post.like(otherUser);

    // then
    assertEquals(1, post.getLikeCount());
  }

  @Test
  void givenPostCreated_whenLikeSelf_thenThrowError() {
    // when, then
    assertThrows(IllegalArgumentException.class, () -> post.like(user));
  }

  @Test
  void givenPostCreatedAndLike_whenUnlike_thenLikeShouldBeZero() {
    // given
    post.like(otherUser);

    // when
    post.unlike();

    // then
    assertEquals(0, post.getLikeCount());
  }

  @Test
  void givenPostCreated_whenUnlike_thenLikeShouldBeZero() {
    // when
    post.unlike();
    // when, then
    assertEquals(0, post.getLikeCount());
  }

  @Test
  void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated() {
    // given
    String newContent = "new content";

    // when
    post.updatePost(user, newContent, PostPublicationState.PUBLIC);

    // then
    assertEquals(newContent, post.getContent());
  }

  @Test
  void givenPostCreated_whenUpdateOtherUser_thenThrowError() {
    // when, then
    assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, "new content", PostPublicationState.PUBLIC));
  }
}
