public class Bike {
    
    //Atributos
    private String state = "disponible";
    private int id = 0; 

    //constructor
    public Bike(int id, String state) {
        this.id = id;
        this.state = state;
    }

    //get
    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }


    //Metodos

    //met reserva
    //state reservada
    public boolean reservar(){

        if (state.equals("disponible")){
            state = "reservada";
            return true;
        }else{
            return false;
        }
    }
    public void desReservar(){

        if (state.equals("reservada")){
            state = "disponible";
            //return true;
        }else{
            //return false;
        }
    }
        
    public  void disponible() {
        state = "disponible";
        System.out.println("bicicleta " + id + "está disponible");
    }
    
    public  void No_disponible() {
        state = "mantenimiento";
        System.out.println("bicicleta " + id + "está en mantenimiento");
    }

    public boolean cambiarEstado(String newState){

        if(newState.equals("disponible")){
            disponible();
            return true;
        }else if(newState.equals("mantenimiento")){
            No_disponible();
            return true;
        }
        System.out.println("Estado inválido.");
        return false;
    }
    public void info (){

        System.out.println("id: " + id);
        System.out.println("estado: " + state);
    }

}
