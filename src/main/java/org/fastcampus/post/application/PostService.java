package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.interfaces.LikeRepository;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.domain.User;

public class PostService {
  private final PostRepository postRepository;
  private final LikeRepository likeRepository;
  private final UserService userService;

  public PostService(PostRepository postRepository, LikeRepository likeRepository, UserService userService) {
    this.postRepository = postRepository;
    this.likeRepository = likeRepository;
    this.userService = userService;
  }

  public Post getPost(Long id) {
    return postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
  }

  public Post createPost(CreatePostRequestDto dto) {
    User author = userService.getUser(dto.userId());
    Post post = Post.createPost(null, author, dto.content(), dto.state());
    return postRepository.save(post);
  }

  public Post updatePost(Long id, CreatePostRequestDto dto) {
    Post post = getPost(id);
    User author = userService.getUser(dto.userId());

    post.updatePost(author, dto.content(), dto.state());

    return postRepository.save(post);
  }

  public void likePost(LikeRequestDto dto) {
    Post post = getPost(dto.targetId());
    User user = userService.getUser(dto.userId());

    if (likeRepository.checkLike(post, user)) {
      return;
    }

    post.like(user);
    likeRepository.like(post, user);
  }

  public void unlikePost(LikeRequestDto dto) {
    Post post = getPost(dto.targetId());
    User user = userService.getUser(dto.userId());

    if (likeRepository.checkLike(post, user)) {
      post.unlike();
      likeRepository.unlike(post, user);
    }
  }


}
