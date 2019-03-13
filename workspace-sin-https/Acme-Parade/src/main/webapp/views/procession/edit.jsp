<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="procession/brotherhood/edit.do" modelAttribute="procession">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	
	<spring:message code="procession.ob" var="ob"/>
	<jstl:out value="${ob}"/>
	<form:label path="title">
		<spring:message code="procession.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />

	<form:label path="description">
		<spring:message code="procession.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />

	<form:label path="departureDate">
		<spring:message code="procession.departureDate" />:
	</form:label>
	<form:input path="departureDate" />
	<form:errors cssClass="error" path="departureDate" />
	<br />	
	
	<form:label path="finalMode">
		<spring:message code="procession.finalMode" />:
	</form:label>
	<form:select path="finalMode">
		<form:option value="true"></form:option>
		<form:option value="false"></form:option>
		
	</form:select>
	<br />	
	<br />	
	
	<form:label path="floats">
		<spring:message code="procession.float"/>
	</form:label>
	<form:select path="floats" multiple="true">	
		<form:options items="${floats}" itemLabel="title"
			/>
	</form:select>

	
	<input type="submit" name="save"
		value="<spring:message code="procession.save" />" />&nbsp; 
		
		
	<jstl:if test="${procession.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="procession.delete" />"
			onclick="return confirm('<spring:message code="procession.confirm.delete" />')" />&nbsp;
	</jstl:if>
	
	<input type="button" name="cancel"
		value="<spring:message code="procession.cancel" />"
		onclick="javascript: relativeRedir('procession/brotherhood/list.do');" />
	<br />


</form:form>
