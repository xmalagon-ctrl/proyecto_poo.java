package poo;

import java.time.LocalDateTime;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class Comment {

    private String mensaje;
    private LocalDateTime fecha;
    private Student autor;
    private Administrator admin;
    private long tiun;

    private static final String ARCHIVO_ESTUDIANTES =
            "poo/archivoHistorial/comentarios_estudiantes.txt";

    private static final String ARCHIVO_ADMINS =
            "poo/archivoHistorial/reportes_admin.txt";

    // Comentario de estudiante
    public Comment(String mensaje, Student autor) {
        this.mensaje = mensaje;
        this.autor = autor;
        this.fecha = LocalDateTime.now();

        guardarComentarioEstudiante();
    }

    // Comentario de administrador
    public Comment(String mensaje, Administrator admin, long tiun) {
        this.mensaje = mensaje;
        this.admin = admin;
        this.tiun = tiun;
        this.fecha = LocalDateTime.now();

        guardarComentarioAdmin();
    }

    private void guardarComentarioEstudiante() {

        try (PrintWriter pw = new PrintWriter(
                new FileWriter(ARCHIVO_ESTUDIANTES, true))) {

            pw.println("Estudiante: " + autor.getUserName());
            pw.println("TIUN: " + autor.getTiun());
            pw.println("Fecha: " + fecha);
            pw.println("Comentario: " + mensaje);
            pw.println("--------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarComentarioAdmin() {

        try (PrintWriter pw = new PrintWriter(
                new FileWriter(ARCHIVO_ADMINS, true))) {

            pw.println("Administrador: " + admin.getUserName());
            pw.println("TIUN estudiante: " + tiun);
            pw.println("Fecha: " + fecha);
            pw.println("Comentario: " + mensaje);
            pw.println("--------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarReportes() {

        System.out.println("\n===== COMENTARIOS DE ESTUDIANTES =====\n");

        File archivoEstudiantes = new File(ARCHIVO_ESTUDIANTES);

       // System.out.println("Ruta buscada:");
       // System.out.println(archivoEstudiantes.getAbsolutePath());

        try (BufferedReader br = new BufferedReader(
                new FileReader(archivoEstudiantes))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {

            System.out.println(
                    "Error leyendo comentarios de estudiantes:"
            );

            e.printStackTrace();
        }

        System.out.println(
                "\n===== REPORTES DE ADMINISTRADORES =====\n"
        );

        File archivoAdmins = new File(ARCHIVO_ADMINS);

      //  System.out.println("Ruta buscada:");
       // System.out.println(archivoAdmins.getAbsolutePath());

        try (BufferedReader br = new BufferedReader(
                new FileReader(archivoAdmins))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {

            System.out.println(
                    "Error leyendo reportes de administradores:"
            );

            e.printStackTrace();
        }
    }
}
