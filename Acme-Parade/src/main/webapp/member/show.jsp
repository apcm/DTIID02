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
		<spring:message code="brotherhood.edit.label.name" />:
	</h4>
	<jstl:out value="${member.name}"></jstl:out>
	
	<h4>
		<spring:message code="brotherhood.edit.label.middleName" />:
	</h4>
	<jstl:out value="${member.middleName}"></jstl:out>

	<h4>
		<spring:message code="brotherhood.edit.label.surname" />:
	</h4>
	<jstl:out value="${member.surname}"></jstl:out>
	
	<h4>
		<spring:message code="brotherhood.edit.label.address" />:
	</h4>
	<jstl:out value="${member.address}"></jstl:out>
	
	<h4>
		<spring:message code="brotherhood.edit.label.email" />:
	</h4>
	<jstl:out value="${member.email}"></jstl:out>
	
	<h4>
		<spring:message code="brotherhood.edit.label.phoneNumber" />:
	</h4>
	<jstl:out value="${member.phoneNumber}"></jstl:out>

	<h4>
		<spring:message code="brotherhood.edit.label.username" />:
	</h4>
	<jstl:out value="${member.userAccount.username}"></jstl:out>
	
<br/>
<br/>
	<spring:message code="member.export.explanation" var="exportExplanation"/>
	<jstl:out value="${exportExplanation}"/>
	
	<a href="member/member/edit.do"> Link </a>
	
	
	<br/>
	<br/>
	<br/>

	<input type="button" name="back" onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="brotherhood.back" />" />
	