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
	
	<h2>
		<spring:message code="request.parade" />
	</h2>
	<p><jstl:out value="${parade.title}"/></p>
	
	<h2>
		<spring:message code="request.parade.description" />
	</h2>
	<p><jstl:out value="${parade.description}"/></p>
	
	<h2>
		<spring:message code="request.departureDate" />
	</h2>
	<p><jstl:out value="${parade.departureDate}"/></p>
	
	<h2>
		<spring:message code="request.parade.ticker" />
	</h2>
	<p><jstl:out value="${parade.ticker}"/></p>
	
	<h2>
		<spring:message code="request.status" />
	</h2>
	<p><jstl:out value="${request.status}"/></p>
	
	<jstl:if test="${request.rowPosition!=null }">
	<h2>
		<spring:message code="request.rowPosition" />
	</h2>
	<p><jstl:out value="${request.rowPosition}"/></p>
	</jstl:if>
	
	<jstl:if test="${request.columnPosition!=null }">
	<h2>
		<spring:message code="request.columnPosition" />
	</h2>
	<p><jstl:out value="${request.columnPosition}"/></p>
	</jstl:if>
	
	<jstl:if test="${request.rejectReason!=''}">
	<h2>
		<spring:message code="request.rejectReason" />
	</h2>
	<p><jstl:out value="${request.rejectReason}"/></p>
	</jstl:if>
	
	
	<br/>
	<form:form action="requests/member/show.do" modelAttribute="request">
	<form:hidden path="id"/>
	<jstl:if test="${request.status=='APPROVED' }">
	<input type="submit" name="delete"
			value="<spring:message code="request.delete" />"
			onclick="return confirm('<spring:message code="message.confirm.delete" />')" />
	</jstl:if>
	</form:form>
	
	<input type="button" name="back" onclick="javascript: window.location.replace('requests/member/list.do')"
		value="<spring:message code="request.back" />" />
	
</security:authorize>