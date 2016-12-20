package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Cocinero;
import model.Plato;


public class RestauranteJDBC {
    private Connection connection;
    //Insertar plato
    public void insertPlato(Plato p) throws SQLException{
        String insert = "insert into plato values (?, ?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(insert);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getTipo());
        ps.setDouble(3, p.getPrecio());
        ps.setString(4, p.getCocinero().getNombre());
        ps.execute();
        ps.close();
    }
    public List<Plato> devolverPlatos() throws SQLException{
        List<Plato> platos = new ArrayList<>();
        String query = "select * from plato;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Plato p = new Plato();
            p.setNombre(rs.getString("nombre"));
            p.setPrecio(rs.getDouble("precio"));
            p.setTipo(rs.getString("tipo"));
            p.setCocinero(devolverCocinero(rs.getString("cocinero")));
            platos.add(p);
        }
        rs.close();
        st.close();
        return platos;
    }
    //Devolver un cocinero concreto
    public Cocinero devolverCocinero(String nombre) throws SQLException{
        Cocinero c = new Cocinero();
        String query = "select * from cocinero where nombre ="+nombre+";";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            c.setNombre(rs.getString("nombre"));
            c.setEdad(rs.getInt("edad"));
            c.setSexo(rs.getString("sexo"));
            c.setEspecialidad(rs.getString("especialidad"));
            c.setExperiencia(rs.getInt("experiencia"));
            c.setTelefono(rs.getString("telefono"));
        }
        rs.close();
        st.close();
        return c;
    }
    //Devolver la lista con todos los datos de los cocineros
    public List<Cocinero> devolverCocinero() throws SQLException{
        List<Cocinero> cocineros = new ArrayList<>();
        String query = "select * from cocinero;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Cocinero c = new Cocinero();
            c.setNombre(rs.getString("nombre"));
            c.setEdad(rs.getInt("edad"));
            c.setSexo(rs.getString("sexo"));
            c.setEspecialidad(rs.getString("especialidad"));
            c.setExperiencia(rs.getInt("experiencia"));
            c.setTelefono(rs.getString("telefono"));
            cocineros.add(c);
        }
        rs.close();
        st.close();
        return cocineros;
    }
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
