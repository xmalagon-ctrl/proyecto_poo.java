public class Reservar {
    private Station estacionRecogida;
    private Station estacionEntrega;
    private Bike bicicletaReservada;
    private int tiempoReserva;
    private String estadoReserva;
    private Student estudiante;

    public Reservar(Station estacionRecogida, Station estacionEntrega, Bike bicicletaReservada, int tiempoReserva, Student estudiante) {
        this.estacionRecogida = estacionRecogida;
        this.estacionEntrega = estacionEntrega;
        this.bicicletaReservada = bicicletaReservada;
        this.tiempoReserva = tiempoReserva;
        this.estudiante = estudiante;
    }

    public Station getEstacionRecogida() {
        return estacionRecogida;
    }
    public Station getEstacionEntrega() {
        return estacionEntrega;
    }
    public Bike getBicicletaReservada() {
        return bicicletaReservada;
    }
    public int getTiempoReserva() {
        return tiempoReserva;
    }  
    public String getEstadoReserva() {
        return estadoReserva;
    }
    public Student getEstudiante() {
        return estudiante;
    }

    public void realizarReserva() {
        if (bicicletaReservada.reservar()) {
            estadoReserva = "reservada";

            estacionRecogida.devolverBicicleta(bicicletaReservada);
            estudiante.asignarBicicleta(bicicletaReservada);

            System.out.println("Reserva realizada con éxito.");
        } else {
            estadoReserva = "fallida";
            System.out.println("No se pudo realizar la reserva ");
        }
    }

    public void finalizarReserva() {
        if (estadoReserva.equals("reservada")) {
            bicicletaReservada.disponible();
            estadoReserva = "finalizada";

            estacionEntrega.asignarBicicleta(bicicletaReservada);
            estudiante.devolverBicicleta(bicicletaReservada);

            System.out.println("Reserva finalizada. Gracias por usar nuestro servicio.");
        } else {
            System.out.println("No hay una reserva activa para finalizar.");
        }
    }



    public void cancelarReserva() {
        if (estadoReserva.equals("reservada")) {
            bicicletaReservada.disponible();
            estadoReserva = "cancelada";

            estacionRecogida.asignarBicicleta(bicicletaReservada);
            eswtudiante.devolverBicicleta(bicicletaReservada);

            System.out.println("Reserva cancelada.");
        } else {
            System.out.println("No hay una reserva activa para cancelar.");
        }
    }



    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

}
