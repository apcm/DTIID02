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
<title><spring:message code="periodRecord.create" /></title>
</head>
<body>
	<form:form modelAttribute="periodRecord" action="periodrecord/brotherhood/edit.do">

		<form:hidden path="id" />
		<form:hidden path="version" />

		<acme:textbox code="record.title" path="title"/>
		<br>
		<br>

		<acme:textbox code="record.description" path="description"/>
		<br>
		<br>

		<acme:textbox code="record.startYear" path="startYear"/>
		<br>
		<br>

		<acme:textbox code="record.endYear" path="endYear"/>
		<br>
		<br>

		<acme:textbox code="record.photos" path="photos"/>
		<br>
		<br>

	<spring:message code="record.mandatory" />
		<br>

		<input type="submit" name="save"
			value="<spring:message code="record.save" />" />
	<jstl:if test="${legalRecord.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="record.delete" />"
			onclick="return confirm('<spring:message code="record.confirm.delete" />')" />&nbsp;
	</jstl:if>



	<input type="button" name="cancel"
		value="<spring:message code="record.cancel" />"
		onclick="javascript: relativeRedir('brotherhood/showRecords.do');" />
</form:form>

</body>
</html>