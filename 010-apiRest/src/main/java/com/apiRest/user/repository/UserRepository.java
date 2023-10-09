package com.apiRest.user.repository;

import com.apiRest.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //queryMethod
    //TODO revisar este queryMethod, ya que en el entity de usuario se identifica con userName
    Optional<User> findByUserName(String userName);
}
