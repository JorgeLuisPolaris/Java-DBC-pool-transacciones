package org.jorge.java_jdbc;

import org.jorge.java_jdbc.modelo.Categoria;
import org.jorge.java_jdbc.modelo.Producto;
import org.jorge.java_jdbc.servicio.CatalogoService;
import org.jorge.java_jdbc.servicio.Service;
import java.sql.*;
import java.util.Date;

public class EjemploJDBC {
    public static void main(String[] args) throws SQLException {
            Service service = new CatalogoService();
            System.out.println("=================Listar elementos===================");
            service.listar().forEach(System.out::println);

            System.out.println("=================Insertar Nueva Categoría===================");
            Categoria categoria = new Categoria();
            categoria.setNombre("Perecibles");

            System.out.println("=================Insertar nuevo producto===================");
            Producto producto = new Producto();
            producto.setNombre("Leche");
            producto.setPrecio(20);
            producto.setFechaRegistro(new Date());
            producto.setSku("dsacaewqea");
            service.guardarProductoConCategoria(producto,categoria);
            System.out.println("Producto guardado con éxito : " +producto.getId());
            service.listar();

         }
}

