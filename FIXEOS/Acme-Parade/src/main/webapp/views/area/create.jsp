<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="area.create" /></title>
</head>
<body>
	<form:form modelAttribute="area" action="area/administrator/create.do">
		<!-- Name -->
		<acme:textbox code="area.name" path="name"/>
		<br>
		<br>

		<!-- Pictures -->
		<acme:textbox code="area.pictures" path="pictures"/>
		<spring:message code="area.comma"></spring:message>
		<br>
		<br>


		<input type="submit" name="saveCreate"
			value="<spring:message code="area.save" />" />
	</form:form>

	<input type="button" name="cancel"
		value="<spring:message code="area.cancel" />"
		onclick="javascript: relativeRedir('area/administrator/list.do');" />

</body>
</html>