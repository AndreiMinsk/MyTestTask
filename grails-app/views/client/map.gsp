<%@ page import="task.Client" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'client.label', default: 'Client')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <meta charset="utf-8"/>
    <title>Show Client</title>
    <style>
    html, body, #map-canvas {
        height: 100%;

        padding: 0px;

    }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDggu1MnCEGRHR2IqTraFGKwCGzMCHJACE"></script>
    <script>
        function initialize() {

            var myLatlng = new google.maps.LatLng(51.512,-0.052);
            var mapOptions = {
                zoom: 5,
                center: myLatlng
            };
            var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            <g:each in="${clients}" status="i" var="client">



            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(${client.latitude}, ${client.longitude}),
                map: map,
                title: '${clientInstance?.name}'
            });

            </g:each>


        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>

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
<div id="map-canvas"></div>

</body>
</html>