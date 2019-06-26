package edu.utn.BancoLaguna.controller;

import edu.utn.BancoLaguna.model.Account;
import edu.utn.BancoLaguna.model.Client;
import edu.utn.BancoLaguna.repository.AccountRepository;
import edu.utn.BancoLaguna.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("")
    public void add(@RequestBody Account account){
        accountRepository.save(account);
    }

    @GetMapping("")
    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable("id") Integer id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format("No existe la cuenta con el id: %s",id)));
    }


}
