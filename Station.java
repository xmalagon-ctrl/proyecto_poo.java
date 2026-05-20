import java.util.ArrayList;

public class Station {
    //Atributos
    private String name_station;
    private int maxBicicletas; 
    private ArrayList<Bike> bicis = new ArrayList<>();

    //Constructor
    public Station (String name_station, int maxBicicletas){
        this.name_station=name_station;
        this.maxBicicletas = maxBicicletas;
    }

    //metodos
    public boolean agregarBicicleta(Bike bicicleta){ //editar para que se pueda agregar la cicla asi supere el limite, pero genera alertas al administrador para que mueva las ciclas fisicamente y regule de nuevo 
        if (bicis.size() <= maxBicicletas){
            bicis.add(bicicleta);
            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
            System.out.println("Se agrego la cicla correctamente");
            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
            return true;
        }
        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
        System.out.println("La estación ya se encuentra en su limite");
        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
        return false;
    }

    public boolean aquiEstaBicicleta (Bike bicicleta){
        for (Bike bike : bicis) {
            if (bike.getId() == bicicleta.getId()){
            System.out.println("La bicicleta" + bicicleta.getId() + "se encuentra en esta estacion" + name_station);
            return true;
            }
        }
        System.out.println("La bicicleta" + bicicleta.getId() + "NO se encuentra en esta estación" + name_station + "\n" + "Se buscara en la siguiente estación.");
        return false;
    }
            
    public boolean retirarBicicleta(Bike bicicleta){
        for (Bike bike : bicis) {
            if (bike.getId() == bicicleta.getId()){
            bicis.remove(bicicleta);
            System.out.println("Se removio con exito la bicicleta " + bicicleta.getId());
            return true;  
            }
        }
        System.out.println("No se encuentra esta bicicleta en esta estacion " + name_station);
        return false;
    }
    public boolean alertaMaxBicicleta (){
        if (bicis.size() >= maxBicicletas){
            return true;
        }
        return false;
    }

    public void infoEstacion(){
        System.out.println("Nombre de la estación: " + name_station);
        System.out.println("Maximo de biciletas: "+ maxBicicletas);
        System.out.println("Cantidad de bicicletas: " + bicis.size());
        
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


    //get
    public String getName_station() {
        return name_station;
    }
    
    public int getMaxBicicletas() {
        return maxBicicletas;
    }

    public ArrayList<Bike> getBicis() {
        return bicis;
    }
      
