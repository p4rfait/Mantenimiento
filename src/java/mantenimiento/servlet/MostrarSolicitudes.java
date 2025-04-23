package mantenimiento.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "MostrarSolicitudes", urlPatterns = {"/MostrarSolicitudes"})
public class MostrarSolicitudes extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            Conexion con = new Conexion();
            String consulta = "SELECT * FROM solicitudes";
            ResultSet rs = con.ejecutarConsulta(consulta);

            out.println("<html><head><title>Lista de Solicitudes</title>");
            out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
            out.println("</head><body class='container py-5'>");
            out.println("<h2 class='mb-4 text-center'>Solicitudes Registradas</h2>");

            out.println("<table class='table table-bordered table-striped'>");
            out.println("<thead><tr><th>ID</th><th>Nombre</th><th>Departamento</th><th>Tipo</th><th>Descripción</th></tr></thead>");
            out.println("<tbody>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("client_name") + "</td>");
                out.println("<td>" + rs.getInt("apartment_number") + "</td>");
                out.println("<td>" + rs.getString("problem_type") + "</td>");
                out.println("<td>" + rs.getString("problem_description") + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody></table>");
            out.println("<div class='text-center mt-3'><a href='formularioSolicitud.html' class='btn btn-primary'>Volver</a></div>");
            out.println("<footer class='text-center mt-5 py-3 bg-light border-top'>");
            out.println("<small>&copy; 2025 Sistema de Mantenimiento - Desarrollado por Tomás Armando Campos Lopez (CL231461) y Omarvis Innaun Mendoza Portillo (MP192089)</small>");
            out.println("</footer>");
            out.println("</body></html>");

            con.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
