/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.Servlet;

//import cl.dto.Conexion; :(((
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
//import org.apache.tomcat.jdbc.pool.DataSource;
import javax.sql.DataSource;

/**
 *
 * @author Ninry
 */
public class SolicitudServlet extends HttpServlet {
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
            out.println("<title>Servlet SolicitudServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SolicitudServlet at " + request.getContextPath() + "</h1>");
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
         this.getServletContext().getRequestDispatcher("/Solicitud.jsp").forward(request, response);
        
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
            context= new InitialContext();
            dsource= (DataSource) context.lookup("java:/comp/env/jdbc/tallerprog1");
            conector= dsource.getConnection();
        } catch (Exception e) {
            System.out.println("Error asd" + e.toString());
            e.printStackTrace();
        }
        
        try {
         //   conector = new Conexion();
        //Connection reg = conector.conectar();
        String sql="INSERT INTO solicitud (FK_TIPO, NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO, RUT, FNACIMIENTO, FK_ESTADOCIVIL, CDONANTE, FSOLICITUD, OBSERVACION) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement insert= conector.prepareStatement(sql);
            
           
        for (int i = 1; i < 11; i++) {
           String req= (String)request.getParameter("var"+i);
           if(i==6){
              
               try {
                  
                  java.util.Date selectedDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("var6"));
                  java.sql.Date selectedSQLDate =  new java.sql.Date(selectedDate.getTime());
                  insert.setDate(6, selectedSQLDate);
                   System.out.println(selectedSQLDate);
               } catch (ParseException ex) {
                   Logger.getLogger(SolicitudServlet.class.getName()).log(Level.SEVERE, null, ex);
               }
             //  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               
           }else{
               if(i==9){
                long millis=System.currentTimeMillis();  
                   
                 java.sql.Date date=new java.sql.Date(millis); 
                   System.out.println(date);
             insert.setDate(9, date);  
               
           
               }else{
            insert.setString(i, req);
            System.out.println(i + " "+ req);}
                }
        }
            System.out.println(insert);
         insert.execute();
         
         
        } catch (SQLException ex) {
            Logger.getLogger(SolicitudServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
      //  request.setAttribute("resultado", "Solicitud ingresada correctamente");
       this.getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response); 
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
