package ar.com.utn.datos;

import ar.com.utn.dominio.Estudiante;
import static ar.com.utn.conexion.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    //Metodo listar
    public List<Estudiante> listar(){
        List<Estudiante> estudiantes = new ArrayList<>();
        //Creamos objetos para comunicarnos con la db
        PreparedStatement ps; //Evia la sentencia a la db
        ResultSet rs; //Obtenemos el resultado de la db
        //Creamos un objeto de tipo conexion
        Connection con = getConnection();
        String sql = "SELECT * FROM estudiantes2022 ORDER BY idestudiantes2022 ";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("idestudiantes2022"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("mail"));
                //Falta agregar a la lista
                estudiantes.add(estudiante);
            }
        }catch(Exception e){
            System.out.println("Ocurrio un error al selecionar datos: "+e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Ocurrio un error al cerrar la conexion: "+e.getMessage());
            }
        }//fin finally
        return estudiantes;
    }//fin listar


    // Metodo por id -> find by id
    public boolean buscarEstudiantePorId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM estudiantes2022 WHERE idestudiantes2022=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("mail"));
                return true; //Se encontro un registro
            }//fin if
        }catch(Exception e){
            System.out.println("Ocurrio un error al buscar estudiante: "+e.getMessage());
        }//fin catch
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion: "+e.getMessage());
            }//fin try
        }//fin finally
        return false;
    }//fin buscarEstudiantePorId

    //Metodo agregar un nuevo estudiante
    public boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con =getConnection();
        String sql = "INSERT INTO estudiantes2022 (nombre, apellido, telefono, mail) VALUES (?, ?, ? ,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.execute();
            return true;
        }catch(Exception e) {
            System.out.println("Ocurrio un error al agregar el estudiante: " + e.getMessage());
        }// fin try catch
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion: "+e.getMessage());
            }//fin try catch
        }//fin finally
        return false;
    }//fin agregarEstudiante

    //Metodo para modificar estudiante
    public boolean modificarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "UPDATE estudiantes2022 SET nombre=?, apellido=?, telefono=?, mail=? WHERE idestudiantes2022=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
        }catch(Exception e){
            System.out.println("Ocurrio un error al modificar un estudiante: "+e.getMessage());
        }//fin try catch
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Ocurrio un error al cerrar la conexion");
            }//fin try catch
        }//fin finally
        return false;
    }//fin modificarEstudiante

    public boolean eliminarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "DELETE FROM estudiantes2022 WHERE idestudiantes2022=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, estudiante.getIdEstudiante());
            ps.execute();
            return  true;
        } catch (Exception e){
            System.out.println("Error al eliminar estudiante: " +e.getMessage());
        }//fin try catch
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexcion: "+ e.getMessage());
            }//fin try catch
        }//fin finally
        return false;
    }//fin eliminarEstudiante

    public static void main(String[] args) {

        var estudianteDao = new EstudianteDAO();

        //Eliminar estudiante
        var estudianteEliminado = new Estudiante(3);
        var eliminado = estudianteDao.eliminarEstudiante(estudianteEliminado);
        if(eliminado)
            System.out.println("Estudiante eliminado: "+estudianteEliminado);
        else
            System.out.println("No se elimino estudiante");

        //Modificar estudiante
        //var estudianteModificado = new Estudiante(1,"Juan Carlos", "Juarez", "5556566", "jcjuarez@utn.edu");
        //var modificado = estudianteDao.modificarEstudiante(estudianteModificado);
        //if(modificado)
        //    System.out.println("Estudiante modificado: "+estudianteModificado);
        //else
        //   System.out.println("No se a modificado el estudiante: "+estudianteModificado);
/*
        //Buscar por id
        var estudiante1 = new Estudiante(1);
        System.out.println("Estudiantes antes de la busqueda: " + estudiante1);
        var encontrado = estudianteDao.buscarEstudiantePorId(estudiante1);
        if (encontrado)
            System.out.println("Estudiante encontrado: " + estudiante1);
        else
            System.out.println("No se encontro el estudiante: " + estudiante1.getIdEstudiante());
 */
        /*
        //Agregar estudiante
        var nuevoEstudiante = new Estudiante("Carlos", "Lara", "5558974", "clara@utn.edu");
        var agregado = estudianteDao.agregarEstudiante(nuevoEstudiante);
        if(agregado)
            System.out.println("Estudiante agregado: "+nuevoEstudiante);
        else
            System.out.println("No se ha agregado estudiante: "+nuevoEstudiante);

         */
        //Listar estudiantes
        System.out.println("Listado de estudiantes: ");
        List<Estudiante> estudiantes = estudianteDao.listar();
        estudiantes.forEach(System.out::println);//Funcion lambda para imprimir

    }//fin main
}//fin EstudianteDAO
