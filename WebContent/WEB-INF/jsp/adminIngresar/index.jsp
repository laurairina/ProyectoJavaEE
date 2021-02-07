

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ include file="/WEB-INF/jsp/comun/cabecera.jsp"%>
<%@ page import="java.util.*" %>
<script>
         var agregarReceta=function(){              document.forms[0].submit();      };		   	
 </script>

<!-- Comienzo de inicio, esta parte cambia -->
<br />  <br />
<h2>Ingresar Receta</h2>
<form action="agregarReceta" method="post" name="agregar" >
	<table>
		<tr>
			<td>Nombre de la receta</td>
			<td><input type="text" name="nomReceta"></td>
		</tr>
		<tr>
			<td>Escoger categoria</td>
			<td><select name="nomCategoria">
					<option>Primero</option>
					<option>Postre</option>
			</select></td>
		</tr>
		<tr>
			<td>Resumen</td>
			<td><textarea name="nomResumen" rows="5" cols="40">  </textarea></td>
		</tr>

		<tr>
			<td>Ingredientes</td>
			<td><textarea name="nomIngredientes" rows="5" cols="40"></textarea></td>
		</tr>

		<tr>
			<td>Descripcion de la Preparacion</td>
			<td><textarea name="nomDescipcion" rows="5" cols="40"> </textarea></td>
		</tr>

		<tr>
			<td>Imagen</td>
			<td> 
			<input type="file" id="files" name="files" value="" multiple /> 
			<output	id="list"></output>
		    <input type="hidden"  id="ruta" name="ruta" type="text"/> 
			  <script>
             
              function archivo(evt) {
                  var files = evt.target.files; // Lista de objetos
             
                  // Se obtiene la imagen del campo file
                  for (var i = 0, f; f = files[i]; i++) {
                    //Solo admite imagenes
                    if (!f.type.match('image.*')) {
                        continue;
                    }
                  
                    var reader = new FileReader();
                
                    reader.onload = (function(theFile) {
                    	
                        return function(e) {
                          // Insertamos la imagen
                          document.getElementById("ruta").value= e.target.result;
                         document.getElementById("list").innerHTML = ['<img  src="', e.target.result,'" title="', escape(theFile.name), '"/>'].join('');       
                        };
                    })(f);
             
                    reader.readAsDataURL(f);
                  }
              }
              
              document.getElementById('files').addEventListener('change', archivo, false);
      </script>  

			</td>
 
		</tr>

		<tr>
			<td><input type="hidden" name="personaid"
				value="${sessionScope.usuario.idusu}"></td>
		</tr>

		<tr>
			<td colspan="2"><input type="button" value="Guardar Receta"
				onclick="agregarReceta()"></td>
		</tr>
	</table>
</form>
<!-- Fin de esta parte -->
<%@ include file="/WEB-INF/jsp/comun/pie.jsp"%>