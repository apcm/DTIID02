<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="chapter/chapter/edit.do" modelAttribute="chapter">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	
	<fieldset>
	<legend align="left"><spring:message code="chapter.edit.contact" /></legend>
	
		<form:label path="name">
			<spring:message code="chapter.edit.label.name" />* :
		</form:label>
		<form:input path="name"/>
		<form:errors cssClass="error" path="name" />		
		<br/>
		<br/>
	
		<form:label path="middleName">
			<spring:message code="chapter.edit.label.middleName" />:
		</form:label>
		<form:input path="middleName"/>
		<form:errors cssClass="error" path="middleName" />		
		<br/>
		<br/>
		
		<form:label path="surname">
			<spring:message code="chapter.edit.label.surname" />* :
		</form:label>
		<form:input path="surname"/>
		<form:errors cssClass="error" path="surname" />		
		<br/>
		<br/>
		
		<form:label path="address">
			<spring:message code="chapter.edit.label.address" />* :
		</form:label>
		<form:input path="address"/>
		<form:errors cssClass="error" path="address" />		
		<br/>
		<br/>
	
		<form:label path="email">
			<spring:message code="chapter.edit.label.email" />*:
		</form:label>
		<form:input path="email" />
		<form:errors cssClass="error" path="email" />	
		<br/>
		<br/>
		
		<form:label path="phoneNumber">
			<spring:message code="chapter.edit.label.phoneNumber" />:
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
		            alert("<spring:message code="chapter.confirm" />" );
		        }
		    }
		</script>		
		<br/>
		<br/>
	
		<form:label path="photo">
			<spring:message code="chapter.edit.label.photo" />:
		</form:label>
		<form:input path="photo"/>
		<form:errors cssClass="error" path="photo" />
		
		<br/>
		<br/>

		
	</fieldset>
	<br/>
	<br/>

	<fieldset>
		<legend align="left"><spring:message code="chapter.chapter"/></legend>
		<form:label path="title">
			<spring:message code="chapter.title"/>* :
		</form:label>
		<form:input path="title"/>
		<form:errors cssClass="error" path="title" />
		<br/><br/>
		
		
		<jstl:if test="${area==null}">
		<form:label path="area">
			<spring:message code="chapter.area" />:
		</form:label>
		<form:select id="areas" path="area">
			<form:option value="0" label="----"/>
			<form:options items="${areas}" itemLabel="name" itemValue="id"/>
		</form:select>
		<form:errors cssClass="error" path="area" />
		</jstl:if>
		
	</fieldset>

	<br/>
	<spring:message code="chapter.leave.explanation" var="leaveExplanation"/>
	<jstl:out value="${leaveExplanation}"/>
	
	<a href="chapter/chapter/show.do"> Link </a>
	
	<br/>
	<input type="submit" name="save" value="<spring:message code="chapter.edit.save.save" />" />&nbsp;
	<input type="submit" name="leave" value="<spring:message code="chapter.edit.leave" />" 
		onclick="return confirm('<spring:message code="chapter.confirm.delete" />')" />&nbsp;
	<input type="button" name="cancel" onclick="javascript: window.location.replace('welcome/index.do')"
			value="<spring:message code="chapter.edit.cancel" />" />

</form:form>