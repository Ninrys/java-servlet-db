<%-- 
    Document   : Listados
    Created on : 13-06-2020, 20:34:37
    Author     : Ninry
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Solicitudes</title>
        <style>
            form.xd{
                margin: 0 auto;
                width: 600px;
                padding: 1em;
                border: 1px solid #CCC;
                border-radius: 1em;
                background-color: #d3e29d;



            }
            label{
                display: inline-block;
                text-align: right;
                width: 125px;

            }
            ul{
                list-style: none;
                padding: 0;
            }
            input,
            textarea{
                width: 150px;
                box-sizing: border-box;
                border: 1px solid;

            }
            form li + li {
                margin-top: 1em;
            }
            select{
                box-sizing: border-box;
            }
            div{
                height: 50px;
                width: 100%;
                

                flex-direction: column;
                justify-content: center;
                text-align: center;
            }
            input.don{
                width: 5px;
            }
            label.donLab{
                width: 10px;
            }
            textarea.obser{
                width: 236px;
            } 
            .nope{
               
                background:linear-gradient(to bottom, #8ab44c 5%, #acc864 100%);
                background-color:#8ab44c;
                border:1px solid #4b8f29;
                display:inline-block;
                cursor:pointer;
                color:#000000;
                padding:6px 12px;
                text-decoration:none;
                
                width: 10%;
            }
            table{
                background-color: #d3e3b6;
                border: 2px solid #072a16;
              

            }

        </style>
    </head>
    <body>
        <form class="xd" action="ListarServlet" method="POST">
            <h3 align="center"> Buscar  </h3>
            <ul >
                <li><label for="date">Fecha de nacimiento</label><input type="date" name="var1" id="date"/> </li>
                <li>   <label for="rango">Rango de Solicitud </label> <input required type="date" value="1910-01-01" name="fSolit" id="rango" />  -  <input required type="date" name="fSolit2" value="2050-12-30" /></li>
                <li>

                    <label for="donante"> ¿Donante? </label> 
                    <input class="don" type="radio" id="donante" name="var2" value="S">
                    <label class="donLab" for="donante">Si</label>

                    <input class="don"  type="radio" id="noDonante" name="var2" value="N">
                    <label class="donLab" for="noDonante" >No </label>
                    <%--   <input class="don" type="radio" id="noSabe" name="var2" value="">
                       <label class="donLab" for="noSabe" >N/S</label>  --%>
                </li>
                <li>
                    <label for="tipo" >Tipo de Solicitud: </label>
                    <select id="tipo" name="var3">
                        <option value=" " disabled selected hidden>Seleccione tipo de Solicitud</option>
                        <option value="1">Solicitud de Presupuesto de Éxamenes</option>
                        <option value= "2"> Solicitud de Hora Médica</option>
                        <option value="3"> Solicitud de Licencia Médica</option>  
                        <option value="4"> Solicitud de Informe Médico</option>  
                    </select>  

                </li>
                <li>
                    <label for="eCivil" >Estado Civil: </label>
                    <select id="eCivil" name="var4">
                        <option value=" " disabled selected hidden>Seleccione Estado Civil</option>
                        <option value="SO">Soltero(a)</option>
                        <option value= "CA">Casado(a)</option>
                        <option value="SE">Separado(a)</option>  
                        <option value="VI">Viudo(a)</option>  
                    </select>   </li>
                <li>   <label for="rut">Rut: </label>
                    <input type="text" id="rut" name="var5" pattern="[0-9]{7,8}-[0-9k]"> </li>

                <li>
                    <input class="nope" type="submit" value="Buscar" />
                </li>
            </ul>
        </form>
                <h1>                         </h1>
        <form action="Eliminar" method="get">
            <%
                ResultSet rs = (ResultSet) request.getAttribute("rslista");

                out.print(" <table align='center' border='2'><tr><td></td><td>Id Solicitud</td> <td>Nombre</td><td>RUT</td><td>Edad</td><td>Donante</td><td>Tipo de Solicitud</td><td>Estado Civil</td><td>Observaciones</td></tr>");

                while (rs.next()) {
                    String fecha = rs.getString("FNACIMIENTO").substring(0, 4);
                    int age = 0;
                    String donador;
                    if (rs.getString("CDONANTE").equals("S")) {
                        donador = "Si";
                    } else {
                        donador = "No";
                    }

                    try {
                        int year = Integer.parseInt(fecha);
                        age = 2020 - year;

                    } catch (Exception e) {
                        e.toString();
                    }

                    // for (int i = 0; i < 10; i++) {
                    out.println("<tr>");
                    out.println("<td><input type='radio' name='borrar' value='" + rs.getString("ID_SOLICITUD") + "'></td>");
                    out.println("<td>" + rs.getString("ID_SOLICITUD") + "</td>");
                    out.println("<td>" + rs.getString("NOMBRES") + " " + rs.getString("APELLIDO_PATERNO") + " " + rs.getString("APELLIDO_MATERNO") + "</td>");
                    out.println("<td>" + rs.getString("RUT") + "</td>");
                    out.println("<td>" + age + "</td>");
                    out.println("<td>" + donador + "</td>");
                    out.println("<td>" + rs.getString("TIPOSOLICITUD") + "</td>");
                    out.println("<td>" + rs.getString("ESTADOCIVIL") + "</td>");
                    out.println("<td>" + rs.getString("OBSERVACION") + "</td>");

                    out.println("</tr>");
                }

                out.println("</table>");
            %>
            <h1>  </h1>
                
            <div align="center">
                <input class="nope" type="button" value="volver" onclick="location.href = '/ProyectoJSPBD_EV02_S032_JFEV/Home.jsp'"/> 
                <button class="nope" type="submit" name="eliminar">Eliminar</button>
                <button class="nope" type="submit" name="editar" formaction="EditarServlet">Editar</button>

            </div>        
        </form>   

    </body>
</html>
