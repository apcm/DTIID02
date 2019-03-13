<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<head>
<title><spring:message code="area.list" /></title>
</head>
<spring:message code="area.name" var="name" />
<spring:message code="area.pictures" var="pictures" />
<spring:message code="area.brotherhoods" var="brotherhoods" />


<display:table name="area" id="row"
	requestURI="area/administrator/list.do" pagesize="5">
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="area/administrator/edit.do?areaId=${row.id}"> <spring:message
					code="area.edit"></spring:message>
			</a>
		</display:column>
	</security:authorize>

	<display:column property="name" title="${name}"></display:column>
	<display:column property="pictures" title="${pictures}"></display:column>
	<display:column title="${brotherhoods }">
		<jstl:forEach var="b" items="${row.brotherhoods}">
			<jstl:out value="${b.title}"></jstl:out>
		</jstl:forEach>
	</display:column>

</display:table>

<input type="submit" name="create"
	value="<spring:message code="area.create" />"
	onclick="javascript: relativeRedir('area/administrator/create.do');" />

