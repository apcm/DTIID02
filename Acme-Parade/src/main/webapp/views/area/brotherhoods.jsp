<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="brotherhoods">
	<display:column>
		<jstl:forEach items="${area.brotherhoods}" var="brotherhoods">
			<jstl:out value="${brotherhoods}"></jstl:out>
		</jstl:forEach>
	</display:column>
	
	
</display:table>