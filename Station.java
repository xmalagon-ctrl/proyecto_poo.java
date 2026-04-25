public class Station {
    //Atributos
    private String name_station;
    private int maxBicicletas;
    private int bicicletasAlmacenadas=0;
    private Bike [] bicis;

    //Constructor
    public Station (String name_station, int maxBicicletas){
        this.name_station=name_station;
        this.maxBicicletas=maxBicicletas;
        this.bicis=new Bike [maxBicicletas];
    }

    public boolean agregarBicicleta(Bike bicicleta){
        if (bicicletasAlmacenadas<=maxBicicletas){
            bicis[bicicletasAlmacenadas]=bicicleta;
            bicicletasAlmacenadas++;
            return true;
        }
        return false;
    }

    public String aquiEstaBicicleta (Bike bicicleta){
        for (int i=0;i<bicicletasAlmacenadas;i++) {
            if (bicis[i].getId()==bicicleta.getId()){
                return "La bicicleta" + bicicleta.getId() + "se encuentra aquí.";
            }
        }
        return "La bicicleta" + bicicleta.getId() + "NO se encuentra aquí." + "\n" + "Busque en otra estación.";
    }

    public boolean retirarBicicleta(Bike bicicleta){
        for (int i=0;i<bicicletasAlmacenadas;i++) {
            if (bicis[i].getId()==bicicleta.getId()){
                bicis[i]=null;
                bicicletasAlmacenadas--;
                return true;
            }
        }
        return false;
    }

    public String getName_station() {
        return name_station;
    }
    
    public int getMaxBicicletas() {
        return maxBicicletas;
    }

    public int getBicicletasAlmacenadas() {
        return bicicletasAlmacenadas;
    }

    public Bike[] getBicis() {
        return bicis;
    }
    
    public void info(){
        System.out.println("Nombre de la estación: " + name_station);
        System.out.println("Capacidad máxima de bicicletas: " + maxBicicletas);
        System.out.println("Número de bicicletas almacenadas: " + bicicletasAlmacenadas);
    }
    
}
