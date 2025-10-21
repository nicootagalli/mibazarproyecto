package com.nicodev.MiBazarProyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MejorVentaDTO {

    private Long codigo_venta;
    private Double total;
    private int cant_productos;
    private String nombre;
    private String apellido;


}
