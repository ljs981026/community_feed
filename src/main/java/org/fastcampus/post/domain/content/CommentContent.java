package org.fastcampus.post.domain.content;

public class CommentContent extends Content {
  //private static final int M_LENGTH = 50;
  private static final int MAX_LENGTH = 100;

  public CommentContent(String content) {
    super(content);
  }

  @Override
  protected void checkText(String contentText) {
    if (contentText == null || contentText.isEmpty()) {
      throw new IllegalArgumentException();
    }
    if (contentText.length() > MAX_LENGTH) {
      throw new IllegalArgumentException();
    }
  }
}
