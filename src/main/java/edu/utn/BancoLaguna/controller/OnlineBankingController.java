package edu.utn.BancoLaguna.controller;

import edu.utn.BancoLaguna.model.Client;
import edu.utn.BancoLaguna.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class OnlineBankingController {
    @Autowired
    ClientRepository clientRepository;


}
