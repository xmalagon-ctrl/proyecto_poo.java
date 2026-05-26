
import java.io.*;


public class DocReader {
       String nombre_st;
        public static boolean dupli= false;
       int tiun;
        //constructor



       public static void crearArchivo(String nombreArchivo) {

    File archivo = new File(nombreArchivo);

    try {

        if (archivo.createNewFile()) {
            System.out.println("Archivo creado");
        } else {
            System.out.println("El archivo ya existe");
        }

    } catch(IOException ex) {
        ex.printStackTrace(System.out);
    }
}

        public static void contenidoArchivo(String nombreArchivo, String nombre_st,  int tiun) {

        File archivo = new File(nombreArchivo);

        try {
           if(dupli!=true){
            PrintWriter salida = new PrintWriter(new FileWriter(archivo , true));
           // PrintWriter salida2 = new PrintWriter(new FileWriter(tiun , true));
            salida.print(tiun);
            salida.println(":"+nombre_st);
              
             
             
              
              
            salida.close();
          

            System.out.println("Se actualizo el historial");
           }
        } catch(FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch(IOException ex){
            ex.printStackTrace(System.out);
        }
    }

        public static void leerArchivo(String nombreArchivo) {

        File archivo = new File(nombreArchivo);

        try {
            BufferedReader openHistorial = new BufferedReader(new FileReader(archivo));
            String lectura= openHistorial.readLine();
                while (lectura != null) {
                    System.out.println(lectura);
                    lectura = openHistorial.readLine();
                }

         openHistorial.close();
        } catch(FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch(IOException ex){
            ex.printStackTrace(System.out);
        }
    }

                public static void verificarDuplicados(String nombreArchivo, int tiun) {

                    File archivo = new File(nombreArchivo);

                    try {

                        BufferedReader openHistorial =
                                new BufferedReader(new FileReader(archivo));

                        String lectura;

                        dupli = false;

                        while ((lectura = openHistorial.readLine()) != null) {

                            // Ignorar líneas vacías
                            if (lectura.trim().isEmpty()) {
                                continue;
                            }

                            // Separar datos por coma
                            String[] partes = lectura.split(",");

                            // Verificar formato correcto
                            if(partes.length < 3){
                                continue;
                            }

                            // nombre, cedula, clave
                            int identificacion = Integer.parseInt(partes[1]);

                            if (tiun == identificacion) {

                                System.out.println("Ya existe el user");

                                dupli = true;

                                break;
                            }
                        }

                        openHistorial.close();

                    } catch(FileNotFoundException ex) {

                        ex.printStackTrace(System.out);

                    } catch(IOException ex) {

                        ex.printStackTrace(System.out);

                    } catch(NumberFormatException ex){

                        System.out.println("Error en formato del archivo.");
                    }
                }

public static void guardarAdministrador(
        String nombreArchivo,
        String nombre,
        int cedula,
        int clave) {

    File archivo = new File(nombreArchivo);

    try {

        // Verifica máximo 5 administradores
        BufferedReader br = new BufferedReader(new FileReader(archivo));

        int contador = 0;

        while(br.readLine() != null){
            contador++;
        }

        br.close();

        if(contador >= 5){
            System.out.println("Ya existen 5 administradores.");
            return;
        }

        PrintWriter pw = new PrintWriter(new FileWriter(archivo, true));

        pw.println(nombre + "," + cedula + "," + clave);

        pw.close();

        System.out.println("Administrador guardado.");

    } catch(IOException ex){
        ex.printStackTrace(System.out);
    }
}

public static boolean cambiarClaveAdministrador(
        String nombreArchivo,
        String nombreBuscar,
        int cedulaBuscar,
        int claveActual,
        int nuevaClave) {

    File archivo = new File(nombreArchivo);
    File temporal = new File("temp.txt");

    boolean cambioRealizado = false;

    try {

        BufferedReader br =
                new BufferedReader(new FileReader(archivo));

        PrintWriter pw =
                new PrintWriter(new FileWriter(temporal));

        String linea;

        while((linea = br.readLine()) != null){

            if(linea.trim().isEmpty()){
                continue;
            }

            String[] datos = linea.split(",");

            if(datos.length < 3){
                pw.println(linea);
                continue;
            }

            String nombre = datos[0].trim();

            int cedula = Integer.parseInt(datos[1].trim());

            int claveGuardada =
                    Integer.parseInt(datos[2].trim());

            // Verifica admin correcto
            if(nombre.equalsIgnoreCase(nombreBuscar.trim())
            && cedula == cedulaBuscar
            && claveGuardada == claveActual){

                // 🔥 ESCRIBE NUEVA CLAVE
                pw.println(
                        nombre + "," +
                        cedula + "," +
                        nuevaClave
                );

                cambioRealizado = true;

            }else{

                // Mantiene la línea original
                pw.println(linea);
            }
        }

        br.close();
        pw.close();

        // REEMPLAZAR ARCHIVO ORIGINAL
        if(archivo.delete()){

            temporal.renameTo(archivo);

        }else{

            System.out.println("No se pudo actualizar el archivo.");
        }

    } catch(Exception e){

        e.printStackTrace(System.out);
    }

    return cambioRealizado;
}   
        public static boolean verificarClaveAdministrador(
        String nombreArchivo,
        String nombreBuscar,
        int cedulaBuscar,
        int claveAdmi) {

    File archivo = new File(nombreArchivo);

    try {

        BufferedReader br =
                new BufferedReader(new FileReader(archivo));

        String linea;

        while((linea = br.readLine()) != null){

            // Ignorar líneas vacías
            if(linea.trim().isEmpty()){
                continue;
            }

            String[] datos = linea.split(",");

            // Verificar formato correcto
            if(datos.length < 3){
                continue;
            }

            String nombre = datos[0];

            int cedula = Integer.parseInt(datos[1]);

            int claveGuardada = Integer.parseInt(datos[2]);

            // Comparar datos
            if(nombre.trim().equalsIgnoreCase(nombreBuscar.trim())
            && cedula == cedulaBuscar
            && claveGuardada == claveAdmi){

                br.close();

                return true;
            }
        }

        br.close();

    } catch(IOException ex){

        ex.printStackTrace(System.out);

    } catch(NumberFormatException ex){

        System.out.println("Error en formato del archivo.");
    }

    return false;
}
}
