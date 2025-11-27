package com.nicodev.MiBazarProyecto.controller;

import com.nicodev.MiBazarProyecto.dto.MejorVentaDTO;
import com.nicodev.MiBazarProyecto.dto.MontoPorDiaDTO;
import com.nicodev.MiBazarProyecto.model.Cliente;
import com.nicodev.MiBazarProyecto.model.Item;
import com.nicodev.MiBazarProyecto.model.Producto;
import com.nicodev.MiBazarProyecto.model.Venta;
import com.nicodev.MiBazarProyecto.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaController {

    @Autowired
    private IVentaService ventaServ;

    // POST de una venta.
    @PostMapping("ventas/crear")
    public String saveVenta(@RequestBody Venta venta) {
        ventaServ.saveVenta(venta);
        return "Venta cargada correctamente";
    }

    // GET lista completa de ventas
    @GetMapping("ventas")
    public List<Venta> getListaVentas() {
        return ventaServ.getListaVentas();
    }

    // GET una venta por ID
    @GetMapping("ventas/{codigo_venta}")
    public Venta getVenta (@PathVariable Long codigo_venta) {
        return ventaServ.getVenta(codigo_venta);
    }

    // DELETE de una venta por ID
    @DeleteMapping("ventas/eliminar/{codigo_venta}")
    public String deleteVenta(@PathVariable Long codigo_venta) {
        ventaServ.deleteVenta(codigo_venta);
        return "Venta eliminada correctamente";
    }

    // PUT / EDIT
    @PutMapping("ventas/editar/{codigo_venta}")
    public Venta editVenta(@PathVariable Long codigo_venta,
                           @RequestBody Venta venta) {
        return ventaServ.editVenta(codigo_venta,venta);
    }
   //5. traer todos los productos de una determinada venta.
    @GetMapping("ventas/productos/{codigo_venta}")
    public List<Producto> traerListaProductos (@PathVariable Long codigo_venta){
        return ventaServ.traerListaProducto(codigo_venta);
    }

    //6. Obtener la sumatoria del monto y tambien la cantidad de ventas de un determiando dia.
    @GetMapping("ventas/fecha/{fecha_venta}")
    public MontoPorDiaDTO traerMontoPorDia(@PathVariable LocalDate fecha_venta) {
        return ventaServ.traerMontoPorDia(fecha_venta);
    }

    //7. Obtener codigo_venta, total, cant_productos, nombre y apellido del cliente de la venta con el mayor monto.
    @GetMapping("ventas/mejorVenta")
    public MejorVentaDTO traerMejorVenta(){
        return ventaServ.taerMejorVenta();
    }

}
