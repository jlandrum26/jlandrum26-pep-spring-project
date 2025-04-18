package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("UPDATE Message SET messageText = :messageText WHERE messageId = :messageId")
    void updateMessage(@Param("messageText") String messageText, @Param("messageId") int messageId);

    @Query("FROM Message WHERE postedBy = :postedBy")
    List<Message> findAllByPostedBy(@Param("postedBy") int postedBy);

    @Query("SELECT m FROM Message m WHERE messageId = :messageId")
    Message getById(@Param("messageId") int messageId);
}
