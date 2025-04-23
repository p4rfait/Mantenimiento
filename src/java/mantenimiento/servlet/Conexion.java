package mantenimiento.servlet;

import java.sql.*;

public class Conexion {

    private Connection conexion = null;

    public Conexion() throws SQLException {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conectar a la base de datos "Mantenimiento"
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/Mantenimiento", "root", "");

        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: No se encontró el driver de la BD: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("ERROR: Fallo en la conexión con la BD: " + e.getMessage());
            throw e;
        }
    }

    // Obtener la conexión (útil para crear PreparedStatement desde el servlet)
    public Connection getConexion() {
        return conexion;
    }

    // Ejecutar una consulta SELECT y obtener ResultSet
    public ResultSet ejecutarConsulta(String consulta) {
        try {
            Statement stmt = conexion.createStatement();
            return stmt.executeQuery(consulta);
        } catch (SQLException e) {
            System.out.println("ERROR al ejecutar consulta: " + e.getMessage());
            return null;
        }
    }

    // Ejecutar una actualización (INSERT, UPDATE, DELETE)
    public int ejecutarActualizacion(String consulta) throws SQLException {
        try (Statement stmt = conexion.createStatement()) {
            return stmt.executeUpdate(consulta);
        }
    }

    // Cerrar la conexión
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("ERROR al cerrar la conexión: " + e.getMessage());
        }
    }
}
