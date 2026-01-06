package com.nicodev.MiBazarProyecto.controller;

import com.nicodev.MiBazarProyecto.model.Producto;
import com.nicodev.MiBazarProyecto.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService produService;

    // POST un producto
    @PostMapping("productos/crear")
    public String saveProducto(@RequestBody Producto producto) {
              produService.saveProducto(producto);
       return "Producto creado correctamente";
    }

    // GET lista de productos
    @GetMapping("productos")
    public List<Producto> getListaProductos() {
        return produService.getListaProductos();
    }

    // GET un producto en particular (por ID)
    @GetMapping("productos/{codigo_producto}")
    public Producto getProducto(@PathVariable Long codigo_producto){
        return produService.getProducto(codigo_producto);
    }

    // DELETE un producto (por ID)
    @DeleteMapping("productos/eliminar/{codigo_producto}")
    public String deleteProducto(@PathVariable Long codigo_producto) {
        produService.deleteProducto(codigo_producto);
        return "Producto eliminado correctamente";
    }

    // PUT/EDIT un producto (por ID)
    @PutMapping("productos/editar/{codigo_producto}")
    public Producto editProducto(@PathVariable Long codigo_producto,
                                 @RequestParam(required = false, name = "nombre") String nombre_nue,
                                 @RequestParam(required = false, name = "marca") String marca_nue,
                                 @RequestParam(required = false, name = "costo") Double costo_nue,
                                 @RequestParam(required = false, name = "cantidad_disponible") Integer cant_dis_nue) { // Integer = Wrapper, acepta Null.

        // Envio el codigo original (para buscar) y envio nuevos datos para modificar.
        produService.editProducto(codigo_producto, nombre_nue, marca_nue, costo_nue, cant_dis_nue);

        // busco el producto ya editado para mostrarlo en la response.
        Producto producto = produService.getProducto(codigo_producto);

        // retorno el producto ya editado.
        return producto;

    }

    // 4. Obtener todos los productos cuya cantidad disponible sea menor a 5
    @GetMapping("productos/falta_stock")
    public List<Producto> taerEscasos(){
        return produService.traerProductoEscaso();
    }




}
