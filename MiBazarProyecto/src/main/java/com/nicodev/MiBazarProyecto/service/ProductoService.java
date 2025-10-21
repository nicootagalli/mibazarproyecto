package com.nicodev.MiBazarProyecto.service;

import com.nicodev.MiBazarProyecto.model.Producto;
import com.nicodev.MiBazarProyecto.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository produRepo;

    // POST un producto
    @Override
    public void saveProducto(Producto producto) {
        produRepo.save(producto);
    }

    // GET lista de productos
    @Override
    public List<Producto> getListaProductos() {
        return produRepo.findAll();
    }

    // GET un producto en particular (por ID)
    @Override
    public Producto getProducto(Long codigo_producto) {
        return produRepo.findById(codigo_producto).orElse(null);
    }

    // DELETE un producto (por ID)
    @Override
    public void deleteProducto(Long codigo_producto) {
        produRepo.deleteById(codigo_producto);
    }

    // PUT/EDIT un producto (por ID)
    @Override
    public void editProducto(Long codigo_producto, String nombre_nue, String marca_nue, Double costo_nue, Double cant_dis_nue) {
        // busco el objeto original
        Producto producto = this.getProducto(codigo_producto);

        // modifico sus nuevos valores requeridos.
        if (nombre_nue != null) producto.setNombre(nombre_nue);
        if (marca_nue != null) producto.setMarca(marca_nue);
        if (costo_nue != null) producto.setCosto(costo_nue);
        if (cant_dis_nue != null) producto.setCantidad_disponible(cant_dis_nue);

        // guado nuevos valores
        this.saveProducto(producto);
    }

    // 4. Obtener todos los productos cuya cantidad disponible sea menor a 5
    @Override
    public List<Producto> traerProductoEscaso() {
        return produRepo.traerProductoEscaso();
    }

}
