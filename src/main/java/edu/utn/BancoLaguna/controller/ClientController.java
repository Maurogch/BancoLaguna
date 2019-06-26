package edu.utn.BancoLaguna.controller;

import edu.utn.BancoLaguna.model.Account;
import edu.utn.BancoLaguna.model.Client;
import edu.utn.BancoLaguna.model.Transfer;
import edu.utn.BancoLaguna.model.TransferDTO;
import edu.utn.BancoLaguna.repository.ClientRepository;
import edu.utn.BancoLaguna.repository.TransferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    @Qualifier("ModelMapperTransfer")
    private ModelMapper modelMapper;

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

    @GetMapping("/{id}/transfers")
    public List<TransferDTO> getAllbyId(@PathVariable("id") Integer id){
        List<Transfer> transfers = transferRepository.findAll();

        return transfers.stream()
                .map(transfer -> convertToDto(transfer))
                .collect(Collectors.toList());
    }

    private TransferDTO convertToDto(Transfer transfer){
        TransferDTO transferDTO = modelMapper.map(transfer, TransferDTO.class);            //Date-Time conversion should not be done without moddel mapper
        //transferDTO.setDateTime(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT) //With fomat style, ex: 8/23/16 1:12 PM
          //      .format(transfer.getDateTime()));
        transferDTO.setDateTime(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")         //With pattern
              .format(transfer.getDateTime()));
        return transferDTO;
    }
}
