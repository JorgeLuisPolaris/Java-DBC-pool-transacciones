package org.jorge.java_jdbc.repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> { //esta interfaz define todo lo que sea consultas y operaciones de la bd
    List<T> listar() throws SQLException;
    T porId(Long id) throws SQLException;
    T guardar(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;

    void setConn(Connection conn);
}
