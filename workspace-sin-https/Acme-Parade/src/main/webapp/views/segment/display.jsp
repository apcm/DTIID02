<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>



<b><spring:message code="segment.origLatitude"/></b>
<jstl:out value="${segment.origLatitude}"/>
<br/>

<b><spring:message code="segment.origLongitude"/></b>
<jstl:out value="${segment.origLongitude}"/>
<br/>

<b><spring:message code="segment.destLatitude"/></b>
<jstl:out value="${segment.destLatitude}"/>
<br/>

<b><spring:message code="segment.destLongitude"/></b>
<jstl:out value="${segment.destLongitude}"/>
<br/>

<b><spring:message code="segment.startTime"/></b>
<jstl:out value="${segment.startTime}"/>
<br/>

<b><spring:message code="segment.arriveTime"/></b>
<jstl:out value="${segment.arriveTime}"/>
<br/>




	

