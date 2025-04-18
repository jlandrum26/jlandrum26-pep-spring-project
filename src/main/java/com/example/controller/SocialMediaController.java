package com.example.controller;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping("/")
public class SocialMediaController {

    private final MessageService messageService;
    private final AccountService accountService;
    
    @Autowired
    SocialMediaController(MessageService messageService, AccountService accountService) {
        this.messageService = messageService;
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account) {
        //TODO: process POST request
        
        try {
            Account registeredAccount = accountService.addAccount(account);
            if (registeredAccount != null) {
                return ResponseEntity.status(HttpStatus.OK).body(registeredAccount);  
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<Account> loginUser(@RequestBody Account account) {
        //TODO: process POST request
        Account loginAccount = accountService.login(account);
        if (loginAccount != null) {
            return ResponseEntity.status(HttpStatus.OK).body(loginAccount);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        //TODO: process POST request
        Message newMessage = messageService.addMessage(message);
        if (newMessage != null) {
            return ResponseEntity.status(HttpStatus.OK).body(newMessage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable int messageId) {
        Message message = messageService.getMessage(messageId);
        if (message != null) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/account/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesFromUser(@PathVariable int accountId) {
        List<Message> messages = messageService.getAllMessagesFromUser(accountId);
        if (messages != null) {
            return ResponseEntity.status(HttpStatus.OK).body(messages);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/messages/{messageID}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int messageId) {
        Message deletedMessage = messageService.deleteMessage(messageId);
        if (deletedMessage != null) {
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PatchMapping("/messages/{messageID}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int messageId, @RequestBody String messageText) {
        Message updatedMessage = messageService.updateMessage(messageText, messageId);
        if (updatedMessage != null) {
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
