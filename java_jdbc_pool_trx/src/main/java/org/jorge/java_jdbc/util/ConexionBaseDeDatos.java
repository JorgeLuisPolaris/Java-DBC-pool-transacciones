package org.jorge.java_jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDeDatos { // utilizando el patrón singleton crearemos una conexión única a la bd
    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=UTC ";
    private static String username = "root";
    private static String pass= "sasa";

    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {
        if (pool==null){
            pool  = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(username);
            pool.setPassword(pass);
            pool.setInitialSize(3);
            pool.setMinIdle(3);//mínimo de conexiones inactivas
            pool.setMaxIdle(10);
            pool.setMaxTotal(8);
            return pool;
        }
        return pool;
    }

    public static Connection getConection() throws SQLException {
        return getInstance().getConnection();
    }

}
