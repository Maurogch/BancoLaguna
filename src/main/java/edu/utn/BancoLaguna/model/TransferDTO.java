package edu.utn.BancoLaguna.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {
    private Integer id;
    private String destinationCbu;
    private double sum;
}
