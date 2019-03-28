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



<display:table name="segments" id="row" requestURI="${requestURI}" pagesize="5" class ="displaytag">

<display:column property="segmentOrder" titleKey="segment.segmentOrder"/>
<display:column property="origLatitude" titleKey="segment.origLatitude"/>
<display:column property="origLongitude" titleKey="segment.origLongitude"/>
<display:column property="destLatitude" titleKey="segment.destLatitude"/>
<display:column property="destLongitude" titleKey="segment.destLongitude"/>


<display:column>
	<a href="parade/segment/display.do?segmentId=${row.id}">
		<spring:message code="segment.display"/>
	</a>
</display:column>


</display:table>









