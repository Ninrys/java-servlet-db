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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.jdbc.pool.DataSource;

/**
 *
 * @author Ninry
 */
public class ListarServlet extends HttpServlet {
    Connection conector;
    InitialContext context;
    DataSource datasource;
    

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
            out.println("<title>Servlet ListarServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListarServlet at " + request.getContextPath() + "</h1>");
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
        
         InitialContext context;
       javax.sql.DataSource dsource; 
        try {
            context= new InitialContext();
            dsource= (javax.sql.DataSource) context.lookup("java:/comp/env/jdbc/tallerprog1");
            conector= dsource.getConnection();
        } catch (Exception e) {
            System.out.println("Error asd" + e.toString());
            e.printStackTrace();
        }
        try {
          
        String Sql="SELECT solicitud.ID_SOLICITUD, solicitud.FSOLICITUD, solicitud.NOMBRES, solicitud.APELLIDO_PATERNO, solicitud.APELLIDO_MATERNO, solicitud.RUT, solicitud.FNACIMIENTO, solicitud.CDONANTE, tipo_solicitud.TIPOSOLICITUD, estadocivil.ESTADOCIVIL, solicitud.OBSERVACION FROM `solicitud` inner join tipo_solicitud ON solicitud.FK_TIPO=tipo_solicitud.PK_TIPOSOLICITUD INNER JOIN estadocivil ON solicitud.FK_ESTADOCIVIL=estadocivil.PK_ESTADOCIVIL";
       PreparedStatement lista= conector.prepareStatement(Sql);
       ResultSet rs= lista.executeQuery();
        request.setAttribute("rslista", rs);
        
        this.getServletContext().getRequestDispatcher("/Listados.jsp").forward(request, response); 
        } catch (SQLException ex) {
            Logger.getLogger(ListarServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         //Full List 
        //
        
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
       javax.sql.DataSource dsource; 
       String sql0="SELECT solicitud.ID_SOLICITUD, solicitud.FSOLICITUD, solicitud.NOMBRES, solicitud.APELLIDO_PATERNO, solicitud.APELLIDO_MATERNO, solicitud.RUT, solicitud.FNACIMIENTO, solicitud.CDONANTE, tipo_solicitud.TIPOSOLICITUD, estadocivil.ESTADOCIVIL, solicitud.OBSERVACION FROM `solicitud` inner join tipo_solicitud ON solicitud.FK_TIPO=tipo_solicitud.PK_TIPOSOLICITUD INNER JOIN estadocivil ON solicitud.FK_ESTADOCIVIL=estadocivil.PK_ESTADOCIVIL";
        try {
            context= new InitialContext();
            dsource= (javax.sql.DataSource) context.lookup("java:/comp/env/jdbc/tallerprog1");
            conector= dsource.getConnection();
        } catch (Exception e) {
            System.out.println("Error asd" + e.toString());
            e.printStackTrace();
        }
        try {
            String x= (String)request.getParameter("fSoli");
            System.out.println();
            String sql= " WHERE ";
            int j=0;
            if(request.getParameter("var1")!=null && !(request.getParameter("var1").equals(""))){
                sql= sql + "fNacimiento = '"+(String)request.getParameter("var1")+"'";
                j++;
            }
            if(request.getParameter("var2")!=null&&!(request.getParameter("var2").equals(""))){
                if(j==0){
                    sql= sql + "CDONANTE= '"+(String)request.getParameter("var2")+"'";
                    j++;
                }else{
                    sql= sql + " AND CDONANTE= '"+(String)request.getParameter("var2")+"'";
                }
            }
            if(request.getParameter("var3")!=null){
                if(j==0){
                    sql= sql + "FK_TIPO= "+(String)request.getParameter("var3");
                    j++;
                }else{
                    sql= sql + " AND FK_TIPO= "+(String)request.getParameter("var3");
                }
            }
            if(request.getParameter("var4")!=null){
                if(j==0){
                    sql= sql + "Fk_estadoCIVIL= '"+(String)request.getParameter("var4")+"'";
                    j++;
                }else{
                    sql= sql + " AND FK_ESTADOCIVIL= '"+(String)request.getParameter("var4")+"'";
                }
            }
            if(request.getParameter("var5")!=null && !(request.getParameter("var5").equals(""))){
                if(j==0){
                    sql= sql + "RUT= '"+(String)request.getParameter("var5")+"'";
                    j++;
                }else{
                    sql= sql + " AND RUT= '"+(String)request.getParameter("var5")+"'";
                }
            }
            
            if(j==0){
                sql=sql0+ sql + "fSolicitud Between '"+(String)request.getParameter("fSolit")+"' AND '"+(String)request.getParameter("fSolit2")+"'";
             PreparedStatement buscar=conector.prepareStatement(sql);
            ResultSet rs=buscar.executeQuery();
                    System.out.println(sql);
             request.setAttribute("rslista", rs);
              this.getServletContext().getRequestDispatcher("/Listados.jsp").forward(request, response); 
            }else{
            
            sql= sql0+" "+sql + " AND fSolicitud Between '"+(String)request.getParameter("fSolit")+"' AND '"+(String)request.getParameter("fSolit2")+"'";
            System.out.println(sql);
            PreparedStatement buscar=conector.prepareStatement(sql);
            ResultSet rs=buscar.executeQuery();
                    
             request.setAttribute("rslista", rs);
              this.getServletContext().getRequestDispatcher("/Listados.jsp").forward(request, response); 
            }        
                   
        } catch (SQLException ex) {
            Logger.getLogger(ListarServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
