<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script src="<c:url value="/resources/scripts/jquery-2.0.3.min.js" />"></script>
    <script src="<c:url value="/resources/scripts/globalize.min.js" />"></script>
    <script src="<c:url value="/resources/scripts/dx.chartjs.js" />"></script>
    <script src="<c:url value="/resources/scripts/sensors-charts.js" />"></script>
    <script>
        $(document).ready(function() {


        });
        function ajaxRequest() {
            var data = {
                sensorIndex:'0',
                'sensorType':"DHT11"
            };
            $.ajax({
                url: 'sensors/get/history',
                type: 'POST',
                data: JSON.stringify(data),
                beforeSend: function( xhr ) {
                    xhr.setRequestHeader('Accept','*/*');
                    xhr.setRequestHeader('Content-Type','application/json');
                },
                success: callbackfunction
            });
        }
        function callbackfunction(data, textStatus, jqXHR) {
            console.log(data);
        }
    </script>
</head>
<body>
<h1>Message : ${message}</h1>
<div id="chartContainer" style="max-width:800px;height: 400px;"></div>
</body>
</html>
