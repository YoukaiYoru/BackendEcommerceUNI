package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByClientUser(String usr);
    Cliente findByIdClient(Long idCliente);
    Optional<Cliente> findByClientUserAndClientEmail(String usr, String email);

    @Modifying
    @Query("delete from Cliente c where c.clientUser =:usr")
    void deleteByClientUser(String usr);

    @Modifying(clearAutomatically = true)
    @Query("update Cliente c set c.clientPassword =:newPassword where c.clientUser=:login")
    void updatePassword(String newPassword,String login);
}
