<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<jstl:if test="${brotherhoodView==true }">
<form:form action="enrolements/brotherhood/enrol.do" modelAttribute="enrolement">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="enrolMoment"/>
	<form:hidden path="status"/>
	<form:hidden path="brotherhood"/>
	
		
		<h2><form:label path="position">
			<spring:message code="enrolement.position"/>:
		</form:label></h2>
		<jstl:if test="${pageContext.response.locale.language=='en'}">
		<form:select path="position">
			<form:options items="${listPositions}" itemLabel="positionEng" itemValue="id"/>								 		
		</form:select>
		</jstl:if>
		
		<jstl:if test="${pageContext.response.locale.language=='es'}">
		<form:select path="position">
			<form:options items="${listPositions}" itemLabel="positionEsp" itemValue="id"/>								 		
		</form:select>
		</jstl:if>
		<form:errors cssClass="error" path="position"/>
		
		
	
	<br/>
	<br/>
	<input type="submit" name="save"
			value="<spring:message code="enrolement.save" />" />
			<input type="button" name="back" onclick="javascript: window.location.replace('enrolements/brotherhood/list.do')"
		value="<spring:message code="enrolement.back" />" />
		<br/>
	</form:form>
	</jstl:if>
	
	<jstl:if test="${memberView==true }">
	<form:form action="enrolements/member/edit.do" modelAttribute="enrolement">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="enrolMoment"/>
	<form:hidden path="status"/>
		<h2><form:label path="brotherhood">
			<spring:message code="enrolement.brotherhood"/>:
		</form:label></h2>
		<form:select path="brotherhood">
			<form:options items="${listBrotherhoods}" itemLabel="title" itemValue="id"/>								 		
		</form:select>
		<form:errors cssClass="error" path="brotherhood"/>
	
	<br/>
	<br/>
	<input type="submit" name="save"
			value="<spring:message code="enrolement.save" />" />
			<input type="button" name="back" onclick="javascript: window.location.replace('enrolements/member/list.do')"
		value="<spring:message code="enrolement.back" />" />
		<br/>
	</form:form>
		</jstl:if>
