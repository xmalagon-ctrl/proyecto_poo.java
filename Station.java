import java.util.ArrayList;

public class Station {
    //Atributos
    private String name_station;
    private String archivo;
    private int maxBicicletas; 
    private int bicicletasAlmacenadas = 0;
    private Bike[] bicis;      //    private ArrayList<Bike> bicis = new ArrayList<>(); 

    //Constructor
    public Station (String name_station, int maxBicicletas,String archivo){
        this.name_station=name_station;
        this.maxBicicletas = maxBicicletas;
        this.archivo = archivo;
        this.bicis = new Bike[maxBicicletas];
    }

    

     //get
    public String getName_station() {
        return name_station;
    }
    
    public int getMaxBicicletas() {
        return maxBicicletas;
    }

    public Bike[] getBicis() {
        return bicis;
    }
    public int getBicicletasAlmacenadas() {
        return bicicletasAlmacenadas;
    }
    public String getArchivo() {
        return archivo;
    }


    //metodos
    //*********************************************************************
    public boolean agregarBicicleta(Bike bicicleta){

        if(bicicletasAlmacenadas < maxBicicletas){

            bicis[bicicletasAlmacenadas] = bicicleta;

            bicicletasAlmacenadas++;

            //guardar en txt
            Archivo.guardarBike(archivo, bicicleta);
        System.out.println("Se agrego la cicla correctamente");     
            return true;
        }

        return false;
    }
//**************************************************************************

        public void agregarBikeMemoria(Bike bicicleta){

    if(bicicletasAlmacenadas < maxBicicletas){

        bicis[bicicletasAlmacenadas] = bicicleta;

        bicicletasAlmacenadas++;
    }
}

/***************************************************************************************************/
    
    public String aquiEstaBicicleta(Bike bicicleta){

        for(int i = 0; i < bicicletasAlmacenadas; i++){

            if(bicis[i] != null &&  bicis[i].getId() == bicicleta.getId()){

                return "La bicicleta " + bicicleta.getId() + " se encuentra aquí.";
            }
        }

        return "La bicicleta " + bicicleta.getId() + " NO se encuentra aquí.\n" + "Busque en otra estación.";
    }
//***************************************************************************************************************************
    public boolean retirarBicicleta(Bike bicicleta){

        for(int i = 0; i < bicicletasAlmacenadas; i++){

            if(bicis[i] != null && bicis[i].getId() == bicicleta.getId()){

                //mover elementos a la izquierda
                for(int j = i; j < bicicletasAlmacenadas - 1; j++){

                    bicis[j] = bicis[j + 1];
                }

                bicis[bicicletasAlmacenadas - 1] = null;

                bicicletasAlmacenadas--;

                //actualizar txt
                Archivo.reescribirArchivo(archivo, bicis, bicicletasAlmacenadas);

                return true;
            }
        }

        return false;
    }
//***************************************************************************************************************************

    //Buscar bicicleta por ID
    public Bike buscarBike(int id){

        for(int i = 0; i < bicicletasAlmacenadas; i++){

            if(bicis[i] != null && bicis[i].getId() == id){

                return bicis[i];
            }
        }

        return null;
    }
    //**********************************************************************************************************************
    
    public boolean alertaMaxBicicleta (){
        if (bicis.size() >= maxBicicletas){
            return true;
        }
        return false;
    }

    public void infoBicicletasGeneral(){  //Muestra todas las ciclas que estan en esa estacion, ya sea que esten disponibles o en mantenimiento
        System.out.println("Nombre de la estación: " + name_station);
        for (Bike bike : bicis) {
            System.out.println("*" + bike.getId() + " Se encuentra: " + bike.getState());
        }
    }

    public void infoBicicletasDisponibles(){  //Muestra solo las ciclas que eesta disponibeles en esta estacion
        System.out.println("Nombre de la estación: " + name_station);
        bicis.stream()
             .filter(bike -> bike.getState().equals("disponible"))
             .forEach(bike -> System.out.println("*" + bike.getId() + " Se encuentra: " + bike.getState()));
    }
/************************************************************************************************************
        public void info(){

        System.out.println("Nombre de la estación: "
                            + name_station);

        System.out.println("Capacidad máxima de bicicletas: "
                            + maxBicicletas);

        System.out.println("Número de bicicletas almacenadas: "
                            + bicicletasAlmacenadas);
    }
/************************************************************************************************************
      
    public boolean cambiarEstadoBici(int id, String newState){
        for(Bike bike : bicis){
            if(bike.getId() == id){
                return bike.cambiarEstado(newState);
            }
        }
        System.out.println("No existe bicicleta con ese ID.");
        return false;
    }
    public static void alertaEstaciones(ArrayList<Station> estaciones){
        boolean first = true;
        for(Station estacion : estaciones){
        if(estacion.alertaMaxBicicleta()){
            if(first){
            System.out.println("                      (ALERTA)                      ");
            first = false;
            }
            System.out.println("La estación "+ estacion.getName_station() +" alcanzó el máximo de bicicletas.");
            System.out.println();
            }
        }
        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
    }
    public boolean existeBicicleta(int id){

        for(Bike bike : bicis){
            if(bike.getId() == id){
                return true;
            }
        }
        return false;
    }
}
