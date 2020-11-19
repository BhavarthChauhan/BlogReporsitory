package com.adobe.interview.blog.repository;

import com.adobe.interview.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value= "Select u FROM User u where u.userName = :username")
    public List<User> getUserByUserName(@Param("username") String username);

}
