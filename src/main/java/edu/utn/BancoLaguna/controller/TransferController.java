package edu.utn.BancoLaguna.controller;

import edu.utn.BancoLaguna.model.Transfer;
import edu.utn.BancoLaguna.model.TransferDTO;
import edu.utn.BancoLaguna.repository.TransferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    @Qualifier("ModelMapperTransfer")
    private ModelMapper modelMapper;



    @GetMapping("/{id}")
    public List<TransferDTO> getAllbyId(@PathVariable("id") Integer id){
        List<Transfer> transfers = transferRepository.findAll();

        return transfers.stream()
                .map(transfer -> convertToDto(transfer))
                .collect(Collectors.toList());
    }

    private TransferDTO convertToDto(Transfer transfer){
        return modelMapper.map(transfer, TransferDTO.class);
    }
}
