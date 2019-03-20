<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>




	<h4>
		<spring:message code="chapter.edit.label.name" />:
	</h4>
	<jstl:out value="${chapter.name}"></jstl:out>
	
	<h4>
		<spring:message code="chapter.edit.label.middleName" />:
	</h4>
	<jstl:out value="${chapter.middleName}"></jstl:out>

	<h4>
		<spring:message code="chapter.edit.label.surname" />:
	</h4>
	<jstl:out value="${chapter.surname}"></jstl:out>
	
	<h4>
		<spring:message code="chapter.edit.label.address" />:
	</h4>
	<jstl:out value="${chapter.address}"></jstl:out>
	
	<h4>
		<spring:message code="chapter.edit.label.email" />:
	</h4>
	<jstl:out value="${chapter.email}"></jstl:out>
	
	<h4>
		<spring:message code="chapter.edit.label.phoneNumber" />:
	</h4>
	<jstl:out value="${chapter.phoneNumber}"></jstl:out>

	<h4>
		<spring:message code="chapter.edit.label.username" />:
	</h4>
	<jstl:out value="${chapter.userAccount.username}"></jstl:out>
	
	<h4>
		<spring:message code="chapter.title" />:
	</h4>
	<jstl:out value="${chapter.title}"></jstl:out>

	<h4>
		<spring:message code="chapter.area" />:
	</h4>
	<jstl:out value="${chapter.area.name}"></jstl:out>

	
<br/><br/>

	<spring:message code="chapter.export.explanation" var="exportExplanation"/>
	<jstl:out value="${exportExplanation}"/>
	
	<a href="chapter/chapter/edit.do"> Link </a>
	
	<br/>
