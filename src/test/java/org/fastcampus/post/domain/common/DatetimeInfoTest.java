package org.fastcampus.post.domain.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DatetimeInfoTest {
  @Test
  void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() throws InterruptedException {
    // given
    DatetimeInfo dateTimeInfo = new DatetimeInfo();
    LocalDateTime localDateTime = dateTimeInfo.getDatetime();

    // when
    Thread.sleep(1);
    dateTimeInfo.updateEditDatetime();

    // then
    assertTrue(dateTimeInfo.isEdited());
    assertNotEquals(localDateTime, dateTimeInfo.getDatetime());
  }
}
