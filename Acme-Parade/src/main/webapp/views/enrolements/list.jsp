<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="enrolements" requestURI="${requestURI}" id="row">

<!-- Attributes -->	
		
		<jstl:choose>
		
		<jstl:when test="${row.status=='PENDING'}">
		<jstl:if test="${memberView==true}">
		<display:column property="brotherhood.title" titleKey="enrolement.brotherhood" style="background-color:lightgrey;"/>
		
		</jstl:if>
		<display:column property="enrolMoment" titleKey="enrolement.enrolMoment" style="background-color:lightgrey;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:lightgrey;"/>
		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:lightgrey;"/>
		<jstl:if test="${memberView==true}">
		<display:column style="background-color:lightgrey;"/>
		</jstl:if>
		
		<jstl:if test="${brotherhoodView==true}">
		<display:column style="background-color:lightgrey;"><a href="enrolements/brotherhood/enrol.do?enrolementId=${row.id}">
			<spring:message code="enrolement.acept"/></a></display:column>
		
		<display:column style="background-color:lightgrey;"><div style="align:center;"><a href="enrolements/brotherhood/reject.do?enrolementId=${row.id}">
			<spring:message code="enrolement.reject" /></a></div></display:column>
		<display:column style="background-color:lightgrey;"></display:column>
		</jstl:if>
		</jstl:when>
		
		<jstl:when test="${row.status=='APPROVED'}">
		<jstl:if test="${memberView==true}">
		<display:column property="brotherhood.title" titleKey="enrolement.brotherhood" style="background-color:lightgreen;"/>
		</jstl:if>
		<display:column property="enrolMoment" titleKey="enrolement.enrolMoment" style="background-color:lightgreen;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:lightgreen;"/>
		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:lightgreen;"/>
	
		
		<display:column style="background-color:lightgreen;"><a href="brotherhood/member/show.do?brotherhoodId=${row.brotherhood.id}"><spring:message code="brotherhood.show"/></a></display:column>
		<security:authorize access="hasRole('BROTHERHOOD')">
		<display:column style="background-color:lightgreen;">></display:column>
		
		</security:authorize>
		</jstl:when>
		
		<jstl:when test="${row.status=='REJECTED'}">
		<jstl:if test="${memberView==true}">
		<display:column property="brotherhood.title" titleKey="enrolement.brotherhood" style="background-color:orange;"/>
		</jstl:if>
		<display:column property="enrolMoment" titleKey="enrolement.enrolMoment" style="background-color:orange;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:orange;"/>
		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:orange;"/>
		<display:column style="background-color:orange;"></display:column>
		<security:authorize access="hasRole('BROTHERHOOD')">
		<display:column style="background-color:orange;">></display:column>

		</security:authorize>
		</jstl:when>
		
		<jstl:when test="${row.status=='EXPELLED'}">
		<jstl:if test="${memberView==true}">
		<display:column property="brotherhood.title" titleKey="enrolement.brotherhood" style="background-color:orange;"/>
		</jstl:if>
		<display:column property="enrolMoment" titleKey="enrolement.enrolMoment" style="background-color:orange;"/>
		<display:column property="status" titleKey="enrolement.status" sortable="true" style="background-color:orange;"/>
	
		<display:column property="dropOutMoment" titleKey="enrolement.dropOutMoment" style="background-color:orange;"/>
		<display:column style="background-color:orange;"></display:column>
		
		<security:authorize access="hasRole('BROTHERHOOD')">
		<display:column style="background-color:orange;">></display:column>
		</security:authorize>
		</jstl:when>
		
		</jstl:choose>
		
		
	</display:table>
<security:authorize access="hasRole('MEMBER')">
<div>
	<a href="enrolements/member/create.do"> 
	<spring:message	code="enrolement.create" />
	</a>
</div>
</security:authorize>
