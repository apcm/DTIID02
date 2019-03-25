<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<!-- <a href="#"><img src="images/logo.png" alt="Acme Madruga Co., Inc." /></a> -->
	
	<a href="#"><img src="${customisation.bannerURL}" alt="${customisation.systemName}" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/administrator/create.do"><spring:message code="master.page.admin.create" /></a></li>
					<li><a href="administrator/administrator/edit.do"><spring:message code="master.page.admin.edit" /></a></li>
					<li><a href="area/administrator/list.do"><spring:message code="master.page.area.list" /></a></li>
					<li><a href="position/administrator/list.do"><spring:message code="master.page.administrator.position" /></a></li>
					<li><a href="customisation/edit.do"><spring:message code="master.page.customisation" /></a></li>					
					<li><a href="dashboard/administrator/dashboard.do"><spring:message code="master.page.dashboard"/></a></li>
					<li><a href="position/administrator/list.do"><spring:message code="master.page.position.edit" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('BROTHERHOOD')">
			<li><a class="fNiv"><spring:message	code="master.page.brotherhood" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="float/brotherhood/list.do"><spring:message code="master.page.float.list" /></a></li>	
					<li><a href="brotherhood/brotherhood/edit.do"><spring:message code="master.page.brotherhood.edit" /></a></li>	
					<li><a href="parade/brotherhood/list.do"><spring:message code="master.page.parade.list" /></a></li>	
					<li><a href="members/brotherhood/list.do"><spring:message code="master.page.members.list" /></a></li>	
					<li><a href="enrolements/brotherhood/list.do"><spring:message code="master.page.enrolements.list" /></a></li>	
					<li><a href="history/brotherhood/showHistory.do"><spring:message code="master.page.history" /></a></li>	
				</ul>
			</li>
		</security:authorize>
	
		<security:authorize access="hasRole('MEMBER')">
			<li><a class="fNiv"><spring:message	code="master.page.member" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="member/member/edit.do"><spring:message code="master.page.member.edit" /></a></li>
					<li><a href="finder/member/show.do"><spring:message code="master.page.finder" /></a></li>
					<li><a href="requests/member/list.do"><spring:message code="master.page.requests.list" /></a></li>	
					<li><a href="enrolements/member/list.do"><spring:message code="master.page.enrolements.list" /></a></li>	
				</ul>
			</li>
		</security:authorize>
	
		<security:authorize access="hasRole('CHAPTER')">
			<li><a class="fNiv"><spring:message	code="master.page.member" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="chapter/chapter/edit.do"><spring:message code="master.page.chapter.edit" /></a></li>
					<li><a href="parade/chapter/list.do"><spring:message code="master.page.parade.list" /></a></li>
				</ul>
			</li>
		</security:authorize>	
	
		
		<security:authorize access="isAnonymous()">
			<li>
				<a class="fNiv" href="brotherhood/register.do"><spring:message code="master.page.brotherhood.register" /></a>
			</li>
			<li>
				<a class="fNiv" href="member/register.do"><spring:message code="master.page.member.register" /></a>
			</li>
			<li>
				<a class="fNiv" href="chapter/register.do"><spring:message code="master.page.chapter.register" /></a>
			</li>
			<li><a class="fNiv" href="brotherhood/list.do"><spring:message code="master.page.brotherhoods" /></a></li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
		<li>
				<a class="fNiv"> 
					<spring:message code="master.page.messageboxes" /> 
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="boxes/list.do"><spring:message code="master.page.boxes" /></a></li>
					<li><a href="socialprofile/list.do"><spring:message code="master.page.socialprofile" /></a></li>
					</ul>
			</li>
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

