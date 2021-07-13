package com.valkymorie.task.service;

import com.valkymorie.task.exception.ItemAlreadyExistsException;
import com.valkymorie.task.exception.ItemNotFoundException;
import com.valkymorie.task.model.Account;
import com.valkymorie.task.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private  final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository= accountRepository;
    }

    public Account saveAccount(Account account){

        return accountRepository.save(account);
    }


    public void deleteAllAccounts(){
        accountRepository.deleteAll();
    }

    public void deleteAccount(String id){
        accountRepository.deleteById(id);
    }

    public Optional<Account> findAccountbyId(String id){
        Optional<Account> accontById = accountRepository.findById(id);
        if(accontById.isPresent())
            throw  new ItemAlreadyExistsException("Account already exists with this id: "+ id);

        return accountRepository.findById(id);
    }

    public List<Account> geAccounts(String name) {
        if(name == null)
            return accountRepository.findAll();
        else
            return accountRepository.findAllByName(name);
    }



    public Account findAccount(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Account not found with this id: "+ id));
    }

    public Account addAccount(Account newAccount) {

        Optional<Account> accountByName = accountRepository.findByName(newAccount.getName());

        if(accountByName.isPresent())
            throw new ItemAlreadyExistsException("Account already exists with this name: "+ newAccount.getName());

        return accountRepository.save(newAccount);
    }
}
