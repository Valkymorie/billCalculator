package com.valkymorie.task.controller;


import com.valkymorie.task.exception.ItemAlreadyExistsException;
import com.valkymorie.task.exception.ItemNotFoundException;
import com.valkymorie.task.model.Account;
import com.valkymorie.task.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts(@RequestParam(required = false) String name){
        return new ResponseEntity<>(accountService.geAccounts(name), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id){
        return new ResponseEntity<>(findAccount(id), OK);
    }

    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody Account newAccount){
        return new ResponseEntity<>(accountService.addAccount(newAccount), CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id){

        accountService.deleteAccount(id);
        return new ResponseEntity<>(OK);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> accountNotFoundExceptionHandler (ItemNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<String> accountAlreadyExistsExceptionHandler (ItemAlreadyExistsException e){
        return new ResponseEntity<>(e.getMessage(), CONFLICT);
    }


    private Account findAccount(String id){
        return accountService.findAccount(id);
    }

}
