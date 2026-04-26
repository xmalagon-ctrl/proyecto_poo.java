import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String nombre_st = " ", opcionMenu = " ", nombre_admi, estado;
        int tiun = 0, num_estacion, id, admi_usu, menu,cedulaEstudiante, cedulaAdmi;
        boolean verif_u, verif_tiun, verif_CC_TI, estadoCorrecto ;
        Student estudiante, est_admi;
        Administrator administrador;
        ArrayList<Student> listaEstudiante = new ArrayList<>();
        ArrayList<Administrator> listaAdministrador = new ArrayList<>();
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

        do {
            admi_usu = verifExcepcion(sc, "Si es administrativo ingrese 1 y si es estudiante ingrese 2: ");
            sc.nextLine();
            if(admi_usu == 1){
                do{
                //Nombre del administrador
                System.out.print("Ingresa el nombre del administrador: ");
                nombre_admi = sc.nextLine();

                //Numero C.C 
                cedulaAdmi = verifExcepcion(sc, "Ingresa su C.C: ");

                //Clase administrador
                administrador = new Administrator(nombre_admi,cedulaAdmi);
                sc.nextLine();
                verif_u = administrador.setUserName(nombre_admi);
                verif_CC_TI = administrador.setCedula(cedulaAdmi);
    
                }while(!verif_u || !verif_CC_TI);

                //Agregar administrador a la lista de administrador
                listaAdministrador.add(administrador);

                do{
                    //Menu principal
                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                    System.out.println("Menu principal: ");
                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                    System.out.println("1. Penalizar estudiante");
                    System.out.println("2. Agregar cicla");
                    System.out.println("3. Reglas");
                    System.out.println("3. Ver reportes");
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
                                System.out.print("Ingrese el estado que desea ponerle: ");
                                estado = sc.nextLine();
                                estadoCorrecto = administrador.penalizeStudent(listaEstudiante,tiun, estado);
                           }
                        }while(!estadoCorrecto);


                    }else if ( menu == 2){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Agregar cicla");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println();

                    }else if (menu == 3){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Reglas");
                        administrador.seeRules();
                        sc.nextLine();
    
                    }else if ( menu == 4){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Ver reportes");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println();

                    }else if (menu == 5){
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        System.out.println("Quitar a acceso a estudiante");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
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

                do{
                    //Menu principal
                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                    System.out.println("Menu principal: ");
                    System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                    System.out.println("1. Reservacion de cicla");
                    System.out.println("2. Estado de la cuenta");
                    System.out.println("3. Reglas");
                    System.out.println("3. Tiempo de reservacion");
                    System.out.println("5. Queja o comentario sobre servicio");
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
                        System.out.println("6.Calle 30");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
                        //bucle para error
                        num_estacion = verifExcepcion(sc, "Ingresa el numero de la estacion que deseas usar (1 a 5): "); //hacer validador de rango de estaciones dentro de la clase
                        //muestra el id de bicicletas disponibles en esa estacion(y tal vez al frente muestre de una ves su estado)
                        id = verifExcepcion(sc, "Ingresa el id de la cicla que deseas usar: ");
                        //ingresa id 

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
                        System.out.println("Queja o comentario sobre servicio");
                        System.out.println("- - - -- - - - -- - - -- - - - -- - - - -- - - - -- ");
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
