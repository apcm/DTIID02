<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('BROTHERHOOD')">
	<h2>
		<spring:message code="member.name" />
	</h2>
	<p><jstl:out value="${member.name}"/></p>
	
	<h2>
		<spring:message code="member.surname" />
	</h2>
	<p><jstl:out value="${member.surname}"/></p>
	
	<h2>
		<spring:message code="member.email" />
	</h2>
	<jstl:out value="${member.email}"/>
	
	<h2>
		<spring:message code="member.phoneNumber" />
	</h2>
	<jstl:out value="${member.phoneNumber}"/>
	
	<h2>
		<spring:message code="member.address" />
	</h2>
	<jstl:out value="${member.address}"/>
	
	<h2>
		<spring:message code="member.email" />
	</h2>
	<jstl:out value="${member.email}"/>
	
	<h2>
		<spring:message code="enrolement.enrolMoment" />
	</h2>
	<jstl:out value="${enrolement.enrolMoment}"/>
	
	<br/>
	<br/>
	<input type="button" name="back" onclick="javascript: window.location.replace('enrolements/brotherhood/list.do')"
		value="<spring:message code="member.back" />" />
</security:authorize>