<%@page import="model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/comun/cabecera.jsp" %>
<!-- pagina principal del usuario ingresado -->

   

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
     	
  
	 <c:forEach  var="b"  items="${recetasU}" >
	     		<h2> ${b.getNombrerect()}</h2>	 <br/>
	   <ul >
		  <li>
		  	
	       <h3>Resumen</h3><br/>
		    <c:out value=" ${b.getResumen()}"/> <br/>
		  <p><a href='mostrar?id=${b.getIdrect()}'>Seguir leyendo...</a> &nbsp; &nbsp;  &nbsp; &nbsp; <a href='eliminarReceta?idreceta=${b.getIdrect()}'>Eliminar</a></p> <br/><br/>
		
		  </li>
		   	 
	   </ul>
	 </c:forEach>
	    <br/>  
	
	   
         

	
<%@ include file="/WEB-INF/jsp/comun/pie.jsp"%>