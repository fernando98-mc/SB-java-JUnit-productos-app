package com.app.productos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.productos.entity.Producto;
import com.app.productos.repository.ProductoRepositorio;

/*
 * Pruebas unitarias para las entidadas JPA
 * [inserciones a bases de datos]
 */
@DataJpaTest
public class ProductoTests {

	@Autowired
	private ProductoRepositorio productoRepositorio;

	@Test
	public void testGuardarProducto() {
		Producto producto = new Producto("Malteada", 30);
		productoRepositorio.save(producto);
	}

}
