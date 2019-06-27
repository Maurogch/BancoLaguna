package edu.utn.BancoLaguna.repository;

import edu.utn.BancoLaguna.model.Account;
import edu.utn.BancoLaguna.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    @Query(value = "select * " +
            "from accounts " +
            "where cbu = ?1", nativeQuery = true)
    Account getByCbu(String cbu);
}
