package com.nicodev.MiBazarProyecto.repository;

import com.nicodev.MiBazarProyecto.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    // 4. Obtener todos los productos cuya cantidad disponible sea menor a 5
    @Query("SELECT p FROM Producto p WHERE p.cantidad_disponible < 5")
    List<Producto> traerProductoEscaso();

}
