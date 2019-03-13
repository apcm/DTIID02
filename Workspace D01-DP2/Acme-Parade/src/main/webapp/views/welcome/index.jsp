<%--
 * index.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<jstl:if test="${idioma == 'en'}">
		<jstl:out value="${customisation.welcomeMessage[0]}"></jstl:out>
	</jstl:if>
	
	<jstl:if test="${idioma == 'es'}">
		<jstl:out value="${customisation.welcomeMessage[1]}"></jstl:out>
	</jstl:if>

<p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> 