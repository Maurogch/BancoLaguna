package edu.utn.BancoLaguna.repository;

import edu.utn.BancoLaguna.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    @Query(value = "SELECT * FROM Client WHERE username = ?1 AND password = ?2", nativeQuery = true)
    Client getByUsernameAndPassword(String usernamer, String password);
}
