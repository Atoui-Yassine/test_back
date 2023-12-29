package com.example.test_technique.repositories.userR;

import com.example.test_technique.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    @Query("select (count(u) > 0) from UserModel u where u.username = ?1")
    Boolean existsByUsername(String username);

    @Query("select (count(u) > 0) from UserModel u where u.email = ?1")
    Boolean existsByEmail(String email);
    @Query("select u from UserModel u where u.username = ?1 or u.email = ?1")
    Optional<UserModel> findByUsernameOrEmail(String username);


}
