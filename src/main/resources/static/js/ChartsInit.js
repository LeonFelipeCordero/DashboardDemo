/**
 * Created by leon on 01/03/2017.
 */
$(document).ready(function () {
    var lineChart = document.getElementById("line");
    if (lineChart != undefined) {
        window.myLine = new Chart(lineChart.getContext("2d")).Line(lineChartData, {responsive: true});
    }
    //
    // var pieChart = document.getElementById("pie");
    // if (pieChart != undefined) {
    //     window.myLine = new Chart(pieChart.getContext("2d")).Pie(pieData, {
    //         responsive: true
    //     });
    // }
    //
    // var donutChart = document.getElementById("dnut");
    // if (donutChart != undefined) {
    //     window.myLine = new Chart(donutChart.getContext("2d")).Doughnut(donutData, {
    //         responsive: true
    //     });
    // }
});



