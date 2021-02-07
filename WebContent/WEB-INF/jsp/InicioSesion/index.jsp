<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
      
<%@ include file="/WEB-INF/jsp/comun/cabecera.jsp" %>

<script src="${contextPath}/js/sha1.js"></script> 
	<script>
		var ingresar = function() {
			// Calculando HASH de la clave
			var claveLimpia = document.getElementById("claveLimpia").value;
			var hash = CryptoJS.SHA1(claveLimpia);
			document.getElementById("clave").value = hash;
			
			document.forms[0].submit();
		};
	</script>

<!-- Comienzo de inicio, esta parte cambia -->
    
                         <br/><br/>
                        <h2>Iniciar Sesi&oacute;n</h2> <br/>
                        <form action="autenticar"  method="post">  <!-- El formulario envia a autenticar -->
	                         <table>
		                     	<tr>
			                     	<td>Usuario</td>
				                    <td><input type="text" name="nomusuario"></td>
		                    	</tr>		
		                    	<tr>
			                    	<td>Contraseña</td>
			                       	<td>
					                    <input type="hidden" id="clave" name="clave">
					                  <input type="password" id="claveLimpia">  
				                    </td>
		                       	</tr>		
			                    <tr>
			                      	<td colspan="2" >  <!-- El onlclick su ingresar es una funcion que esta arriba y la contraseña lo encripta -->
				                    <input type="button" value="Ingresar" onclick="ingresar()">  
			                    	</td>
			                   </tr>		
		                   </table>
                     </form>
<!-- Fin de esta parte -->                  
<%@ include file="/WEB-INF/jsp/comun/pie.jsp"%>