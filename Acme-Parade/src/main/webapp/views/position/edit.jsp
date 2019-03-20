<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>





<form:form modelAttribute="position" action="position/administrator/edit.do">
		<form:hidden path="id"/>
		<form:hidden path="version"/>

		<form:label path="positionEsp">
			<spring:message code="position.positionEsp" />:
		</form:label>
		<form:input path="positionEsp" />
		<form:errors cssClass="error" path="positionEsp" />
		<br />	

		<form:label path="positionEng">
			<spring:message code="position.positionEng" />:
		</form:label>
		<form:input path="positionEng" />
		<form:errors cssClass="error" path="positionEng" />
		<br />
			
	<input type ="submit" name="save" value="<spring:message code="position.edit.save"/>" />

	<input type="button" name="cancel" value="<spring:message code="position.cancel" />" onclick="javascript:relativeRedir('position/administrator/list.do');" />


	<input type ="submit" name="delete" value="<spring:message code="position.delete"/>" />

</form:form>










