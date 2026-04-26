//clase hija para administraador

import java.util.ArrayList;

public class Administrator extends User{
    //atributos
    //..

    //constructor
    public Administrator (String username, long cedula){
        super(username, cedula);
    }

    //metodos
   public boolean penalizeStudent(ArrayList<Student> listaEstudiante, long tiun, String estado){
        for (int i = 0; i < listaEstudiante.size(); i++){
            if(listaEstudiante.get(i).getTiun() == tiun){
                if(listaEstudiante.get(i).setState(estado)){
                    System.out.println("Estado actualizado correctamente.");
                    return true;
                }else{
                    return false;
                }
            }
        }

        System.out.println("Ese TIUN no pertenece a ningún estudiante");
        return false;
    }
   //addBicycle(), viewReports(), removeStudent()*/

}
