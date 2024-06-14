package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orden")
@Data
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;
    private float ordenMonto;
    private LocalDate ordenDate;
    private LocalDate dateDelivery;



    //ORDERS

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_orden_estado")
    private OrdenEstado ordenEstado;

    @OneToMany(mappedBy = "orden",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrdenDetalles> ordenDetalles;

}
