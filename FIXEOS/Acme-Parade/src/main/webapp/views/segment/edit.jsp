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


<form:form modelAttribute="segment" action="parade/segment/edit.do">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="segmentOrder"/>
		
		<jstl:if test="${segment.segmentOrder != 1}">
		<spring:message code ="segment.hidden.values"/>
		<br/>
		<form:hidden path="origLatitude"/>
		<form:hidden path="origLongitude"/>
		<form:hidden path="startTime" />
		</jstl:if>
		
		
		<jstl:if test="${segment.segmentOrder == 1}">
		<form:label path="origLatitude">
			<spring:message code="segment.origLatitude" />:
		</form:label>
		<form:input path="origLatitude" />
		<form:errors cssClass="error" path="origLatitude" />
		<br />
		
		<form:label path="origLongitude">
			<spring:message code="segment.origLongitude" />:
		</form:label>
		<form:input path="origLongitude" />
		<form:errors cssClass="error" path="origLongitude" />
		<br />
		</jstl:if>
		
		<form:label path="destLatitude">
			<spring:message code="segment.destLatitude" />:
		</form:label>
		<form:input path="destLatitude" />
		<form:errors cssClass="error" path="destLatitude" />
		<br />	

		<form:label path="destLongitude">
			<spring:message code="segment.destLongitude" />:
		</form:label>
		<form:input path="destLongitude" />
		<form:errors cssClass="error" path="destLongitude" />
		<br />	
		
		<jstl:if test="${segment.segmentOrder == 1}">
		
		<form:label path="startTime">
			<spring:message code="segment.startTime" />:
		</form:label>
		<form:input path="startTime" placeholder="Ex: 2020-12-30 23:59:59"/>
		<form:errors cssClass="error" path="startTime" />
		<br />	
		</jstl:if>
		<form:label path="arriveTime">
			<spring:message code="segment.arriveTime" />:
		</form:label>
		<form:input path="arriveTime" placeholder="Ex: 2020-12-30 23:59:59"/>
		<form:errors cssClass="error" path="arriveTime" />
		<br />	
			
			
	<input type ="submit" name="save" value="<spring:message code="segment.save"/>" />
	
	
	<jstl:if test="${segment.id != 0}">
	<input type ="submit" name="delete" value="<spring:message code="segment.delete"/>" />
	</jstl:if>


</form:form>










