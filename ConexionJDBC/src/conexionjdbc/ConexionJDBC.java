
package conexionjdbc;

//Incluir en Java librería para incluir bases que permitan el trabajo
//con la base de datos de MySQL
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//

public class ConexionJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //definir cadena de conexión
        //usuario por defecto
        String usuario = "root";
        String password = "";
        //URL donde se encuentra el servicio
        String url = "jdbc:mysql://localhost:3306/prueba";
        
        //establecer conexión
        Connection conexion;
        //ejecutar sentencias SQL a través de la conexión establecida
        Statement statement;
        //recibir respuesta desde la base de datos
        ResultSet rs;
        
        
        try {
            //instanciar Driver para cargarlo en memoria y utilizarlo en la aplicación
            //instanciar cualquier librería externa al proyecto
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //hacer la conexión
            conexion = DriverManager.getConnection(url, usuario, password);
            //instanciar elemento para hacer consultas SQL
            statement = conexion.createStatement();
            //métodos de consulta
            statement.execute("INSERT INTO USUARIO(user-name, user-password)VALUES('ABC', 'ABC123')");
            rs = statement.executeQuery("SELECT * FROM USUARIO ");
            //avanzar dentro de la estructura
            rs.next();
            do{
                System.out.println(rs.getInt("user-id") +  ": " + rs.getString("user-name"));
            }while(rs.next());
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
