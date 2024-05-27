package org.backend.trabajo.backendproyecto.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ref_prod_tipo")
public class RefProdTipo {
    @Id
    private int idProdTipo;
    private String prodTipoDescripcion;
    public RefProdTipo() {}
}
