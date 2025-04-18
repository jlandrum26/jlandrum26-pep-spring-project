package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("FROM Account WHERE username = :username AND password = :password")
    Account verifyAccount(@Param("username") String username, @Param("password") String password);

    @Query("FROM Account WHERE username = :username")
    Account findByUsername(@Param("username") String username);
}
