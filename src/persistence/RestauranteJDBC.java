package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import model.Cocinero;


public class RestauranteJDBC {
    private Connection connection;
    //Insertar un cocinero a la BBDD
    public void insertCheff(Cocinero c) throws SQLException{
        String insert = "insert into cocinero values (?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(insert);
        ps.setString(1, c.getNombre());
        ps.setString(2, c.getTelefono());
        ps.setString(3, c.getSexo());
        ps.setInt(4, c.getEdad());
        ps.setInt(5, c.getExperiencia());
        ps.setString(6, c.getEspecialidad());
        ps.execute();
        ps.close();
    }
    //Modificar cocinero
    public void modificarCheff(Cocinero c) throws SQLException{
        String update = "update cocinero set telefono=?, sexo=?, edad =?, experiencia =?, especialidad =? where nombre = '" + c.getNombre()+"';";
        //se tiene que preparar con los ?
        PreparedStatement ps = connection.prepareStatement(update);
        ps.setString(1, c.getTelefono());
        ps.setString(2, c.getSexo());
        ps.setInt(3, c.getEdad());
        ps.setInt(4, c.getExperiencia());
        ps.setString(5, c.getEspecialidad());
        ps.execute();
        ps.close();
    }
    //eliminar cocinero
    public void eliminarCheff(String nombre) throws SQLException{
        String delete = "delete from cocinero where nombre = '" + nombre+"';";
        //como no tienes que preparar los ?, lo haces directamente con un Statement
        Statement st = connection.createStatement();
        st.executeUpdate(delete);
        st.close();
    }
    //En un entorno de ventanas se conecta y desconecta en cada metodo, y se declaran como privado
    //conectar BBDD
    public void conectar() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/restaurant";
        String usr = "root";
        String pass = "";
        connection = DriverManager.getConnection(url, usr, pass);
    }
    //desconectar BBDD
    public void desconectar() throws SQLException{
        if(connection != null){
            connection.close();
        }
    }
}
