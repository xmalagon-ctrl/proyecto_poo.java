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

                BufferedReader openHistorial = new BufferedReader(new FileReader(archivo));

                String lectura;

                dupli = false;

                while ((lectura = openHistorial.readLine()) != null) {

                                // ignorar líneas vacías
                        if (lectura.trim().isEmpty()) {
                            continue;
                        }
                    String[] partes = lectura.split(":");

                    int identificacion = Integer.parseInt(partes[0]);

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
            }
        }
}
