package com.nicodev.MiBazarProyecto.service;

import com.nicodev.MiBazarProyecto.model.Producto;

import java.util.List;

public interface IProductoService {

    // POST un producto
    public void saveProducto(Producto producto);

    // GET lista de productos
    public List<Producto> getListaProductos();

    // GET un producto en particular (por ID)
    public Producto getProducto(Long codigo_producto);

    // DELETE un producto (por ID)
    public void deleteProducto(Long codigo_producto);

    // PUT/EDIT un producto (por ID)
    public void editProducto(Long codigo_producto, String nombre_nue, String marca_nue, Double costo_nue, Integer cant_dis_nue);

    // 4. Obtener todos los productos cuya cantidad disponible sea menor a 5
    public List<Producto> traerProductoEscaso();

}
