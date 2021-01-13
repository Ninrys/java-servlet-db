<%-- 
    Document   : Modificar
    Created on : 15-06-2020, 23:25:46
    Author     : Ninry
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estiles/sheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Información</title>
        <style>
            
            input.don{
                width: 5px;
            }
            label.donLab{
                width: 10px;
                display: inline-block;
            }
            
        </style>
            
    </head>
    <body>
        <%  ResultSet rs = (ResultSet) request.getAttribute("rsupdate");
            rs.next();
            String tipo="";
            if (rs.getString("FK_TIPO").equals("1")) {
                tipo = "Solicitud de Presupuesto de Exámenes";
            }
            if (rs.getString("FK_TIPO").equals("2")) {
                tipo = "Solicitud de Hora Médica";
            }
            if (rs.getString("FK_TIPO").equals("3")) {
                tipo = "Solicitud de Licencia Médica";
            }
            if (rs.getString("FK_TIPO").equals("4")) {
                tipo = "Solicitud de Informe Médico";
            }
            

             String mensaje=rs.getString("Observacion");
%>

        <form action="EditarServlet" method="POST">
            <ul class="form-style-1">
                <input type="text"  name="var8" value="<%=rs.getString("ID_solicitud")%>" hidden >
                <li><label>Nombre</label><input type="text" name="var1" class="field-divided" required value="<%=rs.getString("Nombres")%>" /> 

                <li>
                    <label>Apellidos</label>        
                    <input type="text" name="var2" class="field-divided" value="<%=rs.getString("Apellido_Paterno")%>" required />
                    <input type="text" name="var3" class="field-divided" value="<%=rs.getString("Apellido_Materno")%>" required />

                </li>
                <li>
                    <label >Rut   </label>                  
                    <input type="text" name="rut" class="field-divided" value="<%=rs.getString("RUT")%>" disabled />
                    <label > Fecha de nacimiento</label>
                    <input type="date" name="var4" class="field-divided" value="<%=rs.getString("Fnacimiento")%>"  />
                </li>
                <li>
                    <label>Tipo y fecha de Solicitud</label>
                    <input type="text" name="tipoSol" class="field-divided" value="<%=tipo%>" disabled /> 
                    <input type="text" name="fechaSol" class="field-divided" placeholder=<%=rs.getString("Fsolicitud")%> disabled />    

                </li>
                <li><label>Estado Civil</label>

                    <select id="eCivil" name="var5">
                        <option <%= ( rs.getString("FK_estadocivil").equals("SO"))? "selected ": "" %>value="SO">Soltero(a)</option>
                        <option <%= ( rs.getString("FK_estadocivil").equals("CA"))? "selected ": "" %> value= "CA">Casado(a)</option>
                        <option <%= ( rs.getString("FK_estadocivil").equals("SE"))? "selected ": "" %> value="SE">Separado(a)</option>  
                        <option <%= ( rs.getString("FK_estadocivil").equals("VI"))? "selected ": "" %>value="VI">Viudo(a)</option>  </select>


                </li> 
                <li>
                <li><label  > ¿Donante? </label> 
                    <input class="don" <%= ( rs.getString("Cdonante").equals("S"))? "checked ": "" %> required type="radio" id="donante" name="var6" value="S">
                    <label class="donLab" for="donante">Si</label>
                    <input class="don" <%= ( rs.getString("Cdonante").equals("N"))? "checked ": "" %> required type="radio" id="noDonante" name="var6" value="N" >
                    <label class="donLab" for="noDonante" >No </label>
                </li>

                <li>
                    <label>Observaciones <span class="required">*</span></label>
                    <textarea name="var7" id="field5" class="field-long field-textarea"><%=mensaje %></textarea>
                </li>
                <li>
                    <input type="submit" value="Editar" />
                </li>
            </ul>
        </form>
                <input type="button" value="volver al inicio" onclick="location.href = '/ProyectoJSPBD_EV02_S032_JFEV/Home.jsp'"/>
                 <input type="button" value="volver" onclick="location.href = 'ListarServlet'"/>
    </body>
</html>
