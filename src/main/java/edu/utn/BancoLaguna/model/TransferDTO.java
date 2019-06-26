package edu.utn.BancoLaguna.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {
    private String type;
    private String cbuOrigin;
    private String cbuDestination;
    private double sum;
    private String dateTime;
}
