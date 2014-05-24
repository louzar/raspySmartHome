var dataSource = [
    { month: "January", avgT: 9.8, minT: 4.1, maxT: 15.5, prec: 109 },
    { month: "February", avgT: 11.8, minT: 5.8, maxT: 17.8, prec: 104 },
    { month: "March", avgT: 13.4, minT: 7.2, maxT: 19.6, prec: 92 },
    { month: "April", avgT: 15.4, minT: 8.1, maxT: 22.8, prec: 30 },
    { month: "May", avgT: 18, minT: 10.3, maxT: 25.7, prec: 10 },
    { month: "June", avgT: 20.6, minT: 12.2, maxT: 29, prec: 2 },
    { month: "July", avgT: 22.2, minT: 13.2, maxT: 31.3, prec: 2 },
    { month: "August", avgT: 22.2, minT: 13.2, maxT: 31.1, prec: 1 },
    { month: "September", avgT: 21.2, minT: 12.4, maxT: 29.9, prec: 8 },
    { month: "October", avgT: 17.9, minT: 9.7, maxT: 26.1, prec: 24 },
    { month: "November", avgT: 12.9, minT: 6.2, maxT: 19.6, prec: 64 },
    { month: "December", avgT: 9.6, minT: 3.4, maxT: 15.7, prec: 76 }
];

var historySource;

function historyAjaxRequest() {
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
    historySource = data.history;
    createChart();
}

function createChart() {
    $("#chartContainer").dxChart({
        dataSource: historySource,
        commonSeriesSettings:{
            argumentField: "date"
        },
        panes: [{
            name: "topPane"
        }, {
            name: "bottomPane"
        }],
        series: [{
            pane: "topPane",
            color: "#87CEEB",
            type: "rangeArea",
            rangeValue1Field: "temp",
            rangeValue2Field: "temp",
            name: "Temperature Ranges, °C"
        }, {
            pane: "topPane",
            valueField: "temp",
            name: "Average Temperature, °C",
            label: {
                visible: true,
                customizeText: function (){
                    return this.valueText + " °C";
                }
            }
        }, {
            type: "bar",
            valueField: "hum",
            name: "hum, %",
            label: {
                visible: true,
                customizeText: function (){
                    return this.valueText  + "%";
                }
            }
        }
        ],
        valueAxis: [{
            pane: "bottomPane",
            grid: {
                visible: true
            },
            title: {
                text: "Humidity,%"
            }
        }, {
            pane: "topPane",
            min: -30,
            max: 40,
            grid: {
                visible: true
            },
            title: {
                text: "Temperature, °C"
            }
        }],
        legend: {
            verticalAlignment: "bottom",
            horizontalAlignment: "center"
        },
        title: {
            text: "Temperature and humidity history"
        }
    });
}