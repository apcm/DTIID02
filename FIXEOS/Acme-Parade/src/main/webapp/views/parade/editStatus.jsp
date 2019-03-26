<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

  <button type="submit" id="boton" style="display:none"> Algun texto </button>



<form:form action="parade/chapter/editStatus.do" modelAttribute="parade">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />

	
	<security:authorize access="hasRole('CHAPTER')">
	
	<form:label path="status">
		<spring:message code="parade.status"/>
	</form:label>
	<jstl:if test="${status == SUBMITTED}">	
	<form:select path="status" name="status" id="status" >	
		<form:option value="SUBMITTED" label="Submitted"/>
		<form:option value="ACCEPTED" label="Accepted"/>
		<form:option value="REJECTED" label="Rejected"/>	
	</form:select>
	
	<script>
	window.onload=myFunction;
	document.getElementById("status").onchange = function() {myFunction()};	
	
    function myFunction() {
       var sta = document.getElementById('status').value;
	   var rej = new String('REJECTED');
       

       
      if (sta == rej) {
        document.getElementById("reject").style.display = "block";
        document.getElementById("reject2").style.display = "block";
        
      } else {
        document.getElementById("reject").style.display = "none";
        document.getElementById("reject2").style.display = "none";
        
      }
    }
  </script>
	<br/>
	
	
	<form:label path="rejectReason" id="reject2">
		<spring:message code="parade.rejectReason" />:
	</form:label>
	<form:input path="rejectReason" name="reject" id="reject"/>
		</jstl:if>
	</security:authorize>
	<br/>
	<br/>
	
	
	<input type="submit" name="save"
		value="<spring:message code="parade.save" />" />&nbsp; 
	
	
	<input type="button" name="cancel"
		value="<spring:message code="parade.cancel" />"
		onclick="javascript: relativeRedir('parade/chapter/list.do');" />
	<br />


</form:form>
