package edu.utn.BancoLaguna.controller;

import edu.utn.BancoLaguna.model.Account;
import edu.utn.BancoLaguna.model.Client;
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
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("")
    public void add(@RequestBody Client client){
        clientRepository.save(client);
    }

    @GetMapping("")
    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable("id") Integer id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format("No existe el cliente con el id: %s",id)));
    }

    @GetMapping("/{id}/accounts")
    public List<Account> getAccountsByClientId(@PathVariable("id") Integer id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format("No existe el cliente con el id: %s",id)));

        return client.getAccounts();
    }
}
