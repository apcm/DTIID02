<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="${formAction}" modelAttribute="request">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<jstl:if test="${brotherhoodView==true}">
	</jstl:if>
	<jstl:if test="${memberView==true}">
	<form:hidden path="status"/>
	<jstl:out value="${errors }"/>
	<p style="color:red;"> <jstl:out value="${error }"/></p>
	
	<h2><form:label path="parade">
			<spring:message code="request.parade"/>:
		</form:label></h2>
		<form:select path="parade">
			<form:options items="${listParades}" itemLabel="title" itemValue="id"/>								 		
		</form:select>
		<form:errors cssClass="error" path="parade"/>
	
	<br/>
	<br/>
	</jstl:if>
	<jstl:if test="${brotherhoodView==true}">
	<form:hidden path="parade"/>
	
	<jstl:if test="${status=='APPROVED'}">
	<form:label path="rowPosition"><spring:message code="request.row"/></form:label>
	<form:input path="rowPosition" type="number" value="${row }"/>
	<form:errors cssClass="error" path="rowPosition"/>
		
	<form:label path="columnPosition"><spring:message code="request.column"/></form:label>
	<form:input path="columnPosition" type="number" value="${column}"/>
	
	<form:errors cssClass="error" path="columnPosition"/>
	</jstl:if>
	
		<jstl:if test="${status=='REJECTED'}">
	<form:label path="rejectReason"><spring:message code="request.reject.reason"/></form:label>
	<form:input path="rejectReason"/>
	<form:errors cssClass="error" path="rejectReason"/>
	</jstl:if>
	</jstl:if>
	
	<br/>
	<br/>
	<input type="submit" name="save"
			value="<spring:message code="request.save" />" />
			<input type="button" name="back" onclick="javascript: window.location.replace('${formBack}')"
		value="<spring:message code="request.back" />" />
		<br/>
	</form:form>
	
	