package cn.vikkey.security.oauth.server.user.repository;

import cn.vikkey.security.oauth.server.user.engity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
