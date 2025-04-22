/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mantenimiento.servlet;

import java.io.IOException;
import java.sql.*;

@jakarta.servlet.annotation.WebServlet(name = "RegistrarPersona", urlPatterns
        = {"/RegistrarPersona"})
public class RegistrarSolicitud extends jakarta.servlet.http.HttpServlet {

    protected void ProcessRequest(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        try (java.io.PrintWriter out = response.getWriter()) {
            try {
                String nombre = request.getParameter("nombre");
                int edad = Integer.parseInt(request.getParameter("edad"));
                String sexo = request.getParameter("sexo");
                String fecha = request.getParameter("fecha");
                int idocupacion = Integer.parseInt(request.getParameter("ocupacion"));
                String ocupacion = "";
                String usuario = request.getParameter("usuario");
                String password = request.getParameter("password");
                String query = "INSERT INTO persona(nombre_persona, edad_persona, sexo_persona, id_ocupacion, fecha_nac, usuario, contrasenia) " +"VALUES ('"+ nombre + "'," + edad + ", '" + sexo + "' , " + idocupacion + ", '" + fecha +"' ,'" +usuario+ "'  , '"+password+"'  )";

                Conexion con = new Conexion();
                con.setQuery(query);
                String query2 =
                "SELECT ocupacion FROM ocupaciones WHERE id_ocupacion="+idocupacion;
con.setRs(query2);
                ResultSet rs = con.getRs();
                rs.next();
                ocupacion = rs.getString(1);
                con.cerrarConexion();
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Registro" + usuario + "</title>");
                out.println("<link rel='stylesheet' href='css/bootstrap.min.css'>");
                out.println("<link rel='stylesheet' href='css/estilo.css' >");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1 class='text-center'>Usted ha registrado un usuario exitosamente</h1 >");
                out.println("<section class='container text-center'>");
                out.println("<h3>Nombre: </h3>" + nombre);
                out.println("<h3>Edad: </h3>" + edad);
                out.println("<h3>Sexo: </h3>" + sexo);
                out.println("<h3>Ocupaci&oacute;n: </h3>" + ocupacion);
                out.println("<h3>Fecha de nacimiento: </h3>" + fecha);
                out.println("<br><a class='btn btn-light' href='login.html'>Volver</a>");
                out.println("</section>");
                out.println("</body>");
                out.println("</html>");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    protected void doPost(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException,
            IOException {
        ProcessRequest(request, response);
    }

    protected void doGet(jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException,
            IOException {
        ProcessRequest(request, response);
    }
}
