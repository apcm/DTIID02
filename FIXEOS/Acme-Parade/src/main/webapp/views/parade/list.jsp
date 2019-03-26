<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="parades" requestURI="${requestURI}" id="row">

	<jstl:if test="${row.finalMode == true}">
		<jstl:choose>
			<jstl:when test="${row.status == 'SUBMITTED' }">
				<display:column property="title" titleKey="parade.title"
					style="background-color:lightgrey;" />
				<display:column property="description" titleKey="parade.description"
					style="background-color:lightgrey;" />
				<display:column property="brotherhood.title"
					titleKey="parade.brotherhood" style="background-color:lightgrey;" />
				<display:column property="departureDate"
					titleKey="parade.departureDate" style="background-color:lightgrey;" />
				<display:column property="ticker" titleKey="parade.ticker"
					style="background-color:lightgrey;" />
				<display:column property="finalMode" titleKey="parade.finalMode"
					style="background-color:lightgrey;" />
				<security:authorize access="hasRole('BROTHERHOOD')">
					<display:column style="background-color:lightgrey;">
						<a href="requests/brotherhood/list.do?paradeId=${row.id}"> <spring:message
								code="parade.list.request" />
						</a>
					</display:column>
					
					<display:column style="background-color:lightgrey;">
						<a href=""
					</display:column>
				</security:authorize>

				<display:column style="background-color:lightgrey;" titleKey = "parade.floats">
					<jstl:forEach var="f" items="${row.floats }">
						<jstl:out value="${f.title }"></jstl:out>
					</jstl:forEach>
				</display:column>

				<security:authorize
					access="hasRole('BROTHERHOOD')||hasRole('CHAPTER')">
					<display:column property="status" titleKey="parade.status"
						sortable="true" style="background-color:lightgrey;" />

				</security:authorize>

			</jstl:when>

			<jstl:when test="${row.status == 'ACCEPTED'}">
				<display:column property="title" titleKey="parade.title"
					style="background-color:lightgreen;" />
				<display:column property="description" titleKey="parade.description"
					style="background-color:lightgreen;" />
				<display:column property="brotherhood.title"
					titleKey="parade.brotherhood" style="background-color:lightgreen;" />
				<display:column property="departureDate"
					titleKey="parade.departureDate"
					style="background-color:lightgreen;" />
				<display:column property="ticker" titleKey="parade.ticker"
					style="background-color:lightgreen;" />
				<display:column property="finalMode" titleKey="parade.finalMode"
					style="background-color:lightgreen;" />
				<security:authorize access="hasRole('BROTHERHOOD')">
					<display:column style="background-color:lightgreen;">
						<a href="requests/brotherhood/list.do?paradeId=${row.id}"> <spring:message
								code="parade.list.request" />
						</a>
					</display:column>
				</security:authorize>
				<display:column style="background-color:lightgreen;" titleKey = "parade.floats">
					<jstl:forEach var="f" items="${row.floats }">
						<jstl:out value="${f.title }"></jstl:out>
						<br/>
					</jstl:forEach>
				</display:column>


				<security:authorize
					access="hasRole('BROTHERHOOD')||hasRole('CHAPTER')">
					<display:column property="status" titleKey="parade.status"
						sortable="true" style="background-color:lightgreen;" />

				</security:authorize>
			</jstl:when>

			<jstl:when test="${row.status == 'REJECTED'}">
				<display:column property="title" titleKey="parade.title"
					style="background-color:red;" />
				<display:column property="description" titleKey="parade.description"
					style="background-color:red;" />
				<display:column property="brotherhood.title"
					titleKey="parade.brotherhood" style="background-color:red;" />
				<display:column property="departureDate"
					titleKey="parade.departureDate" style="background-color:red;" />
				<display:column property="ticker" titleKey="parade.ticker"
					style="background-color:red;" />
				<display:column property="finalMode" titleKey="parade.finalMode"
					style="background-color:red;" />
				<security:authorize access="hasRole('BROTHERHOOD')">
					<display:column style="background-color:red;">
						<a href="requests/brotherhood/list.do?paradeId=${row.id}"> <spring:message
								code="parade.list.request" />
						</a>
					</display:column>
				</security:authorize>
					<display:column titleKey = "parade.floats" style="background-color:red;">
						<jstl:forEach var="f" items="${row.floats }">
							<jstl:out value="${f.title }"></jstl:out>
							<br/>
						</jstl:forEach>
					</display:column>


					<security:authorize
						access="hasRole('BROTHERHOOD')||hasRole('CHAPTER')">
						<display:column property="status" titleKey="parade.status"
							sortable="true" style="background-color:red;" />

					</security:authorize>
			</jstl:when>
		</jstl:choose>
	</jstl:if>

	<jstl:if test="${row.finalMode == false}">
		<display:column property="title" titleKey="parade.title"
			style="background-color:lightorange;" />
		<display:column property="description" titleKey="parade.description"
			style="background-color:lightorange;" />
		<display:column property="brotherhood.title"
			titleKey="parade.brotherhood" style="background-color:lightorange;" />
		<display:column property="departureDate"
			titleKey="parade.departureDate" style="background-color:lightorange;" />
		<display:column property="ticker" titleKey="parade.ticker"
			style="background-color:lightorange;" />
		<display:column property="finalMode" titleKey="parade.finalMode"
			style="background-color:lightorange;" />

		<display:column style="background-color:lightorange;" titleKey="parade.floats">
			<jstl:forEach var="f" items="${row.floats }">
				<jstl:out value="${f.title }"></jstl:out>
				<br/>
			</jstl:forEach>
		</display:column>

		<security:authorize access="hasRole('BROTHERHOOD')">
			<display:column style="background-color:lightorange;">
				<a href="parade/brotherhood/edit.do?paradeId=${row.id}"> <spring:message
						code="parade.edit.link" />
				</a>
			</display:column>
		</security:authorize>

		<security:authorize
			access="hasRole('BROTHERHOOD')||hasRole('CHAPTER')">
			<display:column property="status" titleKey="parade.status"
				sortable="true" style="background-color:lightlightorange;" />

		</security:authorize>
	</jstl:if>

	<security:authorize access="hasRole('CHAPTER')">
		<display:column>

			<jstl:if
				test="${row.finalMode == false and row.status == 'SUBMITTED'}">
				<a href="parade/chapter/editStatus.do?paradeId=${row.id}"> <spring:message
						code="parade.edit.link" />
				</a>
			</jstl:if>

		</display:column>
		<display:column>

			<a href="chapter/segment/list.do?paradeId=${row.id}"> <spring:message
					code="parade.segment.list" />
			</a>

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