 //clase hija  de madre para estudiante
public class Student extends User {
    //Atributos
    private long tiun, numEmergencia; //codigo del carnet
    private String state = "activo";
    public boolean TieneReserva = false; 
    public Bike bicicletaAsignada = null;

    //Constructor
    public Student (String username, long cedula, long tiun){
        super(username, cedula);
        this.tiun = tiun;
    }

    //Get

    public long getTiun(){
        return tiun;
    }

    public String getState(){
        return state;
    }

        //Set

    //verificacion de tiun
    public boolean setTiun(long tiun){
        String tiunS = String.valueOf(tiun);
        if (tiunS.length() == 10 ){  
            this.tiun = tiun;
            return true;
        }else {
            System.out.println("Tiun invalido");
            return false;   
        }
    }
    //verificacion de numuero de emergencia
    public boolean setNumEmergencia(long numEmergencia){
        String numEmergenciaS = String.valueOf(numEmergencia);
        if (numEmergenciaS.length() == 10 ){  
            this.numEmergencia = numEmergencia;
            return true;
        }else {
            System.out.println("Numero de emergencia invalido");
            return false;   
        }
    }

    //verificacion estado
    public boolean setState(String state){ //para que lo edite el administrador
        state = state.toLowerCase();
        if (state.equals("activo")){
            this.state = state;  
            return true;
        }else if (state.equals("bloqueado")){
            this.state = state;
            return true;
        }else {
            System.out.println("Estado invalido");
            return false;
        }
    }

    public void asignarBicicleta(Bike bicicleta){
        this.bicicletaAsignada = bicicleta; //cuando haya ids se les asigna == se debe completar
        this.TieneReserva = true;
    }

    public void devolverBicicleta(Bike bicicleta){
        this.bicicletaAsignada = null;
        this.TieneReserva = false;
        }
    
}


    
