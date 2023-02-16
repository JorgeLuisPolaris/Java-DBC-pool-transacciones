package org.jorge.java_jdbc.servicio;

import org.jorge.java_jdbc.modelo.Categoria;
import org.jorge.java_jdbc.modelo.Producto;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<Producto> listar() throws SQLException;
    Producto porId(Long id) throws SQLException;
    Producto guardar(Producto producto) throws SQLException;
    void eliminar(Long id) throws SQLException;

    List<Categoria> listarCategoria() throws SQLException;

    Categoria porIdCategoria(Long id) throws SQLException;
    Categoria guardarCategoria(Categoria categoria) throws SQLException;

    void eliminarCategoria(Long id) throws SQLException;
     void guardarProductoConCategoria(Producto producto, Categoria categoria) throws SQLException;

}
