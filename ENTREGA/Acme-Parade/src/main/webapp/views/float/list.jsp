<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<spring:message code="float.title" var="title" />
<spring:message code="float.description" var="description" />
<spring:message code="float.brotherhood" var="brotherhood" />
<spring:message code="float.pictures" var="pictures" />

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="floats" requestURI="${requestURI}" id="row">
	
	<display:column property="brotherhood.title" title="${brotherhood}"></display:column>
	<display:column property="title" title="${title}"></display:column>
	<display:column property="description" title="${description}"></display:column>

	<security:authorize access="hasRole('BROTHERHOOD')">
	<display:column>
		<a href="float/brotherhood/show.do?floatId=${row.id}"> <spring:message
				code="float.show"></spring:message></a>
	</display:column>

		<display:column>
			<a href="float/brotherhood/edit.do?floatId=${row.id}"> <spring:message
					code="float.edit"></spring:message>
			</a>
		</display:column>
	</security:authorize>
</display:table>

<security:authorize access="hasRole('BROTHERHOOD')">
<input type="submit" name="create"
	value="<spring:message code="float.create" />"
	onclick="javascript: relativeRedir('float/brotherhood/create.do');" />
</security:authorize>
