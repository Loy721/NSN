package com.example.nsn.repo;

import com.example.nsn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUid(String uid);

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name=?1 AND u.surname=?2 ORDER BY u.surname, u.name")
    List<User> findAllByNameAndSurname(String name, String surname);
}
