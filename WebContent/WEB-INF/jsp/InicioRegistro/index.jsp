<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>  <!-- El nombre de la aplicación web (la ruta del contexto)  -->

<%@ include file="/WEB-INF/jsp/comun/cabecera.jsp" %> <!-- incluye el contenido de la cabecera.jsp -->


<script src="${contextPath}/js/sha1.js"></script>  <!--Este codigo esta guardo en un archivo .js, desde aqui lo lee -->
	<script>
		 function darAlta() {
			
		    //primero comprobamos que ambas claves son iguales 
		   	if (document.agregar.clave.value == document.agregar.claveRepetida.value ) {
		   	
		   	// Calculando HASH de la clave
				var claveLimpia = document.getElementById("clave").value;
				var hash = CryptoJS.SHA1(claveLimpia);  //Aqui encripta la clave con el codigo del archivo sha1.js
				
				document.getElementById("clave").value = hash;
			
				//envia los datos con la clave ya encriptada para guardarlo
				document.agregar.submit();  
		   	}
		   	else{
		   		alert("Contraseñas no son iguales");
		   	}
		   	
			
		};
	</script>



<!-- Comienzo de inicio, esta parte cambia -->
    
                      <br/><br/>
                            	<h2>Darse de alta</h2>
                          	<form action="agregar" method="post" name="agregar">
	                        	<table>
		                        	<tr>
			                        	<td>Usuario</td>
			                          	<td><input type="text" name="nomusuario"></td>
		                        	</tr>		
			                       <tr>
				                       <td>Contrase&ntilde;a</td>
				                       <td><input type="password" id="clave"  name="clave">
				                           <input type="hidden" id="claveLimpia" >
				                        </td>  
			                       </tr>		
			                       <tr>
				                       <td>Repita contrase&ntilde;a</td>
				                       <td><input type="password" id="claveRepetida" name="claveRepetida">
				                          
				                       </td>
				                        
		                        	</tr>		
		                        	<tr>
			                         	<td colspan="2">
					                       <input type="button" value="Dar de alta"   onclick="darAlta()">  <!-- Envial javascript todos los datos -->
			                    	   </td>
			                       </tr>		
	                        	</table>
                        	</form>
                       
<!-- Fin de esta parte -->                  
<%@ include file="/WEB-INF/jsp/comun/pie.jsp"%>  <!-- incluye el contenido del pie.jsp -->
