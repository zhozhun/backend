/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectiondb;

import java.sql.*;

public class ConnectionDB {

    public static void main(String[] args) {
        // Información de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/prueba";
        String user = "root";
        String password = "";

        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión a la base de datos
            Connection conn = DriverManager.getConnection(url, user, password);

            // Operación CREATE: Insertar un nuevo usuario
            String queryCreate = "INSERT INTO usuario (user_name, user_password) VALUES (?, ?)";
            PreparedStatement psCreate = conn.prepareStatement(queryCreate);
            psCreate.setString(1, "nuevo_usuario");
            psCreate.setString(2, "123456");
            psCreate.executeUpdate();

            // Operación READ: Obtener todos los usuarios
            String queryRead = "SELECT * FROM usuario";
            Statement stRead = conn.createStatement();
            ResultSet rsRead = stRead.executeQuery(queryRead);
            while (rsRead.next()) {
                int userId = rsRead.getInt("user_id");
                String userName = rsRead.getString("user_name");
                String userPassword = rsRead.getString("user_password");
                System.out.println(userId + ", " + userName + ", " + userPassword);
            }

            // Operación UPDATE: Actualizar el usuario con id = 1
            String queryUpdate = "UPDATE usuario SET user_password = ? WHERE user_id = ?";
            PreparedStatement psUpdate = conn.prepareStatement(queryUpdate);
            psUpdate.setString(1, "654321");
            psUpdate.setInt(2, 1);
            psUpdate.executeUpdate();

            // Operación DELETE: Eliminar el usuario con id = 2
            String queryDelete = "DELETE FROM usuario WHERE user_id = ?";
            PreparedStatement psDelete = conn.prepareStatement(queryDelete);
            psDelete.setInt(1, 2);
            psDelete.executeUpdate();

            // Cerrar la conexión a la base de datos
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
