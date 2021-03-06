package edu.utn.BancoLaguna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cbu;
    private String type; //This should be an entity, but for the sake of simplicity it's only a string
    private double balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Client client;
    //to do: list of account movements
}
