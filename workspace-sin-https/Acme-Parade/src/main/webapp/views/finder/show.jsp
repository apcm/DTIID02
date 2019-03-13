<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('MEMBER')">
	
	
	<spring:message code="finder.moment"/>
	<jstl:out value="${finder.moment}"/>
	<br/>
	
	<spring:message code="finder.keyword"/>
	<jstl:out value="${finder.keyword}"/>
	<br/>
	
	<spring:message code="finder.area"/>
	<jstl:out value="${finder.area.name}"/>
	<br/>
	
	<spring:message code="finder.startDate"/>
	<jstl:out value="${finder.startDate}"/>
	<br/>
	
	<spring:message code="finder.endDate"/>
	<jstl:out value="${finder.endDate}"/>
	<br/>
	
	<h3><spring:message code="finder.results"/></h3>
	<br/>
	<jstl:forEach var = "result" items="${finder.processions}">
		<jstl:out value="${result.title}"/>
		<br/>
	</jstl:forEach>
	<br/>
	
	<a href="finder/member/edit.do"><spring:message code="finder.edit"/></a>
</security:authorize>