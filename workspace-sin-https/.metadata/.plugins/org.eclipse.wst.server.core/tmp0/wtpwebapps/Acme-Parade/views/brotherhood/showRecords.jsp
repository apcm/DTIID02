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
	name="legalRecords" requestURI="${requestURI}" id="row">

	<display:column property="title" titleKey="brotherhood.title"  />
	<display:column property="description" titleKey="brotherhood.description"  />
	<display:column property="legalName" titleKey="brotherhood.legalName"  />
	<display:column property="applicableLaws" titleKey="brotherhood.applicableLaws"  />
	<display:column property="vatNumber" titleKey="brotherhood.vatNumber"  />

</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="periodRecords" requestURI="${requestURI}" id="row">

	<display:column property="title" titleKey="brotherhood.title"  />
	<display:column property="description" titleKey="brotherhood.description"  />
	<display:column property="startYear" titleKey="brotherhood.startYear"  />
	<display:column property="endYear" titleKey="brotherhood.endYear"  />
	<display:column property="photos" titleKey="brotherhood.photos"  />

</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="inceptionRecords" requestURI="${requestURI}" id="row">

	<display:column property="title" titleKey="brotherhood.title"  />
	<display:column property="description" titleKey="brotherhood.description"  />
	<display:column property="photos" titleKey="brotherhood.photos"  />

</display:table>

	
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="linkRecords" requestURI="${requestURI}" id="row">

	<display:column property="title" titleKey="brotherhood.title"  />
	<display:column property="description" titleKey="brotherhood.description"  />
	<display:column property="link" titleKey="brotherhood.link"  />

</display:table>


<br/><br/>

	<input type="button" name="back" onclick="javascript: window.location.replace('enrolements/member/list.do')"
		value="<spring:message code="member.back" />" />
