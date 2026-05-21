import java.time.LocalDateTime;

public class Comment{

    //Atributos

    private String mensaje;
    private LocalDateTime fecha;
    private Student autor;

    //Constructor
            public Comment(String mensaje, Student autor) {
            this.mensaje = mensaje;
            this.autor = autor;
            //Se asigna la fecha de manera automática
            this.fecha = LocalDateTime.now();
        }

    //Get
    public String getMensaje(){
        return mensaje;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
        public Student getAutor() {
        return autor;
    }

    //Set
    public void setMensaje(){
        this.mensaje = mensaje;
    }

    //Métodos
    public void verComentario() {

    System.out.println("Autor: " + autor.getUserName());
    System.out.println("TIUN: " + autor.getTiun());
    System.out.println("Fecha: " + fecha);
    System.out.println(mensaje);
    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
    }


}
