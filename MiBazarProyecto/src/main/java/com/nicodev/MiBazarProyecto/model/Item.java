package com.nicodev.MiBazarProyecto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int item_id;

    @ManyToOne  // N items pueden pertenecer a 1 venta
    @JoinColumn(name = "venta_id")
    @JsonBackReference   //Esta parte no se serializa (evitala para que no se haga recursión infinita)
     public Venta unaVenta;

    @ManyToOne
    @JoinColumn(name = "codigo_producto")
     private Producto unProducto;

     public int cantidad;

    public Item() {
    }

    public Item(int item_id, Venta unaVenta, Producto unProducto, int cantidad) {
        this.item_id = item_id;
        this.unaVenta = unaVenta;
        this.unProducto = unProducto;
        this.cantidad = cantidad;
    }
}
