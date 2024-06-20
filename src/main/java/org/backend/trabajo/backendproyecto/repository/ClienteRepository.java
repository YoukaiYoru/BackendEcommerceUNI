package org.backend.trabajo.backendproyecto.repository;

import org.backend.trabajo.backendproyecto.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByClientUser(String usr);
    List<Cliente> findByIdClient(Long idCliente);
    List<Cliente> findByClientUserAndClientEmail(String usr, String email);

    void deleteByClientUser(String usr);

    @Modifying(clearAutomatically = true)
    @Query("update Cliente c set c.clientPassword =:newPassword where c.clientUser=:login")
    void updatePassword(String newPassword, String login);

    List<Cliente> findByClientUserOrClientEmail(String clientUser, String clientEmail);
}
