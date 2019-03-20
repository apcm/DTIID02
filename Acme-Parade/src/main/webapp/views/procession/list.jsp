
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
	name="parades" requestURI="${requestURI}" id="row">

	<display:column property="title" titleKey="parade.title"  />
	<display:column property="description" titleKey="parade.description"  />
	<display:column property="departureDate" titleKey="parade.departureDate"  />
	<display:column property="ticker" titleKey="parade.ticker"  />
	<display:column property="finalMode" titleKey="parade.finalMode"  />

	<security:authorize access="hasRole('BROTHERHOOD')">
	<display:column>	
			
			<jstl:if test="${row.finalMode == false}">
			<a href="parade/brotherhood/edit.do?paradeId=${row.id}">
			<spring:message code="parade.edit.link" />
			</a>
			</jstl:if>
			
			<jstl:if test="${row.finalMode == true}">
			<a href="requests/brotherhood/list.do?paradeId=${row.id}">
			<spring:message code="parade.list.request" />
			</a>
			</jstl:if>
			
	</display:column>
	</security:authorize>			
</display:table>



<security:authorize access="hasRole('BROTHERHOOD')">
		<div>
			<a href="parade/brotherhood/create.do"> <spring:message
					code="parade.list.create" />
			</a>
		</div>
</security:authorize>