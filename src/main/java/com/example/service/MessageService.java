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

    public Message deleteMessage(int messageId) {
        Message oldMessage = getMessage(messageId);
        if (oldMessage != null) {
            messageRepository.deleteById(messageId);
            if (!(messageRepository.existsById(messageId))) {
                return oldMessage;
            }
        }
        return null;
    }

    public Message updateMessage(String messageText, int messageId) {
        if (!(messageText.isEmpty())
        && !(messageText.isBlank())
        && (messageText.length() <= 255)
        && messageRepository.existsById(messageId)) {
            messageRepository.updateMessage(messageText, messageId);
            return getMessage(messageId);
        }
        return null;
    }

    public List<Message> getAllMessagesFromUser(int postedBy) {
        return messageRepository.findAllByPostedBy(postedBy);
    }
}
