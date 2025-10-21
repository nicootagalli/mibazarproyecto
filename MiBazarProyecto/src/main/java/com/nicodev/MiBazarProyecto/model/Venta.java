package com.nicodev.MiBazarProyecto.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long codigo_venta;
    private LocalDate fecha_venta;
    public Double total;

    @OneToMany(mappedBy = "unaVenta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  //Esta es la parte visible de la relación cuando serialices (por ejemplo al hacer un GET)
    private List<Item> listaItems;

    @ManyToOne   // N ventas pueden pertenece a 1 cliente.
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    public Cliente unCliente;

    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Item> listaItems, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaItems = listaItems;
        this.unCliente = unCliente;
    }
}
