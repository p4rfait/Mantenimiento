package mantenimiento.servlet;

import java.io.IOException;
import java.sql.*;

@jakarta.servlet.annotation.WebServlet(name = "RegistrarSolicitud", urlPatterns = {"/RegistrarSolicitud"})
public class RegistrarSolicitud extends jakarta.servlet.http.HttpServlet {

    protected void ProcessRequest(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try (java.io.PrintWriter out = response.getWriter()) {
            try {
                String clientName = request.getParameter("client_name");
                int apartmentNumber = Integer.parseInt(request.getParameter("apartment_number"));
                String problemType = request.getParameter("problem_type");
                String problemDescription = request.getParameter("problem_description");

                // Crear la consulta SQL de inserción
                String query = "INSERT INTO solicitudes (client_name, apartment_number, problem_type, problem_description) "
                             + "VALUES (?, ?, ?, ?)";

                Conexion con = new Conexion();
                PreparedStatement stmt = con.getConexion().prepareStatement(query);
                stmt.setString(1, clientName);
                stmt.setInt(2, apartmentNumber);
                stmt.setString(3, problemType);
                stmt.setString(4, problemDescription);
                stmt.executeUpdate();

                // Respuesta HTML
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Solicitud Registrada</title>");
                out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
                out.println("<link rel='stylesheet' href='css/estilo.css'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container text-center mt-5'>");
                out.println("<h1>¡Solicitud registrada exitosamente!</h1>");
                out.println("<p><strong>Nombre del cliente:</strong> " + clientName + "</p>");
                out.println("<p><strong>Número de apartamento:</strong> " + apartmentNumber + "</p>");
                out.println("<p><strong>Tipo de problema:</strong> " + problemType + "</p>");
                out.println("<p><strong>Descripción:</strong> " + problemDescription + "</p>");
                out.println("<a class='btn btn-primary' href='index.html'>Registrar otra solicitud</a>");
                out.println("<a class='btn btn-secondary' href='MostrarSolicitudes'>Ver Solicitudes</a>");
                out.println("</div>");
                out.println("<footer class='text-center mt-5 py-3 bg-light border-top'>");
                out.println("<small>&copy; 2025 Sistema de Mantenimiento - Desarrollado por Tomás Armando Campos Lopez (CL231461) y Omarvis Innaun Mendoza Portillo (MP192089)</small>");
                out.println("</footer>");
                out.println("</body>");
                out.println("</html>");

                con.cerrarConexion();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        ProcessRequest(request, response);
    }

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        ProcessRequest(request, response);
    }
}
