import java.time.LocalDateTime; // Para capturar la fecha y hora exacta
import java.time.Duration;      // Para calcular la diferencia de tiempo entre dos momentos

public class Reservar {
    private Station estacionRecogida,estacionEntrega;
    private Bike bicicletaReservada;
    private long tiempoReservaMax= 20,tiempoUsoMAx=15; //tiempo en minutos
    private LocalDateTime tiempoInicioUso, tiempoFinalUso, tiempoInicioReserva, tiempoFinReserva; //toam el tiempo con hora y fecha actual
    private String estadoReserva;
    private Student estudiante;

    public Reservar( Bike bicicletaReservada, Student estudiante) {
        this.bicicletaReservada = bicicletaReservada;
        this.estudiante = estudiante;
    }

    //get

    public Station getEstacionRecogida() {
        return estacionRecogida;
    }
    public Station getEstacionEntrega() {
        return estacionEntrega;
    }
    public Bike getBicicletaReservada() {
        return bicicletaReservada;
    }
    public double getTiempoReservaMax() {
        return tiempoReservaMax;
    } 
    public double getTiempoUsoMax() {
        return tiempoUsoMAx;
    }   
    public String getEstadoReserva() { 
        return estadoReserva;
    }
    public Student getEstudiante() {
        return estudiante;
    }
    public LocalDateTime getTiempoInicioUso(){
        return tiempoInicioUso;
    }

    //set
    public boolean setEstacionEntrega(Station estacionEntrega) {
        if (estacionEntrega != null && !(estacionEntrega.alertaMaxBicicleta()) ) {
            this.estacionEntrega = estacionEntrega;
            return true;
        } else {
            System.out.println("La estación de entrega está llena. ");
            return false;
        }
    }

    public boolean setEstacionRecogida(Station estacionRecogida) {
        if (estacionRecogida != null && estacionRecogida.aquiEstaBicicleta(bicicletaReservada)) {
            this.estacionRecogida = estacionRecogida;
            return true;
        } else {
            return false;
        }
    } 

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    //metodos

    //uno tiene que ser de realizar la reserva, dondte tiene un tiempo maximo de 20 minutos maximo donde puederetirar
    //la cicla, si no se retira en ese tiempo se vueve a poner activa la cicla, y si si se retiera empiza a contar el tiempo de uso 
    //que se puede usar 15 minutos 

    public boolean realizarReserva() {
        if (bicicletaReservada.reservar()) { //este se activara si la cicla se encuentra disponible 
            estadoReserva = "reservada";
            estudiante.asignarBicicleta(bicicletaReservada);
            estudiante.setReserva(this);
            bicicletaReservada.reservar();
            estacionRecogida.retirarBicicleta(bicicletaReservada);
            System.out.println("Reserva realizada con éxito.");
            tiempoInicioReserva = LocalDateTime.now();
            return true;
        } else {
            estadoReserva = "fallida";
            System.out.println("No se pudo realizar la reserva, esta bicicleta no se encuentra disponible selecciona otra. \nSeleccione otra ");
            return false;
        }
    }
    
    public void cancelarReserva() {
        if (estadoReserva.equals("reservada")) {
            estadoReserva = "cancelada";
            estudiante.devolverBicicleta(bicicletaReservada);
            bicicletaReservada.desReservar();
            estacionRecogida.agregarBicicleta(bicicletaReservada);
            System.out.println("Reserva cancelada.");
        } else {
            System.out.println("No hay una reserva activa para cancelar.");
        }
    }



    public void activacionUso(){ //este lo activaria el administrador cuando el estudiante llegue fisicamente a retirar la reserva 
         //validacion previa de que si cuente con una reserva
        if (estadoReserva == null || !estadoReserva.equals("reservada")) {
        System.out.println("No hay ninguna reserva activa para poder activar el uso.");
        }else{
            boolean expiro = verificarExcesoReserva();
            if (!expiro) {
                // Si NO expiró, el administrador puede proceder a activar el uso de la bicicleta de forma segura
                tiempoInicioUso = LocalDateTime.now();
                tiempoFinReserva = LocalDateTime.now();
                estadoReserva = "en_uso";
                System.out.println("Uso activado con exito, para el estudiante " + estudiante.getUserName());
            } else {
                System.out.println("Acceso denegado: El estudiante tardó más de 20 minutos en llegar atomar el servicio.");
            } 
        }    
    }

    public void finalizarUso(){//este lo finalizaria el administrador
        
        estacionEntrega.agregarBicicleta(bicicletaReservada);
        tiempoFinalUso = LocalDateTime.now();
        bicicletaReservada.desReservar();
        estudiante.devolverBicicleta(bicicletaReservada);
        estadoReserva = "finalizado";
        System.out.println("Se finaliza el uso con exito, para el estudiante " + estudiante.getUserName());
        calcularPenalizacionUso();   
    }


    //ESTA OPCION SE PODRA VER ANTES DE QUE EL ADMINISTRADOR LE DE ACTIVAR A USO Y EN EL MENU DE ESTUDIANTE EN ESTADO DE RESERVA
    public boolean verificarExcesoReserva(){//va a poner la cicla de nuevo disponible si se pasa del tiempo de reserva
        //y si cuenta valida si se pasa o no del tiempo MAX de reserva (20min)
        if(tiempoFinReserva == null){ //va a validar si ya la reserva se finalizo, en el momento de activar el uso, esto es para que no se abra el tiempo de reserva en estudiante cuando ya se le activo el uso
            if((Duration.between(tiempoInicioReserva, LocalDateTime.now()).toMinutes()) > tiempoReservaMax){
            estadoReserva = "cancelada";
            estudiante.devolverBicicleta(bicicletaReservada);
            bicicletaReservada.desReservar();
            System.out.println("La reserva expiro. La cicla quedara de nuevo libre");
            return true;
        }else{
            System.out.println("La reserva aún está activa. Tiempo restante: " + (tiempoReservaMax - (Duration.between(tiempoInicioReserva, LocalDateTime.now()).toMinutes())) + " minutos.");
            return false;
        }
        }else{
            System.out.println("Usted no cuenta con ninguna reserva activa en este momento.");
            return false;
        }
        
    }

    public void calcularPenalizacionUso(){  //va a penalizar si se pasa de los 15 minutos //ESTA FUNCION SE LLAMA CUANDO EL ADMINISTRADOR LE DE TERMINAR USO
        if(estudiante.getContadorPenalizaciones() >= 2){//esta es por si cuenta con 2 o mas penalizaciones
            estudiante.setFechaFinPenalizacion(LocalDateTime.now().plusDays(30));
            estudiante.setState("bloqueado");
            System.out.println("Cuenta con mas de una penalizacion. \nSe bloqueara por 30 dias");
        }else{
            if( (Duration.between(tiempoInicioUso, tiempoFinalUso).toMinutes()) > tiempoUsoMAx){
            // Si se pasó, le metemos 15 días de castigo a partir de hoy:
            estudiante.setFechaFinPenalizacion(LocalDateTime.now().plusDays(15));
            estudiante.setState("bloqueado");
            System.out.println("SE PASO DEL TIEMPO DE USO. \nSe bloqueara por 15 dias");
            estudiante.setContadorPenalizaciones();
            }else{
                System.out.println("Felicitaciones por buen uso del sistema. \nGracias por usar el servicio");
            }
        }   
    }

}
