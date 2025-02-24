package org.fastcampus.post.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;

public class PostApplicationTestTemplate {
  final PostService postService = FakeObjectFactory.getPostService();
  final CommentService commentService = FakeObjectFactory.getCommentService();
  final UserService userService = FakeObjectFactory.getUserService();

  final User user = userService.createUser(new CreateUserRequestDto("test1", null));
  final User otherUser = userService.createUser(new CreateUserRequestDto("test2", null));

  final CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);
  final Post post = postService.createPost(postRequestDto);

  final String commentContentText = "this is test comment";

  final CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), "this is test comment");
  final Comment comment = commentService.createComment(commentRequestDto);


}
