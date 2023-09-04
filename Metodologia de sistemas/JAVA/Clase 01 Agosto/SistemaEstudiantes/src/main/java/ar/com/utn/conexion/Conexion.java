package ar.com.utn.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection getConnection(){
        Connection conexion = null;
        //Variables para conectarnos a la base de datos
        var baseDatos = "estudiante";
        var url = "jdbc:mysql://localhost:3306/"+baseDatos;
        var usuario = "root";
        var password = "Admin";

        //Cargamos la clase driver de mysql en memoria
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Ocurrio un error en la conexion: "+e.getMessage());
        }//fin catch
        return conexion;
    }//fin metodo connection
}//fin class cinexion
