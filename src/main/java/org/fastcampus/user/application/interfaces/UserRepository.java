package org.fastcampus.user.application.interfaces;

import java.util.Optional;
import org.fastcampus.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
  User save(User user);
  Optional<User> findById(Long id);
}
