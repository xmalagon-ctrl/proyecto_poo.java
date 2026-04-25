public class Bike {
    
        public int getId() {
        return id;
    }

        public String getState() {
        return state;
    }


    //Atributos
    private String state = "disponible";
    private int id = 0; 

    //cosjtructor
        public Bike(int id, String state) {
            this.id = id;
            this.state = "disponible";
        }

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


    
//state disponible
    public  void disponible() {

        state = "disponible";
        System.out.println("bicicleta " + id + "está disponible");

    }
//no
    public  void No_disponible() {

        state = "mantenimiento";
        System.out.println("bicicleta " + id + "está en mantenimiento");

    }


    // informacion de la bicicleta
    public void info (){

        System.out.println("id: " + id);
        System.out.println("estado: " + state);
    }


}

