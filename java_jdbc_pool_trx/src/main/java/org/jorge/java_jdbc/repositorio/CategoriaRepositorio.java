package org.jorge.java_jdbc.repositorio;

import org.jorge.java_jdbc.modelo.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorio implements Repositorio<Categoria>{

    private Connection conn;

    public CategoriaRepositorio() {
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public CategoriaRepositorio(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias")){// se usa statement si no se tienen variables
            while (rs.next()){
                categorias.add(crearCategoria(rs));
            }
        }
        return categorias;
    }



    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = new Categoria();
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias WHERE id = ?")) {// se usa prepareStatement porque se tienen variables
            stmt.setLong(1,id);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    categoria = crearCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public Categoria guardar(Categoria categoria) throws SQLException {
        String sql;
        if (categoria.getId()!=null && categoria.getId()>0){
            sql = "UPDATE categorias SET nombre=? WHERE id=?";
        }else {
            sql = "INSERT INTO categorias(nombre) VALUES(?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { //esto nos habilita el id recien ingresado
            stmt.setString(1, categoria.getNombre());
            if (categoria.getId() != null && categoria.getId() > 0) {
                stmt.setLong(2, categoria.getId());
            }
            stmt.executeUpdate();
            if (categoria.getId()==null){
                try(ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()){
                        categoria.setId(rs.getLong(1)); //conseguimos el id del cursor generado por el statement.return_generated_keys
                        // y lo usamos para darle el id a la categoría creada en caso de que no sea un uptadte
                    }
                }
            }
        }
        return categoria;
    }

    @Override
    public void eliminar(Long id) throws SQLException {
            try(PreparedStatement statement = conn.prepareStatement("DELETE FROM categorias WHERE id=?")){
                statement.setLong(1,id);
                statement.executeUpdate();
            }
    }
    private static Categoria crearCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong(1));
        categoria.setNombre(rs.getString(2));
        return categoria;
    }
}
