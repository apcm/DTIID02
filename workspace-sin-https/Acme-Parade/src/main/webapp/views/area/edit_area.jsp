<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>



	
	<form:form action="area/brotherhood/edit.do" modelAttribute="brotherhood">
		
		<form:hidden path="id" />
		<form:hidden path="version" />
		
		
		
		
		
		<form:label path="area">
			<spring:message code="brotherhood.area" />:
		</form:label>
		<form:select path="area">
		    <form:option label="----" value="0" />
		    <form:options items="${areaList}" itemLabel="name" itemValue="id" />
		    </form:select>
		<form:errors cssClass="error" path="area" />
		<br />
	
		
		
		
		<input type="submit" name = "save" value = "<spring:message code ="brotherhood.area.save" /> " />
		<spring:message code ="brotherhood.cancel" var="cancel" />
		<input type="button" name="cancel" value="${cancel}" onclick="javascript:relativeRedir('/welcome/index.do');" />

	
	</form:form>
	