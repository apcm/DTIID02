<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="member/member/edit.do" modelAttribute="member">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	
	<fieldset>
	<legend align="left"><spring:message code="member.edit.contact" /></legend>
	
		<form:label path="name">
			<spring:message code="member.edit.label.name" />* :
		</form:label>
		<form:input path="name"/>
		<form:errors cssClass="error" path="name" />		
	
		<br/>
		<br/>
	
		<form:label path="middleName">
			<spring:message code="member.edit.label.middleName" />:
		</form:label>
		<form:input path="middleName"/>
		<form:errors cssClass="error" path="middleName" />		
		
		<br/>
		<br/>
		
		<form:label path="surname">
			<spring:message code="member.edit.label.surname" />* :
		</form:label>
		<form:input path="surname"/>
		<form:errors cssClass="error" path="surname" />		
		
		<br/>
		<br/>
		
		<form:label path="address">
			<spring:message code="member.edit.label.address" />* :
		</form:label>
		<form:input path="address"/>
		<form:errors cssClass="error" path="address" />		
		
		<br/>
		<br/>
	
		<form:label path="email">
			<spring:message code="member.edit.label.email" />*:
		</form:label>
		<form:input path="email" pattern="[A-z0-9]+@[A-z0-9.]+|[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>"/>
		<form:errors cssClass="error" path="email" />	
		<br/>
		<br/>
		
		<form:label path="phoneNumber">
			<spring:message code="member.edit.label.phoneNumber" />:
		</form:label>
		<form:input path="phoneNumber" onchange="check(this)" pattern="^(\d|\(|\)| |\+)+$"/>
		<form:errors cssClass="error" path="phoneNumber" />
		
		<script language='javascript' type='text/javascript'>
		
			var re = /^\+\d{1,3} \(\d{1,3}\) \d{4,}$/;
			var re2 = /^\+\d{1,3} \d{4,}$/;
			var re3 = /^\d{4,}$/;
		
		    function check(input) {
		    	var OK = re.exec(input.value);
		    	var OK2 = re2.exec(input.value);
		    	var OK3 = re3.exec(input.value);
		        if (!(OK || OK2 || OK3)) {
		            alert("<spring:message code="member.confirm" />" );
		        }
		    }
		</script>		
		<br/>
		<br/>
	
		<form:label path="photo">
			<spring:message code="member.edit.label.photo" />:
		</form:label>
		<form:input path="photo"/>
		<form:errors cssClass="error" path="photo" />
		
		<br/>
		<br/>

		
	</fieldset>
	<br/>

	<spring:message code="member.leave.explanation" var="leaveExplanation"/>
	<jstl:out value="${leaveExplanation}"/>
	
	<a href="member/member/show.do"> Link </a>
		<br/>
	<br/>
	<br/>
	<input type="submit" name="save" value="<spring:message code="member.edit.save.save" />" />&nbsp;
		<input type="submit" name="leave" value="<spring:message code="member.edit.leave" />" 
		onclick="return confirm('<spring:message code="member.confirm.delete" />')" />&nbsp;
	<input type="button" name="cancel" onclick="javascript: window.location.replace('welcome/index.do')"
			value="<spring:message code="member.edit.cancel" />" />

</form:form>