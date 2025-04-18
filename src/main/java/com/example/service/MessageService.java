package com.example.service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final AccountRepository accountRepository;
    MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message addMessage(Message message) {
        if (!(message.getMessageText().isEmpty())
        && !(message.getMessageText().isBlank())
        && (message.getMessageText().length() <= 255)
        && (accountRepository.existsById(message.getPostedBy()))) {
            return messageRepository.save(message);
        }
        return null;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessage(int messageId) {
        if (messageRepository.existsById(messageId)) {
            return messageRepository.getById(messageId);
        }
        return null;
    }

    public Message deleteMessage(int messageId) {
        Message oldMessage = getMessage(messageId); //refine
        if (oldMessage != null) {
            messageRepository.deleteById(messageId);
        }
        return oldMessage;
    }

    public Message updateMessage(String messageText, int messageId) {
        return messageRepository.patchMessage(messageText, messageId); //refine
    }

    public List<Message> getAllMessasgesFromUser(int postedBy) {
        return messageRepository.findAllByPostedBy(postedBy);
    }
}
