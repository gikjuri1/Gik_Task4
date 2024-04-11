package ru.gik.task4;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyDBWork extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET username = :username WHERE id = :id", nativeQuery = true)
    int updateUsersSetUsernameForIdNative(@Param("username") String username, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (username, fio) VALUES (?1, ?2)", nativeQuery = true)
    int insertUser(String username, String fio);

    List<User> findByUsername(String username);

}
