<%@page import="model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>  
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <%@ include file="/WEB-INF/jsp/comun/cabecera.jsp" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    
 <script>
  var agregarComentario=function(){      document.forms[0].submit();   } ;   
</script>
	     <h2>${recetasID.getNombrerect()}  </h2>      		  
      	 <br/>
	     <h3>Ingredientes</h3>  
	     
	      <c:set var="oracion" value="${recetasID.getIngredientes()}"/>
	         <div id="imagenReceta">
	         <img src="http://alumnos.iesvjp.es:16181/Imagenes/${recetasID.getImagen()}" width="100" height="100"/> 
	     </div>
	     
	   <ul id="lista">
	       <c:forTokens items="${oracion}" delims='<%="\n"%>' var="token" varStatus="i" >
		  <li>
		     <c:out value="${token}"/>
		  </li>  
		  </c:forTokens> 	 
	   </ul>
	      <br/><br/>
	       <h3>Preparaci&oacute;n</h3><br/>
		  <p><c:out value="${recetasID.getDescripcion()}"/></p>   <br/>

<form action="agregarComentario" method="post" >
      <c:set var="n" value="${sessionScope.usuario.nombreusuario}" />  
        <c:if test="${n==null || n=='anonimo'}">
         <input type="hidden" name="personaid" value="2" >  <!-- value='personaid?=2'> -->
        </c:if>
          <c:if test="${n!=null && n!='anonimo'}">
         <input type="hidden" name="personaid" value="${sessionScope.usuario.idusu}">
        </c:if>
          <input type="hidden" name="recetaid" value="${recetasID.getIdrect()}">
	<table>
		<tr>
			<td><textarea name="comentarios" rows="5" cols="40" >Escribir Comentario</textarea></td>
			<td><input type="button" value="Enviar"  onclick="agregarComentario()"></td>
		</tr>
	</table>
</form>

<h2>Comentarios</h2>

<div class="comentario">
     <c:forEach  var="b"  items="${comentarioT}" >
	<p> <span class="usuario">${b. getUsuario().nombreusuario}  </span> <br /> ${b.getDescripcoment()} &nbsp &nbsp  &nbsp
	<c:set var="u" value="${sessionScope.usuario.nombreusuario}" />
         <c:if test="${u!=null && u!='anonimo'}">
            <c:if test='${recetasID.getUsuario().getIdusu()==sessionScope.usuario.idusu}'>
	           <a href='${pageContext.request.contextPath}/action/admin/eliminarComent?id=${b.getIdcoment()}'>  Eliminar Comentario</a>
            </c:if>   
        </c:if>  

	 </p>
	<br /> <br />
	</c:forEach>
	
</div>

<br/>  
 
<%@ include file="/WEB-INF/jsp/comun/pie.jsp"%>    