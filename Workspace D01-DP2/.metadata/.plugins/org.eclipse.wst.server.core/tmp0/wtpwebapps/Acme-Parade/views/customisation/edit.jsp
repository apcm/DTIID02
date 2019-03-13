
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


	<form:form modelAttribute="customisation" action="customisation/edit.do" >
		
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		
		<form:label path="negativeWords">
			<spring:message code="customisation.negativeWords" />:
		</form:label>
		<form:textarea path="negativeWords" />
		<form:errors cssClass="error" path="negativeWords" />
		<br />
		
		<form:label path="positiveWords">
			<spring:message code="customisation.positiveWords" />:
		</form:label>
		<form:textarea path="positiveWords" />
		<form:errors cssClass="error" path="positiveWords" />
		<br />
		
		<form:label path="finderDuration">
			<spring:message code="customisation.finderDuration" />:
		</form:label>
		<form:input path="finderDuration" />
		<form:errors cssClass="error" path="finderDuration" />
		<br />
		
		<form:label path="resultsNumber">
			<spring:message code="customisation.resultsNumber" />:
		</form:label>
		<form:input path="resultsNumber" />
		<form:errors cssClass="error" path="resultsNumber" />
		<br />
				
		
		<form:label path="systemName">
			<spring:message code="customisation.systemName" />:
		</form:label>
		<form:input path="systemName" />
		<form:errors cssClass="error" path="systemName" />
		<br />	
		
		<form:label path="bannerURL">
			<spring:message code="customisation.bannerURL" />:
		</form:label>
		<form:input path="bannerURL" />
		<form:errors cssClass="error" path="bannerURL" />
		<br />	
		
		<form:label path="welcomeMessage">
			<spring:message code="customisation.welcomeMessage" />:
		</form:label>
		<form:textarea path="welcomeMessage" />
		<form:errors cssClass="error" path="welcomeMessage" />
		<br />	
		
		<form:label path="spamWords">
			<spring:message code="customisation.spamWords" />:
		</form:label>
		<form:textarea path="spamWords" />
		<form:errors cssClass="error" path="spamWords" />
		<br />	
		
		<form:label path="phoneNumberCountryCode">
			<spring:message code="customisation.phoneNumberCountryCode" />:
		</form:label>
		<form:input path="phoneNumberCountryCode" />
		<form:errors cssClass="error" path="phoneNumberCountryCode" />
		<br />	
		
		<form:label path="messagePriorities">
			<spring:message code="customisation.messagePriorities" />:
		</form:label>
		<form:input path="messagePriorities" />
		<form:errors cssClass="error" path="messagePriorities" />
		<br />	
		
		
		<input type="submit" name="save" value = "<spring:message code ="customisation.save" /> " />
		
		<input type="button" name="cancel" value="<spring:message code ="customisation.cancel" />" onclick="javascript:relativeRedir('welcome/index.do');" />
	
	
	
	</form:form>
	
	