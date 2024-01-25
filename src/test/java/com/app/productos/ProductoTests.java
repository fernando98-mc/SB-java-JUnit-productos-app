package com.app.productos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.productos.entity.Producto;
import com.app.productos.repository.ProductoRepositorio;

/*
 * Pruebas unitarias para las entidadas JPA
 * [inserciones a bases de datos]
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductoTests {

	@Autowired
	private ProductoRepositorio productoRepositorio;

	@Test
	@Rollback(false)
	public void testGuardarProducto() {
		Producto producto = new Producto("Malteada", 30);
		Producto productoGuardar = productoRepositorio.save(producto);

		assertNotNull(productoGuardar);
	}

	@Test
	public void testBuscarProductoNombre() {
		String nombre = "Malteada";
		Producto buscarProducto = productoRepositorio.findByNombre(nombre);

		/*
		 * Va a comparar si el string nombre es igual al producto buscado en
		 * "findByNombre"
		 */
		assertThat(buscarProducto.getNombre()).isEqualTo(nombre);
	}

	@Test
	public void testBuscarProductoNombreNoExistente() {
		String nombre = "yohurt";
		Producto buscarProducto = productoRepositorio.findByNombre(nombre);

		/*
		 * Comprobara si un producto no existe, si existe dara ERROR
		 */
		assertNull(buscarProducto);
	}

	@Test
	@Rollback(false)
	public void testActualizarProducto() {
		String nuevoProducto = "Malteada de chocolate"; // producto actualizado
		Producto producto = new Producto(nuevoProducto, 40);
		producto.setId(1); // guarda atraves del Id

		productoRepositorio.save(producto);

		Producto actualizarProducto = productoRepositorio.findByNombre(nuevoProducto);
		assertThat(actualizarProducto.getNombre()).isEqualTo(nuevoProducto);
	}

	@Test
	public void testListarProductos() {
		List<Producto> productos = (List<Producto>) productoRepositorio.findAll();

		for (Producto producto : productos) {
			System.out.println(producto);
		}

		/*
		 * La lista se confirmara si El tamano de la lista de los productos es mayor que
		 * "0"
		 */
		assertThat(productos).size().isGreaterThan(0);
	}

}
