package edu.utn.BancoLaguna.repository;

import edu.utn.BancoLaguna.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Integer> {
    @Query(value = "select * " +
            "from transfers t " +
            "inner join accounts a " +
            "on t.account_origin = a.id " +
            "where a.id_client = ?1", nativeQuery = true)
    List<Transfer> getAllbyClientId(Integer id);
}
