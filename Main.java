import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String nombre_st = " ", opcionMenu = " ", nombre_admi, estado;
        int tiun = 0, num_estacion, id, admi_usu, menu,cedulaEstudiante, cedulaAdmi;
        boolean verif_u , verif_tiun, verif_CC_TI, estadoCorrecto ;
        Student estudiante, est_admi;
        ArrayList<Student> listaEstudiante = new ArrayList<>();
        ArrayList<Administrator> listaAdministrador = new ArrayList<>();
        ArrayList<Comment> listaComentarios = new ArrayList<>();
        var estaciones = new ArrayList<Station>(); //lista de estaciones
        //Estación 1: Calle 53
        estaciones.add(new Station("Calle 53",15));
        //Estación 2: CYT
        estaciones.add(new Station("CYT", 50));
        //Estación 3: Uriel
        estaciones.add(new Station("Uriel", 15));   
        //Estación 4: Calle 45
        estaciones.add(new Station("Calle 45", 15));
        //Estación 5: Calle 26  
        estaciones.add(new Station("Calle 26", 30));
        //Estación 6: Calle 30
        estaciones.add(new Station("Calle 30", 40));
        // Agregar los datos fijos de administrador
        var adminsAutorizados = new ArrayList<Administrator>(); //lista de administradores fijos
        adminsAutorizados.add(new Administrator("santiago gonzalez", 1032443188));
        adminsAutorizados.add(new Administrator("laura valderrama", 1052841022));
        adminsAutorizados.add(new Administrator("sammuel cortes", 1013602884));
        adminsAutorizados.add(new Administrator("valery torres", 1141326715));
        adminsAutorizados.add(new Administrator("xiomara malagon", 1021666771));
        int posicionAdministrador= -1;


        do {
            admi_usu = verifExcepcion(sc, "Si es administrativo ingrese 1 y si es estudiante ingrese 2: ");
            sc.nextLine();
            if(admi_usu == 1){

                //codigo par administradores fijos 
                verif_u = true;
                //Nombre del administrador
                System.out.print("Ingresa el nombre del administrador(primer nombre y primer apellido): ");
                nombre_admi = sc.nextLine().toLowerCase();
                

                //Numero C.C 
                cedulaAdmi = verifExcepcion(sc, "Ingresa su C.C: ");

                for (int i = 0; i < adminsAutorizados.size(); i++){
                    if(adminsAutorizados.get(i).getUserName().equals(nombre_admi) && adminsAutorizados.get(i).getCedula() == cedulaAdmi){
                        System.out.println("Bienvenido a su cuenta " +  adminsAutorizados.get(i).getUserName());
                        verif_u = true;
                        posicionAdministrador = i;
                        break;
                    }else {
                        verif_u = false;
                    }
                    
                }
                if (verif_u==false){
                    System.out.println("No eres administrador autorizado");
                    opcionMenu = "no";
                }else{
                    do{
                        //Menu principal
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Menu principal: ");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("1. Penalizar estudiante");
                        System.out.println("2. Agregar cicla");
                        System.out.println("3. Reglas");
                        System.out.println("4. Ver reportes");
                        System.out.println("5. Quitar a acceso a estudiante");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        menu = verifExcepcion(sc, "Ingrese el numero de lo que desea hacer: ");

                        if (menu == 1){
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            System.out.println("Penalizar estudiante");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            sc.nextLine();
                            do{
                                if(listaEstudiante.isEmpty()){
                                    System.out.println("No hay estudiantes registrados.");
                                    estadoCorrecto = true;
                                }else{
                                    System.out.print("Ingrese tiun que desea buscar: ");
                                    tiun = sc.nextInt();
                                    sc.nextLine();
                                    
                                    estadoCorrecto = adminsAutorizados.get(posicionAdministrador).penalizeStudent(listaEstudiante,tiun);
                            }
                            }while(!estadoCorrecto);


                        }else if ( menu == 2){
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            System.out.println("Agregar cicla");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                                                   System.out.println("Estaciones: ");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");                         
                        System.out.println("1. Calle 53");
                         System.out.println("2. CYT");
                         System.out.println("3. Uriel");
                         System.out.println("4. Calle 45");
                         System.out.println("5. Calle 26");
                         System.out.println("6. Calle 30");
                         System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.print("Ingrese la estación deseada: ");
                        num_estacion = sc.nextInt();
                        System.out.print("Ingrese el ID de la bicicleta: ");
                        id = sc.nextInt();
                        //Se crea una nueva bicicleta

                        Bike newBike = new Bike (id, "disponible");

                        //Se verifica la capacidad de la estacion y se agrega la bicicleta

                        boolean fueAgregada = estaciones.get(num_estacion-1).agregarBicicleta(newBike);
                        
                        if(fueAgregada){
                               System.out.println("La bicicleta fue agregada correctamente.");
                        }else{
                               System.out.println("La estación está llena.");
                        }

                            System.out.println();

                        }else if (menu == 3){
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            System.out.println("Reglas");
                            adminsAutorizados.get(posicionAdministrador).seeRules();
                            sc.nextLine();
        
                        }else if ( menu == 4){
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            System.out.println("Ver reportes");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                         int option = 0;
                            do{
                            System.out.println("1. Estado de las bicicletas");
                            System.out.println("2. Reportes o comentarios de los estudiantes");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            option = verifExcepcion(sc,"Ingrese la opción a la cual desea acceder: ");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            sc.nextLine();
                            switch(option){

                                case 1:{
                                    //Estado de las bicicletas
                                    System.out.println("Estaciones: ");
                                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                                    System.out.println("1. Calle 53");
                                    System.out.println("2. CYT");
                                    System.out.println("3. Uriel");
                                    System.out.println("4. Calle 45");
                                    System.out.println("5. Calle 26");
                                    System.out.println("6. Calle 30");
                                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");

                                    num_estacion = verifExcepcion(sc,"Ingrese la estación deseada: ");
                                    sc.nextLine();
                                    // Se llama al método que imprime la información de las biciletas 
                                    estaciones.get(num_estacion - 1).infoBicicletasGeneral();
                                    System.out.println("¿Desea cambiar el estado de una bicicleta? si/no");
                                    opcionMenu = sc.nextLine().toLowerCase();

                             if(opcionMenu.equals("si")){
                                 System.out.print("Ingrese el ID de la bicicleta: ");
                                 id = sc.nextInt();
                                 sc.nextLine();
                                 System.out.print("Escriba el estado (disponible/mantenimiento): ");
                                 String stateBike = sc.nextLine().toLowerCase();
                                 boolean estadoActualizado = estaciones.get(num_estacion - 1).cambiarEstadoBici(id, stateBike);
                                 if(estadoActualizado){
                                     System.out.println("Estado actualizado.");
                                 }else{
                                     System.out.println("No fue posible actualizar el estado.");
                                 }
                             }else if(!opcionMenu.equals("no")){
                                 mensajeError();
                             }
                                    break;
                                }

                                case 2:{
                                    //Reportes o comentarios de los estudiantes
                                    if(listaComentarios.isEmpty()){
                                        System.out.println("No hay comentarios registrados.");
                                    }else{
                                    //Imprime los comentarios 
                                        for(Comment comentario : listaComentarios){
                                        comentario.verComentario();
                                        }
                                    }
                                    break;
                                }
                                default:{
                                    mensajeError();
                                }

                            }

                        }while(option==0 || option<1 || option>2);

                        System.out.println();

                        }else if (menu == 5){
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            System.out.println("Quitar a acceso a estudiante");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            tiun = verifExcepcion(sc, "Ingrese el Tiun del estudiante que desea remover");
                            if(adminsAutorizados.get(posicionAdministrador).removeStudent(listaEstudiante, tiun)){
                                System.out.println("El estudiante fue removido exitosamente");
                            }else{
                                System.out.println("El TIUN no pertenece a ningún estudiante registrado.");
                            }
                            System.out.println();

                    
                        }else{
                            mensajeError();
                        }
                        do{
                            System.out.print("Desea volver al menu principal? (si/no); ");
                            opcionMenu = sc.nextLine().toLowerCase();
                            if (opcionMenu.equals("no")){
                                System.out.println("Gracias por usar el aplicativo.");
                            }else{
                                mensajeError();
                            }
                        }while(!opcionMenu.equals("no") && !opcionMenu.equals("si"));

                    }while (menu > 5 || menu < 0 || opcionMenu.equals("si"));

                }
    
                            System.out.println();

                        }else if (menu == 5){
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            System.out.println("Quitar a acceso a estudiante");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            
                            tiun = verifExcepcion(sc, "Ingrese el Tiun del estudiante que desea remover");
                            if(adminsAutorizados.get(posicionAdministrador).removeStudent(listaEstudiante, tiun)){
                                System.out.println("El estudiante fue removido exitosamente");
                            }else{
                                System.out.println("El TIUN no pertenece a ningún estudiante registrado.");
                            }
                            System.out.println();

                        }else{
                            mensajeError();
                        }
                        do{
                            System.out.print("Desea volver al menu principal? (si/no); ");
                            opcionMenu = sc.nextLine().toLowerCase();
                            if (opcionMenu.equals("no")){
                                System.out.println("Gracias por usar el aplicativo.");
                            }else{
                                mensajeError();
                            }
                        }while(!opcionMenu.equals("no") && !opcionMenu.equals("si"));

                    }while (menu > 5 || menu < 0 || opcionMenu.equals("si"));

                }
 

            }else if (admi_usu == 2){
                do{
                //Nombre del estudiante
                System.out.print("Ingresa el nombre del estudiante: ");
                nombre_st= sc.nextLine();

                //Numero C.C o T.I
                cedulaEstudiante = verifExcepcion(sc, "Ingresa su C.C o T.I: ");

                //Numero tiun
                tiun = verifExcepcion(sc, "Ingresa el Tiun de su carnet: ");
                
                //Clase estudiante
                estudiante = new Student(nombre_st,cedulaEstudiante, tiun);
                sc.nextLine();
                verif_u = estudiante.setUserName(nombre_st);
                verif_CC_TI = estudiante.setCedula(cedulaEstudiante);
                verif_tiun = estudiante.setTiun(tiun);
    
                }while(!verif_u || !verif_tiun || !verif_CC_TI);

                //agregar estudiante a la lista de estudiantes
                listaEstudiante.add(estudiante);
                //metodos del historial ***********************************************************************************
                        DocReader.crearArchivo("poo/archivoHistorial/estudianteReal.txt");
                        DocReader.verificarDuplicados("poo/archivoHistorial/estudianteReal.txt", tiun);
                        DocReader.contenidoArchivo("poo/archivoHistorial/estudianteReal.txt", nombre_st, tiun);
                        DocReader.leerArchivo("poo/archivoHistorial/estudianteReal.txt");
                //*-********************************************************************************************************
                do{
                    //Menu principal
                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                    System.out.println("Menu principal: ");
                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                    System.out.println("1. Reservacion de cicla");
                    System.out.println("2. Estado de la cuenta");
                    System.out.println("3. Reglas");
                    System.out.println("3. Tiempo de reservacion");
                    System.out.println("5. Reporte o comentario sobre el servicio");
                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                    menu = verifExcepcion(sc, "Ingrese el numero de lo que desea hacer: ");

                    if (menu == 1){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Reservacion de cicla");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                      //  System.out.println(" ");
                        System.out.println("Estaciones: ");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("1. Calle 53");
                        System.out.println("2. CYT");
                        System.out.println("3. Uriel");
                        System.out.println("4. Calle 45");
                        System.out.println("5. Calle 26");
                        System.out.println("6. Calle 30");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                                                //bucle para error
                        num_estacion = verifExcepcion(sc, "Ingresa el numero de la estacion que deseas usar (1 a 6): "); //hacer validador de rango de estaciones dentro de la clase

                        //Se guarda la estación elegida
                        Station estacionRecogida = estaciones.get(num_estacion - 1);

                        //Verifica la capacidad de la estación
                        if(estacionRecogida.getBicicletasAlmacenadas() > 0){
                              //muestra el id de bicicletas disponibles en esa estacion(y tal vez al frente muestre de una ves su estado)
                            for(int i = 0; i < estacionRecogida.getBicicletasAlmacenadas(); i++){
                                estacionRecogida.getBicis()[i].info();
                            }

                        //ingresa id 
                        id = verifExcepcion(sc, "Ingresa el id de la cicla que deseas usar: ");
                        //inicializa una bicicleta
                        Bike bicicleta = null;
                        //Compara el id ingresado con los que se encuentran en la lista.
                        for(int i = 0; i < estacionRecogida.getBicicletasAlmacenadas(); i++){
                            //Si encuentra uno igual, asigna la bicicleta
                            if(estacionRecogida.getBicis()[i].getId() == id){
                                 bicicleta = estacionRecogida.getBicis()[i];
                                }
                        }
                        
                        if(bicicleta != null){
                            //Clase reserva
                             Reservar reserva = new Reservar( bicicleta, 20, estudiante);
                            //Verifica si la estacion seleccionada 
                             boolean recogidaCorrecta = reserva.setEstacionRecogida(estacionRecogida);
                             
                             if(recogidaCorrecta){
                                int entrega = verifExcepcion(sc, "Ingrese estación de entrega: ");
                                Station estacionEntrega = estaciones.get(entrega - 1);
                                boolean entregaCorrecta = reserva.setEstacionEntrega(estacionEntrega);
                                 if(entregaCorrecta){
                                    reserva.realizarReserva();
                                 }
                            }
                        
                        }else{
                            System.out.println("No existe bicicleta con ese ID.");
                        }

                        }else{
                        System.out.println("No hay bicicletas disponibles.");
                        }
                    }else if ( menu == 2){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Estado de la cuenta");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println();

                    }else if (menu == 3){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Reglas");
                        estudiante.seeRules();
                        sc.nextLine();
    
                    }else if ( menu == 4){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Tiempo de reservacion");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println();

                    }else if (menu == 5){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Reporte o comentario sobre el servicio");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.print("Escribe tu comentario: ");
                        sc.nextLine();
                        String mensaje = sc.nextLine();
                        
                        //Se crea un comentario
                        Comment comentario = new Comment(mensaje, estudiante);
                        //Se agrega a la lista de comentarios
                        listaComentarios.add(comentario);
                        //Preservar los datos de la lista en el archivo
                        System.out.println();

                    }else{
                        mensajeError();
                    }
                    do{
                        System.out.print("Desea volver al menu principal? (si/no); ");
                        opcionMenu = sc.nextLine().toLowerCase();
                        if (opcionMenu.equals("no")){
                            System.out.println("Gracias por usar el aplicativo.");
                        }else{
                            mensajeError();
                        }
                    }while(!opcionMenu.equals("no") && !opcionMenu.equals("si"));
                    

                }while (menu > 5 || menu < 0 || opcionMenu.equals("si"));
                

            }else{
                mensajeError();
            }
        }while(admi_usu < 1 || admi_usu > 2 || opcionMenu.equals("no"));
        


        sc.close();
    }

    //Funciones

     //funcion de exepcion para int
    public static int verifExcepcion(Scanner sc, String mensaje){
        boolean v_f = true;
        int num = 0;
        do{ 
            try { 
                System.out.print(mensaje);
                num = sc.nextInt();
                v_f = true;
            }catch(Exception e){ 
                sc.nextLine();
                mensajeError();
                v_f = false;
            }
        }while(!v_f);
        return num;   
    }  
    //Funcion para mensaje de error
    public static void mensajeError (){
        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
        System.out.println("Ingrese de nuevo el valor correcto");
        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
        
    }

}
