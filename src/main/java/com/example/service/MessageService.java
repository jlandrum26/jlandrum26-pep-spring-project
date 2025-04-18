package com.example.service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

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

    public int deleteMessage(int messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return 1;
        }
        return 0;
    }

    public int updateMessage(Message message, int messageId) {
        if (!(message.getMessageText().isEmpty())
        && !(message.getMessageText().isBlank())
        && (message.getMessageText().length() <= 255)
        && messageRepository.existsById(messageId)) {
            message.setMessageId(messageId);
            messageRepository.save(message);
            return 1;
        }
        return 0;
    }

    public List<Message> getAllMessagesFromUser(int postedBy) {
        return messageRepository.findAllByPostedBy(postedBy);
    }
}
