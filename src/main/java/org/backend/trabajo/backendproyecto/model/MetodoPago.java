package org.backend.trabajo.backendproyecto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "metodo_pago")
public class MetodoPago {

    @Id
    private int id_metodo_pago;
    private String metodo_pago;

    @OneToMany(mappedBy = "metodoPago", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Orden> orden;

}
