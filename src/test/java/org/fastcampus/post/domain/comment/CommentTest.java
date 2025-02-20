package org.fastcampus.post.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class CommentTest {
  private final UserInfo info = new UserInfo("test", "url");
  private final User user = new User(1L, info);
  private final User otherUser = new User(2L, info);

  private final Post post = new Post(1L, user, new PostContent("content"));
  private final Comment comment = new Comment(1L, post, user, new CommentContent("comment"));

  @Test
  void givenCommentCreated_whenLike_thenLikeCountShouldBeOne() {
    // when
    comment.like(otherUser);

    // then
    assertEquals(1, comment.getLikeCount());
  }

  @Test
  void givenCommentCreated_whenUnlike_thenLikeCountShouldBeZero() {
    // given
    comment.like(otherUser);

    // when
    comment.unlike();

    // then
    assertEquals(0, comment.getLikeCount());
  }

  @Test
  void givenCommentCreated_whenLikeSelf_thenThrowError() {
    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> comment.like(user));
  }

  @Test
  void givenCommentCreated_whenUnlike_thenLikeCountShouldBeZero2() {
    // when
    comment.unlike();

    // then
    assertEquals(0, comment.getLikeCount());
  }

  @Test
  void givenComment_whenUpdateContent_thenContentShouldBeUpdated() {
    // given
    String newComment = "updated comment";

    // when
    comment.updateComment(user, newComment);

    // then
    assertEquals(newComment, comment.getContent());
  }

  @Test
  void givenComment_whenUpdateContentWithOtherUser_thenThrowError() {
    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> comment.updateComment(otherUser, "updated comment"));
  }
}
