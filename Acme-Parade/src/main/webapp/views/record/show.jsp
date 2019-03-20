<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="view" tagdir="/WEB-INF/tags"%>


<spring:message code="float.brotherhood" var="brotherhood.title" />
<spring:message code="float.title" var="title" />
<spring:message code="float.description" var="description" />
<spring:message code="float.pictures" var="pictures" />


	<h4>
		<spring:message code="float.title" />:
	</h4>
	<jstl:out value="${f.title}"></jstl:out>

	<h4>
		<spring:message code="float.description" />:
	</h4>
	<jstl:out value="${f.description}"></jstl:out>
	
	<h4>
		<spring:message code="float.pictures" />:
	</h4>
	<jstl:out value="${f.pictures}"></jstl:out>
	
	<h4>
		<spring:message code="float.brotherhood" />:
	</h4>
	<jstl:out value="${f.brotherhood.name}"></jstl:out>
		
<br/>
<br/>

	<input type="button" name="back" onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="brotherhood.back" />" />

