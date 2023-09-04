package ar.com.utn.Presentacion;

import ar.com.utn.datos.EstudianteDAO;
import ar.com.utn.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        var estudianteDao = new EstudianteDAO();

        while (!salir){
            try{
                mostrarMenu();
                salir = ejecutarOpciones(consola, estudianteDao);
            }catch (Exception e){
                System.out.println("Ocurrió un error al ejecutar la operacion: "+e.getMessage());
            }//fin catch
        }//while
    }//fin main

    private static void mostrarMenu(){
        System.out.println("""
                ***** Sistema de Estudiantes*****
                1. Listar Estudiantes
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elija una opcion:
                """);
    }//fin mostrarMenu

    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDAO){
        var opcion = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opcion){
            case 1 -> {
                //Listar Estudiantes
                System.out.println("Listado de Estudiantes");
                var estudiates = estudianteDAO.listar();
                estudiates.forEach(System.out::println);
            }//fin case 1
            case 2 -> {
                //Buscar Estudiante por id
                System.out.println("Introduce el id a buscar: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDAO.buscarEstudiantePorId(estudiante);
                if(encontrado)
                    System.out.println("Estudiante encontrado "+estudiante);
                else
                    System.out.println("Estudiante NO encontrado "+estudiante);
            }//fin case 2
            case 3 -> {
                //Agregar Estudiante
                System.out.println("Ingrese los datos del Estudiante: ");
                System.out.println("Ingrese el nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Ingrese el apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Ingrese el telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Ingrese el email: ");
                var email = consola.nextLine();
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var nuevoEstudiante = estudianteDAO.agregarEstudiante(estudiante);
                if(nuevoEstudiante)
                    System.out.println("Estudiante agregado: "+estudiante);
                else
                    System.out.println("No se pudo agregar al Estudiante");
            }//fin case 3
            case 4 -> {
                System.out.println("Ingrese el ID del estudiante a modificar: ");
                var id = Integer.parseInt(consola.nextLine());
                System.out.println("Ingrese el nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Ingrese el apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Ingrese el telefono: ");
                var telefono = consola.nextLine();
                System.out.println("Ingrese el email: ");
                var email = consola.nextLine();
                var estudiante = new Estudiante(id, nombre, apellido, telefono, email);
                var modifcado = estudianteDAO.modificarEstudiante(estudiante);
                if(modifcado)
                    System.out.println("Estudiante modificado: "+estudiante);
                else
                    System.out.println("Estudiante NO modificado: "+estudiante);
            }//fin case 4
            case 5 -> {
                System.out.println("Ingrese el ID del Estudiante a eliminar: ");
                var id = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(id);
                var eliminado = estudianteDAO.eliminarEstudiante(estudiante);
                if(eliminado)
                    System.out.println("Estudiante eliminado: "+estudiante);
                else
                    System.out.println("Estudiante NO eliminado: "+estudiante);
            }//fin case 5
            case 6 -> {
                System.out.println("Hasta pronto!");
                salir = true;
            }//fin case 6

        }//fin switch
        return salir;
    }//fin ejecutarPciones

}//fin class