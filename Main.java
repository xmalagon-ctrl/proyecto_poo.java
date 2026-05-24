import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String nombre_st = " ", opcionMenu = " ", nombre_admi;
        int tiun = 0, num_estacion, id, admi_usu, menu,cedulaEstudiante, cedulaAdmi;
        boolean verif_u , verif_tiun, verif_CC_TI, estadoCorrecto,fueAgregada;
        Student estudiante;
        Station estacionRecogida,estacionEntrega;
        Bike bicicleta = null;
        Reservar reserva = null;
        ArrayList<Student> listaEstudiante = new ArrayList<>();
        ArrayList<Comment> listaComentarios = new ArrayList<>();
        ArrayList<Comment> listaComentariosAdmin = new ArrayList<>();
        var estaciones = new ArrayList<Station>(); //lista de estaciones
        //Estación 1: Calle 53
        estaciones.add(new Station("Calle 53",2));
        //Estación 2: CYT
        estaciones.add(new Station("CYT", 2));
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
                        System.out.println("6. Activar o Desactivar el uso de una bicicleta");;
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        menu = verifExcepcion(sc, "Ingrese el numero de lo que desea hacer: ");

                        if (menu == 1){
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            System.out.println("Penalizar estudiante");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            do{
                                if(listaEstudiante.isEmpty()){
                                    System.out.println("No hay estudiantes registrados.");
                                    estadoCorrecto = true;
                                }else{
                                    System.out.print("Ingrese tiun que desea buscar: ");
                                    tiun = sc.nextInt();
                                    sc.nextLine();
                                    
                                    estadoCorrecto = adminsAutorizados.get(posicionAdministrador).penalizeStudent(listaEstudiante,tiun);
                                    
                                    System.out.print("¿Desea registrar el motivo de la penalización?(si/no): ");
                                    String opcion = sc.nextLine().toLowerCase();

                                    if(opcion.equals("si")){
                                        System.out.print("Ingrese el motivo: ");
                                        String motivo = sc.nextLine();
                                        adminsAutorizados.get(posicionAdministrador).addMotivoPenalizacion(listaComentariosAdmin,tiun, motivo);

                                    }
                                }
                            }while(!estadoCorrecto);
                            sc.nextLine();

                        }else if ( menu == 2){
                        
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            System.out.println("Agregar cicla");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                              System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                                        do{
                                do{
                                    num_estacion = seleccionDeEstacion(sc, "Ingrese la estación deseada: ");
                                    boolean existe;

                                    do{
                                        id = idCicla(sc, "Ingrese el ID de la bicicleta: ");
                                        existe = false;
                                        for(Station estacion : estaciones){
                                            if(estacion.existeBicicleta(id)){
                                                existe = true;
                                                System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                                                System.out.println("El ID ya existe. Porfavor ingrese uno diferente");
                                                System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                                                break;
                                            }
                                        }
                                    }while(existe);

                                    //Se crea una nueva bicicleta
                                    Bike newBike = new Bike(id, "disponible");
                                    //Se verifica la capacidad de la estacion y se agrega la bicicleta
                                    fueAgregada = estaciones.get(num_estacion-1).alertaMaxBicicleta();

                                    if (!fueAgregada) {
                                        estaciones.get(num_estacion-1).agregarBicicleta(newBike);
                                        System.out.println("La cicla fue agregada correctamente.");
                                    }else{
                                        System.out.println("Agregue la cicla en otra estacion");
                                    }

                                }while (fueAgregada);
                                sc.nextLine();
                                do{
                                    System.out.print("Desea agregar otra cicla? (si/no); ");
                                    opcionMenu = sc.nextLine().toLowerCase();
                                    if (opcionMenu.equals("no")){
                                        System.out.println("Se agregaron las ciclas con exito.");
                                    }else if(!opcionMenu.equals("si")){
                                        mensajeError();
                                    }
                                }while(!opcionMenu.equals("no") && !opcionMenu.equals("si"));
                                
                            }while (opcionMenu.equals("si"));
                            

                        }else if (menu == 3){
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            System.out.println("Reglas");
                            adminsAutorizados.get(posicionAdministrador).seeRules();
                            sc.nextLine();
        
                        }else if ( menu == 4){
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            System.out.println("Ver reportes");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            Station.alertaEstaciones(estaciones);
                         int option = 0;
                            do{
                            System.out.println("1. Estado de las bicicletas");
                            System.out.println("2. Reportes o comentarios de los estudiantes");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            option = verifExcepcion(sc,"Ingrese la opción a la cual desea acceder: ");
                            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                            switch(option){

                                case 1:{
                                    //Estado de las bicicletas
                                    num_estacion =seleccionDeEstacion(sc, "Ingrese la estación deseada: ");
                                    sc.nextLine();
                                    // Se llama al método que imprime la información de las biciletas 
                                    estaciones.get(num_estacion - 1).infoBicicletasGeneral();
                                    System.out.print("¿Desea cambiar el estado de una bicicleta? si/no: ");
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
                                            sc.nextLine();
                                            break;
                                        }
                                        default:{
                                            mensajeError();
                                        }

                                    }

                                }while(option==0 || option<1 || option>2);


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

                        }else if (menu == 6){
                            int a_d_bicis;
                            do {
                                a_d_bicis =verifExcepcion(sc, "Selecciona 1 para activar uso y 2 para desactivar: ");
                                if (a_d_bicis == 1){
                                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                                    System.out.println("Activar el uso de una bicicleta");
                                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                                    System.out.println();
                                    if (reserva != null) {
                                    recorreListaEstudiantes(sc, 1, listaEstudiante); 
                                    } else {
                                        System.out.println("No hay reservas en este momento.");
                                    }

                                }else if (a_d_bicis == 2){
                                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                                    System.out.println("Desactivar el uso de una bicicleta");
                                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                                    System.out.println();

                                    if (reserva != null) {
                                    recorreListaEstudiantes(sc, 2, listaEstudiante); 
                                    } else {
                                        System.out.println("No hay reservas en este momento.");
                                    }

                                }else {
                                    mensajeError();
                                }
                                
                            } while (a_d_bicis > 2 || a_d_bicis < 1 );
    
                            sc.nextLine();
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

                    }while (menu > 6 || menu < 0 || opcionMenu.equals("si"));

                }
                

            }else if (admi_usu == 2){
                boolean existenciaEstudiante = false;
                estudiante = null;
                do{
                    //Nombre del estudiante
                    System.out.print("Ingresa el nombre del estudiante: ");
                    nombre_st= sc.nextLine();

                    //Numero C.C o T.I
                    cedulaEstudiante = verifExcepcion(sc, "Ingresa su C.C o T.I: ");

                    //Numero tiun
                    tiun = verifExcepcion(sc, "Ingresa el Tiun de su carnet: ");
                    for (Student student: listaEstudiante) {
                        if(student.getCedula() == cedulaEstudiante && student.getTiun() == tiun && student.getUserName().equals(nombre_st)){
                            estudiante = student;
                            existenciaEstudiante = true;
                            break;
                        }else {
                            existenciaEstudiante = false;
                        }
                    }
                    if (existenciaEstudiante){
                        verif_u = true;
                        verif_CC_TI = true;
                        verif_tiun = true;
                    }else{
                        //Clase estudiante
                        estudiante = new Student(nombre_st,cedulaEstudiante, tiun);
                        sc.nextLine();
                        verif_u = estudiante.setUserName(nombre_st);
                        verif_CC_TI = estudiante.setCedula(cedulaEstudiante);
                        verif_tiun = estudiante.setTiun(tiun);
                    }
                    
    
                }while(!verif_u || !verif_tiun || !verif_CC_TI);

                if (!existenciaEstudiante){
                    //agregar estudiante a la lista de estudiantes
                    listaEstudiante.add(estudiante);
                    //metodos del historial ***********************************************************************************
                            DocReader.crearArchivo("poo/archivoHistorial/estudianteReal.txt");
                            DocReader.verificarDuplicados("poo/archivoHistorial/estudianteReal.txt", tiun);
                            DocReader.contenidoArchivo("poo/archivoHistorial/estudianteReal.txt", nombre_st, tiun);
                            DocReader.leerArchivo("poo/archivoHistorial/estudianteReal.txt");
                    //*-********************************************************************************************************
                }
                do{
                    //Menu principal
                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                    System.out.println("Menu principal: ");
                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                    System.out.println("1. Reservacion de cicla");
                    System.out.println("2. Estado de la cuenta");
                    System.out.println("3. Reglas");
                    System.out.println("4. Estado de reservacion");
                    System.out.println("5. Queja o comentario sobre servicio");
                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                    menu = verifExcepcion(sc, "Ingrese el numero de lo que desea hacer: ");

                    if (menu == 1){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Reservacion de cicla");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        boolean verifEstacion = false;
                        do{
                        num_estacion = seleccionDeEstacion(sc, "Ingresa el numero de la estacion que deseas usar (1 a 6): ");
                        // Se guarda la estación elegida
                        estacionRecogida = estaciones.get(num_estacion - 1);

                        
                            //boolean verifCicla = false;
                            if(estacionRecogida.getBicis().size() > 0){
                            
                                System.out.println("--- BICICLETAS DISPONIBLES EN LA ESTACIÓN ---");
                                estacionRecogida.infoBicicletasDisponibles();
                                System.out.println("---------------------------------------------\n");

                                do{
                                    // Ingresa ID  de cicla a usar
                                    id = idCicla(sc, "Ingresa el id de la cicla que deseas usar: ");
                                    for (Bike bike : estacionRecogida.getBicis()) {
                                        if (bike.getId() == id) {
                                            bicicleta = bike;
                                            //verifCicla = true; 
                                            break;
                                        }
                                    }
                                    if(bicicleta != null /*|| verifCicla*/){
                                    // Clase reserva
                                        reserva = new Reservar(bicicleta, estudiante);
                                        // Verifica si la estación seleccionada es correcta
                                        boolean recogidaCorrecta = reserva.setEstacionRecogida(estacionRecogida);
                                        
                                        if(recogidaCorrecta){
                                            int entrega = seleccionDeEstacion(sc, "Ingrese estación de entrega (1 a 6): ");
                                            estacionEntrega = estaciones.get(entrega - 1);
                                            boolean entregaCorrecta = reserva.setEstacionEntrega(estacionEntrega);
                                            
                                            if(entregaCorrecta){
                                                reserva.realizarReserva();
                                                verifEstacion = false;
                                            }
                                        }
                                    
                                    }else{
                                        System.out.println("No existe bicicleta con ese ID. \nIngrese ID correcto.");
                                    }
                                    sc.nextLine();
                                }while(bicicleta == null/*|| !verifCicla*/);
                                
                            }else{
                                System.out.println("No hay bicicletas en esta estación. \nIngrese otra estacion");
                                verifEstacion = true;
                                
                            }
                        }while(verifEstacion);
                        

                    }else if ( menu == 2){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Estado de la cuenta");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println();
                       
                        estudiante.estadoPenalizacion(); 
                        
                        sc.nextLine();

                    }else if (menu == 3){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Reglas");
                        estudiante.seeRules();
                        sc.nextLine();
    
                    }else if ( menu == 4){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Estado de reservacion");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println();
                        if (reserva != null) {
                            // El estudiante solo consulta. Tu método se encarga de decirle si sigue activa o si se le canceló en ese instante
                            reserva.verificarExcesoReserva(); 
                        } else {
                            System.out.println("Usted no cuenta con ninguna reserva activa en este momento.");
                        }
                        sc.nextLine();


                    }else if (menu == 5){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Queja o comentario sobre servicio");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println();
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

    //funcion para verificar que la estacion se encuentre en el rango correcto
    public static int seleccionDeEstacion(Scanner sc, String mensaje){
        int num_estacion = 0;
        do{
            System.out.println("Estaciones: ");
            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
            System.out.println("1. Calle 53");
            System.out.println("2. CYT");
            System.out.println("3. Uriel");
            System.out.println("4. Calle 45");
            System.out.println("5. Calle 26");
            System.out.println("6. Calle 30");
            System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
            num_estacion = verifExcepcion(sc, mensaje );
                if(num_estacion > 6 || num_estacion < 1){
                    System.out.println("Esta estacion no existe. Ingrese de nuevo estacion correcta");
                }
            }while(num_estacion > 6 || num_estacion < 1);
            return num_estacion;
    }

    //funcion para verificar que ID  de cicla es de tres digitos
    public static int idCicla(Scanner sc, String mensaje){
        boolean verifID = false;
        int id;
        do{
            id = verifExcepcion(sc, mensaje);
            String idString = Integer.toString(id);
            if(idString.length() == 3 ){
                id = Integer.parseInt(idString);
                verifID = true;
            }else{
                System.out.println("ID incorrecto. Ingreeselo de nuevo");
            }
        }while(! verifID );
        return id;
    }

    //funcion para  recorrer lista de estudiantes y activar o finalizar el uso
    public static void recorreListaEstudiantes(Scanner sc, int operacion, ArrayList<Student> listaEstudiante){
        boolean verifStud = false;
        long tiun;
        do{ 
            if(listaEstudiante.isEmpty()){
            System.out.println("No hay estudiantes registrados.");
            verifStud = true;
            }else{
                System.out.print("Ingrese tiun que desea buscar: ");
                tiun = sc.nextInt();
                sc.nextLine();
                
                for (Student student : listaEstudiante) {
                    if (student.getTiun() == tiun) {
                        if(operacion == 1){
                            student.activacionDeUso();
                            verifStud = true;
                        }else if (operacion == 2){
                            student.finalizacionDeUso();
                            verifStud = true;
                        }   
                    }
                }
                if(!verifStud){
                    System.out.println("Ese TIUN no pertenece a ningún estudiante registrado.");
                }   
            }
        }while(!verifStud);
    }
}
