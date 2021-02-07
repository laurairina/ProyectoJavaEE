<%@page import="model.Usuario"%>
<%@page import="model.Receta"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
  <c:set var="contextPath" value="${pageContext.request.contextPath}"/>  <!-- Nombre o contexto de la aplicación -->
<!DOCTYPE html >
<html>
<head>
<meta  charset=UTF-8">
 <title>BLOG</title>
  <link rel="stylesheet" href='<%= getServletContext().getContextPath() %>/css/Menublogcss.css'  media="all"/>
    </head>    
    <body>
        <!-- Contenedor -->
        <div id="contenedor">
                <!-- Cabecera -->
                <div id="cabecera">
                         <div id="logo">
                            <img src='<%= getServletContext().getContextPath() %>/css/LogoCabecera.jpg'  height=100px atl="" />
                         </div>
                        <div class="clear"></div>
                </div>
                <!-- /Cabecera -->

                <!-- Menu principal -->
                <div id="menu">
                        <ul id="menu_principal">
                                 <li><a href='${contextPath}/action/usuario/'>Inicio</a></li> 
                                 
                                  <c:set  var="u"  value="${usuario.nombreusuario}" />
                                  <c:if test="${u==null || u=='anonimo'}">
                                   <li><a href='${contextPath}/action/usuario/iriniciarsesion'>Iniciar Sesi&oacute;n</a></li> 
                                  <li><a href='${contextPath}/action/usuario/irRegistrate'>Registrarte</a></li>
                                      <li><a>${sessionScope.usuario.nombreusuario}</a></li>
                                  </c:if>
                                  
                                     <c:set  var="o"  value="${usuario.nombreusuario}" />
                                     <c:if test="${o!=null && o!='anonimo'}">
                                        <li><a href='${pageContext.request.contextPath}/action/admin/'>P&aacute;gina de Inicio usuario</a></li>
                                         <li><a href='${pageContext.request.contextPath}/action/admin/ingresarRecetas?personaid=${usuario.idusu}'>A&ntilde;adir Recetas</a></li>
                                        <li><a href='${pageContext.request.contextPath}/action/usuario/cerrar'>Salir</a></li>
                                            <li><a>${sessionScope.usuario.nombreusuario}</a></li>
                                      </c:if>
                        </ul>
                        <div class="clear"></div>
                </div>


                <!-- Lateral -->
                <div id="lateral">

                        <!-- Noticias -->
                        <div id="noticias"> 
                                
                                <h3>Recetas subidas</h3> <br/> 
                                  <c:forEach  var="b"  items="${recetas}">
	     		                   <p><span class="fecha"> ${b.getFechapubli()} </span><br/> <a href='mostrar?id=${b.getIdrect()}'>  ${b.getNombrerect()} </a></p> <br/> 
	     		                   
	     		                 </c:forEach>  
                         
                        </div>
                        <!-- /Noticias -->

                        <!-- Publicidad -->
                        <div id="publicidad">
                              <img src='<%= getServletContext().getContextPath() %>/css/cocinando.gif' width=150px height=200px border=0 usemap="#deinteres">       
                        </div>
                        <!-- /Publicidad -->

                </div>
                <!-- /Lateral -->
                
              <div id="contenido">  
              
                   <!-- Principal -->
                 <div id="principal">       
                             
                              
            <div class="articulo">
           