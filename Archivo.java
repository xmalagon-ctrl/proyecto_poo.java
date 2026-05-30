import java.io.*;

public class Archivo {
    /********************************************************* */
public static void cargarBicicletas(Station estacion){

    try(BufferedReader br =
            new BufferedReader(
                    new FileReader(estacion.getArchivo()))){

        String linea;

        while((linea = br.readLine()) != null){

            String[] partes = linea.split(",");

            int id = Integer.parseInt(partes[0]);

            String estado = partes[1];

            Bike bike = new Bike(id, estado);

            //IMPORTANTE:
            //agrega SIN volver a escribir en txt
            estacion.agregarBikeMemoria(bike);
        }

    }catch(IOException e){

        System.out.println(
                "No se pudo cargar "
                + estacion.getName_station()
        );
    }
}
/************************************************************** */
    //Guardar una bicicleta
    public static void guardarBike(String nombreArchivo,
                                   Bike bike){

        try(FileWriter fw =
                new FileWriter(nombreArchivo, true);

            BufferedWriter bw =
                new BufferedWriter(fw);

            PrintWriter out =
                new PrintWriter(bw)){

            out.println(bike.getId()
                        + ","
                        + bike.getState());

        }catch(IOException e){

            e.printStackTrace();
        }
    }

    //Reescribir archivo completo
    public static void reescribirArchivo(String nombreArchivo,
                                         Bike[] bikes,
                                         int cantidad){

        try(PrintWriter writer =
                new PrintWriter(nombreArchivo)){

            for(int i = 0; i < cantidad; i++){

                if(bikes[i] != null){

                    writer.println(
                            bikes[i].getId()
                            + ","
                            + bikes[i].getState()
                    );
                }
            }

        }catch(IOException e){

            e.printStackTrace();
        }
    }
}
