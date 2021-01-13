<%-- 
    Document   : index
    Created on : 13-06-2020, 19:34:38
    Author     : Ninry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Soliicitudes</title>
         <style>
         
            
            form{
                margin: 0 auto;
                width: 600px;
                padding: 1em;
                border: 1px solid greenyellow;
                border-radius: 1em
                
                    
                    
            }
            label{
                display: inline-block;
                text-align: right;
                width: 120px;
                 
            }
            ul{
                list-style: none;
                padding: 0;
                background: #caefb3;
    }
            input,
            textarea{
                width: 150px;
                box-sizing: border-box;
                border: 1px solid;
             
            }
            form li + li {
                margin-top: 1em;
                background: #caefb3;
            }
            select{
                box-sizing: border-box;
            }
            div{
                height: 50px;
                 width: 100%;
                 background: graytext;
                 display: flex;
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
                .divboton{
                    margin: auto;
                     width: 150px;
                    border: 3px solid #73AD21;
                    padding: 10px;
                    text-align: center;
                    background: whitesmoke;
                    height: 30px;
                }
                fieldset{
                    background: #caefb3;
                }
        </style>
    </head>
    <body>
        
        <H1 align="center">Solicitudes de Salud </H1>
        
          <form action="SolicitudServlet" method="post">
            <fieldset>
             <legend align="right">Datos</legend>
            <ul>
                 <li>
                    <label for="tipo" >Tipo de Solicitud: </label>
                    <select id="tipo" name="var1">
                        <option value=" " disabled selected hidden>Seleccione tipo de Solicitud</option>
                        <option value="1">Solicitud de Presupuesto de Éxamenes</option>
                        <option value= "2"> Solicitud de Hora Médica</option>
                        <option value="3"> Solicitud de Licencia Médica</option>  
                        <option value="4"> Solicitud de Informe Médico</option>  
                    </select>  
                </li>  
                
                <li><label for="name">Nombres: </label>
                    <input required type="text" id="name" name="var2">   
                    <label for="rut">Rut: </label>
                    <input required type="text" id="rut" name="var5" pattern="[0-9]{7,8}-[0-9k]">
                </li>  
                
                <li>
                <li><label for="last1">Apellido Paterno: </label>
                    <input required type="text" id="last1" name="var3">   
                </li>
                <li><label for="last2">Apellido Materno: </label>
                    <input required type="text" id="last2" name="var4">   
                </li>
                 <li>
                    <label for="eCivil" >Estado Civil: </label>
                    <select id="eCivil" name="var7">
                        <option value=" " disabled selected hidden>Seleccione Estado Civil</option>
                        <option value="SO">Soltero(a)</option>
                        <option value= "CA">Casado(a)</option>
                        <option value="SE">Separado(a)</option>  
                        <option value="VI">Viudo(a)</option>  
                    </select>  
                </li> 
                <li><label for="fNac">Fecha nacimiento:</label> 
                    <input required type="date" id="fNac" name="var6" min="1910-01-01">
                 </li>
                 <li><label> ¿Donante? </label> 
                    <input class="don" required type="radio" id="donante" name="var8" value="S">
                    <label class="donLab" for="donante">Si</label>
                    
                     <input class="don" required type="radio" id="noDonante" name="var8" value="N">
                     <label class="donLab" for="noDonante" >No </label>
                 </li>
                <li><label for="obser"> Observaciones: </label> 
                    <textarea class="obser" id="obser" name="var10"></textarea>

                 </li>
                 
                <li>      
                    <button type="submit"> Solicitar </button>
                    <button type="reset"> Limpiar  </button>  
                </li>
               
            </ul>
           

            </fieldset>

        </form>
        <div class="divboton"><input class="inboton"  type="button" onclick="location.href='/ProyectoJSPBD_EV02_S032_JFEV/Home.jsp';" value="Volver" /></div>
        
    
    </body>
</html>
