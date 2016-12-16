package restaurante;

import java.sql.SQLException;
import model.Cocinero;
import persistence.RestauranteJDBC;

public class Restaurante {
    public static void main(String[] args) {
        RestauranteJDBC gestor = new RestauranteJDBC();
        
        try{
            System.out.println("Estableciendo conexion con la bbdd...");
            gestor.conectar();
            System.out.println("Conectado a la bbdd restaurante");
            Cocinero a = new Cocinero("Ferran Adria", "123456789", "hombre", 56, 22, "Platos principales");
            gestor.insertCheff(a);
            System.out.println("Cocinero dado de alta.");
            a = new Cocinero ("Alec ", "123456789", "hombre", 56, 22, "Postres");
            a = new Cocinero ("Ferran Adria", "123456789", "hombre", 56, 22, "Postres");
           gestor.modificarCheff(a);
           System.out.println("Cocinero modificado.");
            gestor.eliminarCheff("Ferran Adria");
            System.out.println("Cocinero eliminado.");
           gestor.desconectar();
            System.out.println("Desconectar de la bbdd...");
        }catch(SQLException ex){
            System.out.println("Error con la BBDD: " + ex.getMessage());
        }
        
    } 
}
