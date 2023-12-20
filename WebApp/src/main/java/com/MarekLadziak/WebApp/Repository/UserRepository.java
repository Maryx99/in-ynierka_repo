package com.MarekLadziak.WebApp.Repository;

import com.MarekLadziak.WebApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.firstName FROM User u WHERE u.id = :userId")
    String findFirstNameById(@Param("userId") Long userId);

}
