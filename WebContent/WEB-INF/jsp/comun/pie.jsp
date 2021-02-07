<%@page import="model.Usuario"%>
<%@page import="model.Receta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
               </div> 
                </div> 
                <!-- /Principal -->
   
                <!-- Secundario-->
                <div id="secundario">
                        <h1>Postres</h1> 
                          <c:forEach  var="b"  items="${recetasP}" >
	     		              <h2>  ${b.getNombrerect()}</h2>
	    
	     		              <p id="box"> 
	     		              ${b.getResumen() }
	     		              </p>
	     		                 <p><a href='mostrar?id=${b.getIdrect()}'>Seguir leyendo...</a></p>
	     		          </c:forEach> 
	     		                       
                </div>
                <!-- /Secundario -->   

                </div>
                <!-- /Contenido -->
                
   <!-- Pie -->
                <div id="pie">
                        <span class="enlaces">
                                <a href="http://eladerezo.hola.com/">El aderezo</a> |
                                <a href="http://www.hogarutil.com/cocina/">Hogar util</a> |
                                <a href="http://www.saludalacarta.com/">Salud</a> |
                               
                        </span>

                        <span class="copyright">
                                &copy; Copyright LauraIMendoza
                        </span>
                        <div class="clear"></div>
                </div>
                <!-- /Pie -->

        </div>
        <!-- /Contenedor -->

        </body>
</html>
