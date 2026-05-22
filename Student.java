 //clase hija  de madre para estudiante

import java.time.LocalDateTime; // Para capturar la fecha y hora exacta
import java.time.Duration;      // Para calcular la diferencia de tiempo entre dos momentos


public class Student extends User {
    //Atributos
    private long tiun, numEmergencia;
    private String state = "activo";
    public boolean TieneReserva = false; 
    public Bike bicicletaAsignada = null;
    private LocalDateTime fechaFinPenalizacion;
    private int contadorPenalizaciones = 0;
    private Reservar reserva;
    

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
    public Reservar getReserva(){
        return reserva;
    }

        //Set
    public void setReserva(Reservar reserva) {
        this.reserva = reserva;
    }

        
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
    
    public void activacionDeUso(){
        if (this.reserva != null) {
            reserva.activacionUso();
        }/*else {
            System.out.println("Este estudiante no tiene una reserva asignada en su cuenta.");
        }*/
    }

    public void finalizacionDeUso(){
        if (this.reserva != null) {
            reserva.finalizarUso();
        }/*else {
            System.out.println("Este estudiante no tiene un uso de bicicleta activo.");
        }*/
    }

    public void estadoPenalizacion(){
        
        // VERIFICACIAR SI SE ENCUENTRA CONUNA PENALIZACION O SI NO
        if (this.getState() != null && this.getState().equals("bloqueado")) {
            LocalDateTime ahora = LocalDateTime.now();
            LocalDateTime finCastigo = this.getFechaFinPenalizacion();

            //VERIFIACAR SI LA EL BLOQUEO SIGUE ACTUALMENTE
            if (finCastigo != null && ahora.isBefore(finCastigo)) {
                Duration tiempoRestante = Duration.between(ahora, finCastigo);
                
                long dias = tiempoRestante.toDays();
                long horas = tiempoRestante.toHoursPart();
                long minutos = tiempoRestante.toMinutesPart();

                System.out.println("Cuenta regresiva de tu sanción: "
                        + dias + " días, "
                        + horas + " horas y "
                        + minutos + " minutos.");
            } else {
                // El tiempo ya paso se desbloque automaticamente
                this.setState("activo");
                this.setFechaFinPenalizacion(null);
                System.out.println("El estudiante: " + this.getUserName() + " ya se encuentra activo.");
            }
        } else {
            // Si el estado es "activo" o null, significa que está limpio
            System.out.println("El estudiante: " + this.getUserName() + " no se encuentra penalizado.");
        }
   
    }
  
}


    
