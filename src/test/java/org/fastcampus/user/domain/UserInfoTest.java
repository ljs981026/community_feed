package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class UserInfoTest {
  @Test
  void givenNameAndProfileImage_whenCreated_thenThrowNothing() {
    // given
    String name = "test";
    String profileImage = "";
    
    // when
    // then
    assertDoesNotThrow(() -> new UserInfo(name, profileImage));
  }

  @Test
  void givenBlankNameAndProfileImage_whenCreated_thenThrowError() {
    // given
    String name = "";
    String profileImage = "";

    // when
    // then
    assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImage));
  }
}
