<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Forelesningsoversikt DAT104 - Demo</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous">
    </script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js">
    </script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Stemt', 'Prosent stemmer'],
          ['Fornøyd', <%=request.getAttribute("smile")%>;],
          ['Nokså fornøyd', <%=request.getAttribute("indifferent")%>],
          ['Ikke fornøyd',<%=request.getAttribute("sad")%>],
        ]);

        var options = {
          title: '',
          legend: 'none',
          slices: {
            0: { color: '#21ba45'},
            1: { color: '#fbbd08'},
            2: { color: '#db2828'}
          }
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
</head>
<body>
	<!-- <div class="ui hidden divider"></div> -->
	<div class="ui container">
		<form action=Login method="GET">
			<input type="hidden" name="logout" value=true> <input
				type="submit" value="Logg ut"
				class="small negative ui labeled icon button" style="float: right;">
		</form>
		<div class="ui huge header">${fag.fagkode} ${fag.beskrivelse}</div>
		<div class="ui header">${forelesning.tidsbeskrivelse}</div>
		<div class="ui raised segment">
			<div class="ui header">Underveis i forelesning</div>
			<div class="ui small statistics">
				<div class="green statistic">
					<div class="value">${fornoydUnderveis}%</div>
					<div class="label">Fornøyd</div>
				</div>
				<div class="yellow statistic">
					<div class="value">${noksofornoydUnderveis}%</div>
					<div class="label">Nokså fornøyd</div>
				</div>
				<div class="red statistic">
					<div class="value">${ikkefornoydUnderveis}%%</div>
					<div class="label">Ikke fornøyd</div>
				</div>
				<div class="statistic">
					<div class="value" style="color: rgb(201, 188, 21);">${samletUnderveis}%</div>
					<div class="label">Samlet</div>
				</div>
			</div>
			<div class="ui divider"></div>
			<div class="ui header">Etter forelesning</div>
			<div class="ui small statistics">
				<div class="green statistic">
					<div class="value">${fornoydEtter}%</div>
					<div class="label">Fornøyd</div>
				</div>
				<div class="yellow statistic">
					<div class="value">${noksofornoydEtter}%</div>
					<div class="label">Nokså fornøyd</div>
				</div>
				<div class="red statistic">
					<div class="value">${ikkefornoydEtter}%%</div>
					<div class="label">Ikke fornøyd</div>
				</div>
				<div class="statistic">
					<div class="value" style="color: rgb(201, 188, 21);">${samletEtter}%</div>
					<div class="label">Samlet</div>
				</div>
			</div>
		</div>
	</div>
	<div id="piechart" style=""></div>
	<script type="text/javascript">
// Kilde: https://stackoverflow.com/a/16252776
function getColorForPercentage(pct) {
  pct /= 100;

  const percentColors = [
      { pct: 0.01, color: { r: 0xdb, g: 0x28, b: 0x28 } }, // Rød
      { pct: 0.5, color: { r: 0xfb, g: 0xbd, b: 0x08 } },  // Gul
      { pct: 1.0, color: { r: 0x21, g: 0xba, b: 0x45 }} ]; // Grønn

  for (let i = 0; i < percentColors.length; i++) {
    if (pct <= percentColors[i].pct) {
      let lower = percentColors[i - 1] || { pct: 0.1, color: { r: 0x0, g: 0x00, b: 0 } };
      let upper = percentColors[i];
      let range = upper.pct - lower.pct;
      let rangePct = (pct - lower.pct) / range;
      let pctLower = 1 - rangePct;
      let pctUpper = rangePct;
      let color = {
        r: Math.floor(lower.color.r * pctLower + upper.color.r * pctUpper),
        g: Math.floor(lower.color.g * pctLower + upper.color.g * pctUpper),
        b: Math.floor(lower.color.b * pctLower + upper.color.b * pctUpper)
      };
      return 'rgb(' + [color.r, color.g, color.b].join(',') + ')';
    }
  }
} 
    </script>
</body>
</html>
