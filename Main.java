import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        User usuario;
        String nombre_u, opcionMenu;
        int tiun = 0, num_estacion, id, admi_usu, menu;
        boolean verif_u, verif_tiun;// v_f = true; 
        //Estación 1: Calle 53
        Station calle53 = new Station("Calle 53", 15);
        //Estación 2: CYT
        Station CYT = new Station("CYT", 50);
        //Estación 3: Uriel
        Station Uriel = new Station("Uriel", 15);   
        //Estación 4: Calle 45
        Station calle45 = new Station("Calle 45", 15);
        //Estación 5: Calle 26  
        Station calle26 = new Station("Calle 26", 30);
        //Estación 6: Calle 30
        Station calle30 = new Station("Calle 30", 40);
        
        do {
            admi_usu = verifExcepcion(sc, "Si es administrativo ingrese 1 y si es usuario ingrese 2: ");
            sc.nextLine();
            if(admi_usu == 1){
                //codigo para el administrativo

            }else if (admi_usu == 2){
                do{
                //Nombre del usuario
                System.out.print("Ingresa el nombre del usuario: ");
                nombre_u = sc.nextLine();

                //Numero tiun
                tiun = verifExcepcion(sc, "Ingresa el Tiun de su carnet: ");
                
                //Clase user
                usuario = new User(nombre_u, tiun);
                sc.nextLine();
                verif_u = usuario.setUserName(nombre_u);
                verif_tiun = usuario.setTiun(tiun);
    
                }while(!verif_u || !verif_tiun);

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
                        System.out.println("1." + calle53.getName_station());
                        System.out.println("2." + CYT.getName_station());
                        System.out.println("3." + Uriel.getName_station());
                        System.out.println("4." + calle45.getName_station());
                        System.out.println("5." + calle26.getName_station());
                        System.out.println("6." + calle30.getName_station());
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
                        usuario.seeRules();
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
        }while(admi_usu < 1 || admi_usu > 2);
        
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
