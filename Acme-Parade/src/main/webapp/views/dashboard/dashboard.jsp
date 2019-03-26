<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript" src="scripts/Chart.js"></script>

<security:authorize access="hasRole('ADMIN')">

	<spring:message code="dashboard.minMembers"/>
	<jstl:out value="${minM}"/>
	<br/>
	<spring:message code="dashboard.maxMembers"/>
	<jstl:out value="${maxM}"/>
	<br/>
	<spring:message code="dashboard.avgMembers"/>
	<jstl:out value="${avgM}"/>
	<br/>
	<spring:message code="dashboard.stddevMembers"/>
	<jstl:out value="${stddevM}"/>
	<br/>
	<spring:message code="dashboard.largestBrotherhoods"/>
	<jstl:out value="${largestB}"/>
	<br/>
	<spring:message code="dashboard.smallestsBrotherhoods"/>
	<jstl:out value="${smallestB}"/>
	<br/>
	<spring:message code="dashboard.requestRatioProcession"/>
	<spring:message code="dashboard.ratioList"/>
	<jstl:out value="${ratioList}"/>
	<br>
	<spring:message code="dashboard.processionList"/>
	<jstl:out value="${processionList}"/>
	<br>
	<spring:message code="dashboard.statusList"/>
	<jstl:out value="${statusList}"/>
	<br/>
	<spring:message code="dashboard.processions30Days"/>
	<jstl:out value="${p30}"/>
	<br/>
	<spring:message code="dashboard.requestRatio"/>
	<jstl:out value="${rRatio}"/>
	<br/>
	<spring:message code="dashboard.members10Percent"/>
	<jstl:out value="${m10}"/>
	<br/>
	<spring:message code="dashboard.ratioBrotherhoodArea"/>
	<jstl:out value="${ratioBA}"/>
	<br/>
	<spring:message code="dashboard.ratioBrotherhoodArea"/>
	<jstl:out value="${ratioBA}"/>
	<br/>
	<spring:message code="dashboard.countBrotherhoodArea"/>
	<jstl:out value="${countBA}"/>
	<br/>
	<spring:message code="dashboard.minBrotherhoodArea"/>
	<jstl:out value="${minBA}"/>
	<br/>
	<spring:message code="dashboard.maxBrotherhoodArea"/>
	<jstl:out value="${maxBA}"/>
	<br/>
	<spring:message code="dashboard.avgBrotherhoodArea"/>
	<jstl:out value="${avgBA}"/>
	<br/>
	<spring:message code="dashboard.stddevBrotherhoodArea"/>
	<jstl:out value="${stddevBA}"/>
	<br/>
	<spring:message code="dashboard.avgFinder"/>
	<jstl:out value="${avgFinder}"/>
	<br/>
	<spring:message code="dashboard.minFinder"/>
	<jstl:out value="${minFinder}"/>
	<br/>
	<spring:message code="dashboard.maxFinder"/>
	<jstl:out value="${maxFinder}"/>
	<br/>
	<spring:message code="dashboard.stddevFinder"/>
	<jstl:out value="${stddevFinder}"/>
	<br/>
	<spring:message code="dashboard.ratioEmptyFinder"/>
	<jstl:out value="${ratioEmptyFinders}"/>
	<br/>
	<h2><spring:message code="dashboard.histogram"/></h2>
	<canvas id="myChart" width="300" height="300"></canvas>
	<script type="text/javascript">
	var labels= new Array();
	var values = new Array();
	<jstl:forEach items="${posHistList}" var="pos">
	 labels.push("<jstl:out value='${pos}'/>");
	 </jstl:forEach>
	 <jstl:forEach items="${posCountList}" var="count">
	 values.push(<jstl:out value='${count}'/>);
	 </jstl:forEach>

	
	
	
	var ctx = document.getElementById("myChart");
	var myChart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: labels,
	        datasets: [{
	            label: 'Number of positions',
	            data: values,
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	    }
	});
	</script>
	
	<!-- ACME PARADE -->
	<spring:message code="dashboard.maxRec"/>
	<jstl:out value="${maxRec}"/>
	<br/>
	<spring:message code="dashboard.minRec"/>
	<jstl:out value="${minRec}"/>
	<br/>
	<spring:message code="dashboard.avgRec"/>
	<jstl:out value="${avgRec}"/>
	<br/>
	<spring:message code="dashboard.stdRec"/>
	<jstl:out value="${stdRec}"/>
	<br/>
	<spring:message code="dashboard.largestBH"/>
	<jstl:out value="${largestBH}"/>
	<br/>
	<spring:message code="dashboard.largestAvgBH"/>
	<jstl:out value="${largestAvgBH}"/>
	<br/>
	
	<spring:message code="dashboard.ratioANC"/>
	<jstl:out value="${ratioANC}"/>
	<br/>
	<spring:message code="dashboard.avgPC"/>
	<jstl:out value="${avgPC}"/>
	<br/>
	<spring:message code="dashboard.minPC"/>
	<jstl:out value="${minPC}"/>
	<br/>
	<spring:message code="dashboard.maxPC"/>
	<jstl:out value="${maxPC}"/>
	<br/>
	<spring:message code="dashboard.stddevPC"/>
	<jstl:out value="${stddevPC}"/>
	<br/>
	<spring:message code="dashboard.chapterMoreParades"/>
	<jstl:out value="${chapterMoreParades}"/>
	<br/>
	<spring:message code="dashboard.ratioPDvF"/>
	<jstl:out value="${ratioPDvF}"/>
	<br/>
	<spring:message code="dashboard.ratioPFM"/>
	<jstl:out value="${ratioPFM}"/>
	<br/>
	
</security:authorize>