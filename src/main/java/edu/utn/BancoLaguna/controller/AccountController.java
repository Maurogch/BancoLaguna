package edu.utn.BancoLaguna.controller;

import edu.utn.BancoLaguna.model.Account;
import edu.utn.BancoLaguna.model.Client;
import edu.utn.BancoLaguna.model.Transfer;
import edu.utn.BancoLaguna.model.TransferDTO;
import edu.utn.BancoLaguna.repository.AccountRepository;
import edu.utn.BancoLaguna.repository.ClientRepository;
import edu.utn.BancoLaguna.repository.TransferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    @Qualifier("ModelMapperTransfer")
    private ModelMapper modelMapper;

    @PostMapping("")
    public void add(@RequestBody Account account){
        accountRepository.save(account);
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<?> transferToAccount(@PathVariable("id") Integer id, @RequestBody TransferDTO transferDTO){
        try {
            Transfer transfer = convertToEntity(transferDTO);
            Account origin = accountRepository.findById(transferDTO.getId())
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format("No existe la cuenta con el id: %s",id)));
            Account destination = accountRepository.getByCbu(transferDTO.getDestinationCbu());

            if(!origin.getType().equals(destination.getType())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transferencias solo entre cuentas del mismo tipo");
            }

            if(origin.getBalance() < transfer.getSum()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente");
            }

            if(isNull(destination)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CBU de destino no existe");
            }

            transfer.setDestination(destination);
            transfer.setOrigin(origin);
            transfer.setId(null);
            destination.setBalance(destination.getBalance() + transfer.getSum());
            origin.setBalance(origin.getBalance() - transfer.getSum());

            accountRepository.save(origin);
            accountRepository.save(destination);
            transferRepository.save(transfer);

            return ResponseEntity.ok().body("Transferencia Exitosa");
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Parse Error");
        }

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

    private Transfer convertToEntity(TransferDTO transferDTO) throws ParseException {
        Transfer transfer = modelMapper.map(transferDTO, Transfer.class);

        transfer.setId(transferDTO.getId());
        transfer.setDateTime(LocalDateTime.now());
        return transfer;
    }

}
