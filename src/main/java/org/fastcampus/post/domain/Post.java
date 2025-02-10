package org.fastcampus.post.domain;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;

public class Post {
  private final Long id;
  private final User author;
//  private final Long authorId;
  private final PostContent content;

  public Post(Long id, User author, PostContent content) {
    if (author == null) {
      throw new IllegalArgumentException();
    }

    this.id = id;
    this.author = author;
//    this.authorId = author.getId();
    this.content = content;

  }
}
