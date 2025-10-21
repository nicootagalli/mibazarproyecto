package com.nicodev.MiBazarProyecto.service;

import com.nicodev.MiBazarProyecto.dto.MejorVentaDTO;
import com.nicodev.MiBazarProyecto.dto.MontoPorDiaDTO;
import com.nicodev.MiBazarProyecto.model.Cliente;
import com.nicodev.MiBazarProyecto.model.Item;
import com.nicodev.MiBazarProyecto.model.Producto;
import com.nicodev.MiBazarProyecto.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    // POST una venta.
    public void saveVenta(Venta venta);

    // GET Lista completa de ventas.
    public List<Venta> getListaVentas();

    // GET una venta (por codigo_venta).
    public Venta getVenta (Long codigo_venta);

    // DELETE una venta (por codigo_venta)
    public void deleteVenta(Long codigo_venta);

    // PUT/EDIT una venta (por codigo_venta)
    public Venta editVenta(Long codigo_venta, Venta venta);

    //5. traer todos los productos de una determinada venta.
    public List<Producto> traerListaProducto(Long codigo_producto);

    //6. Obtener la sumatoria del monto y tambien la cantidad de ventas de un determiando dia.
    public MontoPorDiaDTO traerMontoPorDia(LocalDate fecha_venta);

    // 7. Obtener mejor venta
    public MejorVentaDTO taerMejorVenta();
}
