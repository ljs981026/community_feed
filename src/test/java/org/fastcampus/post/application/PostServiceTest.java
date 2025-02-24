package org.fastcampus.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.domain.Post;
import org.junit.jupiter.api.Test;

class PostServiceTest extends PostApplicationTestTemplate {
  @Test
  void givenCreatePostRequestDto_whenCreatePost_thenReturnPost() {
    // when
    Post savedPost = postService.createPost(postRequestDto);

    // then
    Post post = postService.getPost(savedPost.getId());
    assertEquals(savedPost, post);
  }

  @Test
  void givenCreatePost_whenUpdate_thenReturnUpdatedPost() {
    // given
    Post savedPost = postService.createPost(postRequestDto);

    // when
    Post updatedPost = postService.updatePost(savedPost.getId(), postRequestDto);

    // then
    assertEquals(savedPost.getId(), updatedPost.getId());
    assertEquals(savedPost.getAuthor(), updatedPost.getAuthor());
    assertEquals(savedPost.getContent(), updatedPost.getContent());
  }

  @Test
  void givenCreatedPost_whenLiked_thenReturnPostWithLike() {
    // given
    Post savedPost = postService.createPost(postRequestDto);

    // when
    LikeRequestDto dto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(dto);

    // then
    assertEquals(1, savedPost.getLikeCount());
  }

  @Test
  void givenCreatedPost_whenLikedTwice_thenReturnPostWithOneLike() {
    // given
    Post savedPost = postService.createPost(postRequestDto);

    // when
    LikeRequestDto dto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(dto);
    postService.likePost(dto);

    // then
    assertEquals(1, savedPost.getLikeCount());
  }

  @Test
  void givenCreatedPost_whenUnliked_thenReturnPostWithNoLike() {
    // given
    Post savedPost = postService.createPost(postRequestDto);

    // when
    LikeRequestDto dto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.likePost(dto);
    postService.unlikePost(dto);

    // then
    assertEquals(0, savedPost.getLikeCount());
  }

  @Test
  void givenCreatedPost_whenUnliked_thenReturnPostWithoutLike() {
    // given
    Post savedPost = postService.createPost(postRequestDto);

    // when
    LikeRequestDto dto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
    postService.unlikePost(dto);

    assertEquals(0, savedPost.getLikeCount());
  }
}
