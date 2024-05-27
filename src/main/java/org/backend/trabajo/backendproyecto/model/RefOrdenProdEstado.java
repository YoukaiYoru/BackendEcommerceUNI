package org.backend.trabajo.backendproyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ref_orden_prod_estado")
public class RefOrdenProdEstado {
    @Id
    private int idProdEstado;
    private String ProdEstadoDescripcion;
    public RefOrdenProdEstado() {}
}
