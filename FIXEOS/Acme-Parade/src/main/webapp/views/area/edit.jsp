<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<head>
<title><spring:message code="area.edit" /></title>
</head>

<body>

	<form:form modelAttribute="area" action="area/administrator/edit.do">
		<form:hidden path="id" />
		<form:hidden path="version" />

		<!-- Name -->
		<acme:textbox code="area.name" path="name"/>
		<br>
		<br>
		

		<!-- Pictures -->
		<acme:textbox code="area.pictures" path="pictures"/>
		<spring:message code="area.comma"></spring:message>
		<br>
		<br>
		
		

		<input type="submit" name="saveEdit"
			value="<spring:message code="area.save" />" />

		<input type="submit" name="delete"
			value="<spring:message code="area.delete" />" />

	</form:form>
	
	<input type="submit" name="cancel"
			value="<spring:message code="area.cancel" />"
			onclick="javascript: relativeRedir('area/administrator/list.do');" />

</body>
