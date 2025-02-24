package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.Content;
import org.junit.jupiter.api.Test;

class CommentServiceTest extends PostApplicationTestTemplate {
  @Test
  void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment() {
    // when
    Comment comment = commentService.createComment(commentRequestDto);
    // then
    assertEquals(commentContentText, comment.getContent());
  }

  @Test
  void givenUpdateCommentRequestDto_whenUpdateComment_thenReturnUpdatedComment() {
    // given
    Comment comment = commentService.createComment(commentRequestDto);
    UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(comment.getId(), user.getId(), "updated");
    // when
    Comment updatedComment = commentService.updateComment(updateCommentRequestDto);
    // then
    assertEquals(comment.getId(), updatedComment.getId());
    assertEquals(comment.getAuthor(), updatedComment.getAuthor());
    assertEquals(comment.getContent(), updatedComment.getContent());
  }

  @Test
  void givenComment_whenLikeComment_thenIncreaseLikeCount() {
    // given
    Comment comment = commentService.createComment(commentRequestDto);
    // when
    LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
    commentService.likeComment(likeRequestDto);
    // then
    assertEquals(1, comment.getLikeCount());
  }

  @Test
  void givenComment_whenUnlikeComment_thenDecreaseLikeCount() {
    // given
    Comment comment = commentService.createComment(commentRequestDto);
    LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
    commentService.likeComment(likeRequestDto);
    // when
    commentService.unlikeComment(likeRequestDto);
    // then
    assertEquals(0, comment.getLikeCount());
  }
}
