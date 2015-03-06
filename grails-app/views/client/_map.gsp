<%@ page import="task.Client" %>
<!DOCTYPE html>
<html>
<head>
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
            var myLatlng = new google.maps.LatLng('${clientInstance.latitude}','${clientInstance.longitude}' );
            // var myLatlng = new google.maps.LatLng(-25.363882,131.044922);
            var mapOptions = {
                zoom: 10,
                center: myLatlng
            }
            var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

            var marker = new google.maps.Marker({
                position: myLatlng,
                map: map,
                title: '${clientInstance?.name}'
            });
        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<div id="map-canvas"></div>
</body>
</html>