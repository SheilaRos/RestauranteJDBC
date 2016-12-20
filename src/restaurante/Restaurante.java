package restaurante;

import java.sql.SQLException;
import java.util.List;
import model.Cocinero;
import model.Plato;
import persistence.RestauranteJDBC;

public class Restaurante {
    public static void main(String[] args) {
        RestauranteJDBC gestor = new RestauranteJDBC();
        
        try{
            System.out.println("Estableciendo conexion con la bbdd...");
            gestor.conectar();
            System.out.println("Conectado a la bbdd restaurante");
            Cocinero a = new Cocinero("Ferran Adria", "123456789", "hombre", 56, 22, "Platos principales");
           // gestor.insertCheff(a);
            System.out.println("Cocinero dado de alta.");
//            a = new Cocinero ("Alec ", "123456789", "hombre", 56, 22, "Postres");
//            a = new Cocinero ("Ferran Adria", "123456789", "hombre", 56, 22, "Postres");
//           gestor.modificarCheff(a);
//           List<Cocinero> todosCocineros = gestor.devolverCocinero();
//            System.out.println("Listado de cocineros:");
//            for(Cocinero c: todosCocineros){
//                System.out.println(c);
//            }
//           System.out.println("Cocinero modificado.");
//            gestor.eliminarCheff("Ferran Adria");
//            System.out.println("Cocinero eliminado.");
              Plato p = new Plato("Macarrones", "primero", 8.0, a);
             // gestor.insertPlato(p);
                System.out.println("Plato dado de alta.");
                List<Plato> todosPlatos = gestor.devolverPlatos();
                System.out.println("Lista de platos");
                for(Plato plato: todosPlatos){
                    System.out.println(plato);
                }
           gestor.desconectar();
            System.out.println("Desconectar de la bbdd...");
        }catch(SQLException ex){
            System.out.println("Error con la BBDD: " + ex.getMessage());
        }
        
    } 
}
