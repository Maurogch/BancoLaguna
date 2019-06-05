package edu.utn.BancoLaguna.controller;

import edu.utn.BancoLaguna.model.Client;
import edu.utn.BancoLaguna.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.view.RedirectView;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("")
    public RedirectView authenticateUser(@RequestBody Client c){
        Client client = clientRepository.getByUsernameAndPassword(c.getUsername(),c.getPassword());

        if(isNull(client)){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Usuario o contraseña no válidos");
        }

        //TODO: Redirection to client accounts page
        return new RedirectView("/AccountView");
    }
}
