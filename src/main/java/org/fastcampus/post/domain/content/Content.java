package org.fastcampus.post.domain.content;

import org.fastcampus.post.domain.common.DatetimeInfo;

public abstract class Content {
  String contentText;
  final DatetimeInfo datetimeInfo;

  protected Content(String contentText) {
    checkText(contentText);
    this.contentText = contentText;
    this.datetimeInfo = new DatetimeInfo();
  }

  public void updateContent(String updateContentText) {
    checkText(updateContentText);
    this.contentText = updateContentText;
    this.datetimeInfo.updateEditDatetime();
  }

  protected abstract void checkText(String contentText);

  public String getContentText() {
    return contentText;
  }
}
