<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 <%@ include file="/WEB-INF/jsp/comun/cabecera.jsp" %>

<script src="${contextPath}/js/sha1.js"></script>  <!--  -->


<!-- Comienzo de inicio, esta parte cambia -->  
			<div class="errores">
				<c:forEach var="e" items="${errores}">
			           <h3>${e}</h3><br> 
				</c:forEach>
			</div>
			
      <br/><br/>
       <h1>BIENVENIDOS AL BLOG</h1> <br/>
      <p id="parrafo">Este blog esta Pensado para las personas que quieran dar a conocer recetas nuevas y facil de preparar </p>
            

	 <c:forEach  var="b"  items="${recetas}" >
	     		<h2> ${b.getNombrerect()}</h2>
	   <ul>
		  <li>
		  	
	       <h3>Resumen</h3>
		   <p id="resumen"><c:out value=" ${b.getResumen()}"/></p>
		  <p><a href='mostrar?id=${b.getIdrect()} '>Seguir leyendo....</a></p> <br/><br/>
		
		  </li>
		   	 
	   </ul>
	 </c:forEach>
<!-- Fin de esta parte -->                  
<%@ include file="/WEB-INF/jsp/comun/pie.jsp"%>