public class Reservar {
    private Station estacionRecogida;
    private Station estacionEntrega;
    private Bike bicicletaReservada;
    private int tiempoReserva;
    private String estadoReserva;
    private Student estudiante;

    public Reservar(Station estacionRecogida, Bike bicicletaReservada, int tiempoReserva, Student estudiante) {
        this.estacionRecogida = estacionRecogida;
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

    public void setEstacionEntrega(Station estacionEntrega) {
        if (estacionEntrega != null && estacionEntrega.getBicicletasAlmacenadas() != estacionEntrega.getMaxBicicletas()) {
            this.estacionEntrega = estacionEntrega;
        } else {
            System.out.println("La estación de entrega está llena. ");
            this.estacionEntrega = estacionRecogida;
        }
    }

    public void realizarReserva() {
        if (bicicletaReservada.reservar()) {
            estadoReserva = "reservada";

            estacionRecogida.retirarBicicleta(bicicletaReservada);
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

            estacionEntrega.agregarBicicleta(bicicletaReservada);
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

            estudiante.asignarBicicleta(bicicletaReservada);
            estudiante.devolverBicicleta(bicicletaReservada);

            System.out.println("Reserva cancelada.");
        } else {
            System.out.println("No hay una reserva activa para cancelar.");
        }
    }

    public void verificarBicicletaEstacion() {
        estacionRecogida.aquiEstaBicicleta(bicicletaReservada);
    }


    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

}
