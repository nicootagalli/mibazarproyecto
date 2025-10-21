package com.nicodev.MiBazarProyecto.service;

import com.nicodev.MiBazarProyecto.dto.MejorVentaDTO;
import com.nicodev.MiBazarProyecto.dto.MontoPorDiaDTO;
import com.nicodev.MiBazarProyecto.model.Cliente;
import com.nicodev.MiBazarProyecto.model.Item;
import com.nicodev.MiBazarProyecto.model.Producto;
import com.nicodev.MiBazarProyecto.model.Venta;
import com.nicodev.MiBazarProyecto.repository.IProductoRepository;
import com.nicodev.MiBazarProyecto.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepo;

    @Autowired
    private IProductoRepository produRepo;

    // POST de una venta.
    @Override
    public void saveVenta(Venta venta) {

        Double totalCalculado = 0.0;

        // recorremos la lista de items de la venta.
        for (Item item : venta.getListaItems()) {
            // busco el producto completo por si solo mando el ID
            Producto productoCompleto = produRepo.findById(item.getUnProducto().getCodigo_producto()).orElse(null);

            // al item le seteamos el producto completo (porque solo conocia el ID)
            item.setUnProducto(productoCompleto);
            // seteamos la referencia de vuelta a hacia la venta a la que pertenece el item.
            item.setUnaVenta(venta);
            // vamos incrementando el costo final de la venta.
            totalCalculado = totalCalculado + productoCompleto.getCosto() * item.getCantidad();
        }
        // le seteamos a la venta el monto total.
         venta.setTotal(totalCalculado);
         ventaRepo.save(venta);
    }

    // GET ALL
    @Override
    public List<Venta> getListaVentas() {
        return ventaRepo.findAll();
    }

    // EDIT VENTA
    @Override
    public Venta getVenta(Long codigo_venta) {
        return ventaRepo.findById(codigo_venta).orElse(null);
    }

    // DELETE
    @Override
    public void deleteVenta(Long codigo_venta) {
        ventaRepo.deleteById(codigo_venta);
    }

    // PUT/EDIT
    @Override
    public Venta editVenta(Long codigo_venta, Venta venta) {
    // busco la venta a editar
    Venta ventaEditar = this.getVenta(codigo_venta);

    // limpiar y volver a setear itemes.
    ventaEditar.getListaItems().clear();
    for (Item item : venta.getListaItems()) {
        item.setUnaVenta(ventaEditar);   // importante mantener la relacion bidireccional.
        ventaEditar.getListaItems().add(item);
    }

    // guardo los nuevos valores.
    this.saveVenta(ventaEditar);
    return ventaEditar;
    }


    //5. traer todos los productos de una determinada venta.
    @Override
    public List<Producto> traerListaProducto(Long codigo_producto) {
        // creo la lista que voy a devolver
        List<Producto> listaProductos = new ArrayList<>();
        // creo una instancia de venta del id recibido.
        Venta venta = this.getVenta(codigo_producto);

        for (Item item : venta.getListaItems()) {
            listaProductos.add(item.getUnProducto());
        }

        return listaProductos;
    }


    //6. Obtener la sumatoria del monto y tambien la cantidad de ventas de un determiando dia.
    @Override
    public MontoPorDiaDTO traerMontoPorDia(LocalDate fecha_venta) {
        // objeto a devolver
        MontoPorDiaDTO montoPorDiaDTO = new MontoPorDiaDTO();

        // primero traigo TODAS las ventas de la BD
        List<Venta> listaVentas = this.getListaVentas();

        Double total = 0.0;
        int cantVentas = 0;

        // recorro todas las ventas
        for (Venta venta : listaVentas) {
            if (venta.getFecha_venta().equals(fecha_venta)) { // si la fecha de la venta coincide con la fecha que me pasaron
                total = total + venta.getTotal();             // incremento total
                cantVentas = cantVentas + 1;                  // incremento cant dias
            }
        }

        // setemos los valores a mi dto
        montoPorDiaDTO.setMontoTotal(total);
        montoPorDiaDTO.setCantidadVentas(cantVentas);

        return montoPorDiaDTO;
    }

    @Override
    public MejorVentaDTO taerMejorVenta() {

        MejorVentaDTO mejorVenta = new MejorVentaDTO();
        Double max = 0.0;
        int cantProdu = 0;

        // traigo todas las ventas de la BD.
        List<Venta> listaVentas = this.getListaVentas();

        // obtengo la venta con el monto mas alto
        for (Venta venta : listaVentas) {
            if (venta.getTotal() > max) {
                max = venta.getTotal();
                // obtengo la cantidad de productos de la mejor venta
                cantProdu = 0;
                for (Item item : venta.getListaItems()) {
                    cantProdu = cantProdu + item.getCantidad();
                }
                // seteo valores a mi DTO a devolver
                mejorVenta.setCodigo_venta(venta.getCodigo_venta());
                mejorVenta.setTotal(venta.getTotal());
                mejorVenta.setCant_productos(cantProdu);
                mejorVenta.setNombre(venta.getUnCliente().getNombre());
                mejorVenta.setApellido(venta.getUnCliente().getApellido());
            }
        }
        return mejorVenta;
    }


}
