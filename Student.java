 //clase hija  de madre para estudiante

import java.time.LocalDateTime; // Para capturar la fecha y hora exacta


public class Student extends User {
    //Atributos
    private long tiun, numEmergencia;
    private String state = "activo";
    public boolean TieneReserva = false; 
    public Bike bicicletaAsignada = null;
    private LocalDateTime fechaFinPenalizacion;
    private int contadorPenalizaciones = 0;
    

    //Constructor
    public Student (String username, long cedula, long tiun){
        super(username, cedula);
        this.tiun = tiun;
    }

    //Get

    public long getTiun(){
        return tiun;
    }
    public LocalDateTime getFechaFinPenalizacion(){
        return fechaFinPenalizacion;
    }
    public String getState(){
        return state;
    }
    public int getContadorPenalizaciones(){
        return contadorPenalizaciones;
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

    public void setFechaFinPenalizacion( LocalDateTime fechaFinPenalizacion){
        this.fechaFinPenalizacion=  fechaFinPenalizacion;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setContadorPenalizaciones(){
        this.contadorPenalizaciones ++;
    }

    //METODOS

    public void asignarBicicleta(Bike bicicleta){ //se le tiene que agregar al administrador esta opcion para que puede ver que ciclas tienen reservadas por que estudiantes
        this.bicicletaAsignada = bicicleta; //cuando haya ids se les asigna == se debe completar
        this.TieneReserva = true;
    }

    public void devolverBicicleta(Bike bicicleta){
        this.bicicletaAsignada = null;
        this.TieneReserva = false;
    }  
    
}


    
