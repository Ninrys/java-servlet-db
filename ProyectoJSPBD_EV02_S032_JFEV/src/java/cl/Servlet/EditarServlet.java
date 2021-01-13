/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Ninry
 */
public class EditarServlet extends HttpServlet {

    Connection conector;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String i = (String) (request.getParameter("borrar"));
        System.out.println(i);
        String sql = "SELECT * FROM solicitud WHERE ID_SOLICITUD = ?";
        InitialContext context;
        DataSource dsource;
        if (i != null) {
            try {
                context = new InitialContext();
                dsource = (DataSource) context.lookup("java:/comp/env/jdbc/tallerprog1");
                conector = dsource.getConnection();
            } catch (Exception e) {
                System.out.println("Error asd" + e.toString());
                e.printStackTrace();
            }

            try {

                PreparedStatement delete = conector.prepareStatement(sql);
                delete.setString(1, i);

                System.out.println(delete);
                ResultSet rs = delete.executeQuery();
                request.setAttribute("rsupdate", rs);

                this.getServletContext().getRequestDispatcher("/Modificar.jsp").forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            response.sendRedirect("ListarServlet");
        }

        //Editar xdxd
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InitialContext context;
        DataSource dsource;
        try {
            context = new InitialContext();
            dsource = (DataSource) context.lookup("java:/comp/env/jdbc/tallerprog1");
            conector = dsource.getConnection();
        } catch (Exception e) {
            System.out.println("Error asd" + e.toString());
            e.printStackTrace();
        }

        try {

            String upd = "UPDATE solicitud SET nombres= ?, apellido_paterno= ?, apellido_materno = ?, FNacimiento= ? , FK_estadocivil=? ,Cdonante= ?, Observacion = ? WHERE id_solicitud =?";
            PreparedStatement update = conector.prepareStatement(upd);

            for (int i = 1; i < 9; i++) {
                String req = (String) request.getParameter("var" + i);
                if (i == 4) {
                    try {

                        java.util.Date selectedDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("var4"));
                        java.sql.Date selectedSQLDate = new java.sql.Date(selectedDate.getTime());
                        update.setDate(4, selectedSQLDate);
                        System.out.println(selectedSQLDate);
                    } catch (ParseException ex) {
                        Logger.getLogger(SolicitudServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    update.setString(i, req);
                }

            }
            update.execute();
            String Sql = "SELECT solicitud.ID_SOLICITUD, solicitud.FSOLICITUD, solicitud.NOMBRES, solicitud.APELLIDO_PATERNO, solicitud.APELLIDO_MATERNO, solicitud.RUT, solicitud.FNACIMIENTO, solicitud.CDONANTE, tipo_solicitud.TIPOSOLICITUD, estadocivil.ESTADOCIVIL, solicitud.OBSERVACION FROM `solicitud` inner join tipo_solicitud ON solicitud.FK_TIPO=tipo_solicitud.PK_TIPOSOLICITUD INNER JOIN estadocivil ON solicitud.FK_ESTADOCIVIL=estadocivil.PK_ESTADOCIVIL";
            PreparedStatement lista = conector.prepareStatement(Sql);
            ResultSet rs = lista.executeQuery();
            request.setAttribute("rslista", rs);

            response.sendRedirect("ListarServlet");
            // this.getServletContext().getRequestDispatcher("/Listados.jsp").forward(request, response); 

        } catch (SQLException ex) {
            Logger.getLogger(SolicitudServlet.class.getName()).log(Level.SEVERE, null, ex);

        };

        System.out.println("asdasdsa");

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
