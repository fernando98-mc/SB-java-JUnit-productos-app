package com.app.productos.repository;

import org.springframework.data.repository.CrudRepository;
import com.app.productos.entity.Producto;

public interface ProductoRepositorio extends CrudRepository<Producto, Integer> {

}
