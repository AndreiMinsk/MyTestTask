
<%@ page import="task.Client" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'client.label', default: 'Client')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>

</head>
<body>
<a href="#list-client" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <g:if test='${session?.user?.admin}'>
            <li><a class="home" href="${createLink(uri: '/admin/index.gsp')}"><g:message code="default.home.label"/></a></li>
        </g:if>
        <g:elseif test='${session?.user}'>
            <li><a class="home" href="${createLink(uri: '/client/index.gsp')}"><g:message code="default.home.label"/></a></li>
        </g:elseif>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        <li><a href="${g.createLink(controller: 'client', action: 'map')}">SHOW MAP</a></li>
    </ul>
</div>
<div id="list-client" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'client.name.label', default: 'Name')}" />

            <g:sortableColumn property="email" title="${message(code: 'client.email.label', default: 'Email')}" />

            <g:sortableColumn property="street" title="${message(code: 'client.street.label', default: 'Street')}" />

            <g:sortableColumn property="zip" title="${message(code: 'client.zip.label', default: 'Zip')}" />

            <g:sortableColumn property="latitude" title="${message(code: 'client.latitude.label', default: 'Latitude')}" />

            <g:sortableColumn property="longitude" title="${message(code: 'client.longitude.label', default: 'Longitude')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${clientInstanceList}" status="i" var="clientInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${clientInstance.id}">${fieldValue(bean: clientInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: clientInstance, field: "email")}</td>

                <td>${fieldValue(bean: clientInstance, field: "street")}</td>

                <td>${fieldValue(bean: clientInstance, field: "zip")}</td>

                <td>${fieldValue(bean: clientInstance, field: "latitude")}</td>

                <td>${fieldValue(bean: clientInstance, field: "longitude")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${clientInstanceCount ?: 0}" />
    </div>
</div>
</body>
</html>
