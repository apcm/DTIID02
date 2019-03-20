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
	
	<form:form action="finder/member/edit.do" modelAttribute="finder">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		
		<form:label path="keyword">
			<spring:message code="finder.keyword"/>
		</form:label>
		<form:input path="keyword"/>
		<form:errors cssClass ="error" path ="keyword"/>
		<br />
		
		<form:label path="area">
			<spring:message code="finder.areas"/>
		</form:label>
		<form:select id="areas" multiple="false" path="area">
			<form:option value="0" label="------"/>
			<form:options items ="${areas}" itemLabel="name"/>
		</form:select>
		<br/>
		
		<form:label path="startDate">
			<spring:message code="finder.startDate"/>
		</form:label>
		<form:input path="startDate"/>
		<form:errors cssClass ="error" path="startDate"/>
		<br/>
		
		<form:label path="endDate">
			<spring:message code="finder.endDate"/>
		</form:label>
		<form:input path="endDate"/>
		<form:errors cssClass="error" path="endDate"/>
		<br/>
		
		<input type="button" name="cancel" value="<spring:message code="finder.cancel"/>" onclick ="javascript: relativeRedir('finder/member/show.do');"/>
		<input type="submit" name="save" value="<spring:message code="finder.save"/>"/>
		<input type="submit" name="clear" value="<spring:message code="finder.clear"/>"/>
	</form:form>
	
	
</security:authorize>