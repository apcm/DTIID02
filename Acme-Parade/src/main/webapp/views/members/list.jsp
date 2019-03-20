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
	name="members" requestURI="${requestURI}" id="row">

<!-- Attributes -->
		<display:column property="name" titleKey="member.name" sortable="true"/>
		<display:column property="surname" titleKey="member.surname" sortable="true"/>
		<display:column property="middleName" titleKey="member.midleName"/>
		<security:authorize access="hasRole('BROTHERHOOD')">
		<display:column property="email" titleKey="member.email"/>
		
<!-- Actions -->
		<display:column>
	
			<a href="members/brotherhood/show.do?memberId=${row.id}"> 
				<spring:message	code="member.show" />
			</a>

		</display:column>
		</security:authorize>
	</display:table>

<security:authorize access="hasRole('BROTHERHOOD')">
<div>
	<a href="enrolements/brotherhood/list.do"> 
	<spring:message	code="enrolement.list" />
	</a>
</div>
</security:authorize>
