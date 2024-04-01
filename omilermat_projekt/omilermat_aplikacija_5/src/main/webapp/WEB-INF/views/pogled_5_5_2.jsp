<%@page import="org.foi.nwtis.omilermat.projekt.ws.WsMeteo.endpoint.MeteoPodaci"%>
<%@page import="org.foi.nwtis.podaci.UdaljenostAerodrom"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pogled 5.5.2.</title>
<style type="text/css">
	body {
		font-family: Arial, sans-serif;
		background-color: #E4E4E4;
		margin: 0;
		padding: 0;
	}

	h1 {
		margin: 20px 0;
		text-align: center;
		color: #555555;
	}
	
  	a {
	    padding: 10px;
	    margin-top: 5px;
	    background-color: #fff;
	    color: #333;
	    text-decoration: none;
	    border-radius: 10px;
	    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
	    border: 2px solid #A9A9A9;
  	}
  	
	.poveznica {
	  display: flex;
	  justify-content: flex-start;
	  margin-bottom: 20px;
	}
	
	.poveznica a {
	  margin-left: 10px;
	}

	a:hover {
		background-color: #E0E0E0;
	}
	
	table {
	  border-collapse: separate;
	  border-spacing: 3;
	  border-color: #A9A9A9;
	  margin: 0 auto;
	  width: 30%; 
	  background-color: #FFFFFF;
	  border-radius: 10px;
	  overflow: hidden;
	  box-shadow: 0 0 20px rgba(0,0,0,0.1);
	}
	
	th, td {
	  padding: 12px 15px;
	  text-align: center;
	  border-bottom: 1px solid #ECECEC;
	}
	
	th {
	  background-color: #A9A9A9;
	  font-weight: bold;
	  color: #FFFFFF;
	}
	
	tr:hover {
	  background-color: #F7F7F7;
	}
	
	header {
	  text-align: center;
	  margin-bottom: 20px;
	  background-color: #fff;
	  color: #333;
	  padding: 10px;
	  border-radius: 10px;
	  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	}
	
	 .container {
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	  justify-content: center;
	  margin-bottom: 20px;
	}
	
	.oblik {
	  border: 2px solid #555555;
	  border-radius: 10px;
	  padding: 10px;
	  text-align: center;
	  margin-top: 20px;
	}

}
</style>
</head>
<body>
	<header><%="Ime i prezime autora: " + request.getAttribute("autorIme") + " "
    + request.getAttribute("autorPrezime")%><br><%="Predmet: " + request.getAttribute("predmetNaziv")%><br>
    <%="Godina: " + request.getAttribute("godina")%><br>
    <%="Verzija aplikacije: " + request.getAttribute("verzijaAplikacije")%></header>
    
	<h1>Pregled podataka za aerodrom: <%= request.getAttribute("icao") %></h1>
	
	<div class="poveznica">
	  <a href="${pageContext.servletContext.contextPath}" target="_self">Početna stranica</a><br><br>
	  <a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/svi" target="_self"">Povratak</a>
	</div>
	
	<br>
	
	<div class="container">
	  <div class="oblik">
	    <p>Icao: ${aerodrom.icao}</p>
	    <p>Naziv: ${aerodrom.naziv}</p>
	    <p>Država: ${aerodrom.drzava}</p>
	  </div>
	</div>
	
	<br>
	<h1>Meteorološki podaci za <%= request.getAttribute("icao") %></h1>

	<table>
		<thead>
			<tr>
				<th>Status naoblake</th>
				<th>Vrijednost naoblake</th>
				<th>Vlažnost</th>
				<th>Zadnje ažurirano</th>
				<th>Tlak zraka</th>
				<th>Jedinica tlaka zraka</th>
				<th>Izlazak sunca</th>
				<th>Zalazak sunca</th>
				<th>Najviša temperatura</th>
				<th>Najniža temperatura</th>
				<th>Ikona vremena</th>
			</tr>
		</thead>
		<tbody>
		<%
		    MeteoPodaci podaci = (MeteoPodaci) request.getAttribute("meteoPodaci");
		%>
			<tr>
				<td><%= podaci.getCloudsName() %></td>
				<td><%= podaci.getCloudsValue() %></td>
				<td><%= podaci.getHumidityValue() %></td>
				<td><%= podaci.getLastUpdate() %></td>
				<td><%= podaci.getPressureValue() %></td>
				<td><%= podaci.getPressureUnit() %></td>
				<td><%= podaci.getSunRise() %></td>
				<td><%= podaci.getSunSet() %></td>
				<td><%= podaci.getTemperatureMax() %>°C</td>
				<td><%= podaci.getTemperatureMin() %>°C</td>
				<td><img src="http://openweathermap.org/img/w/<%=podaci.getWeatherIcon()%>.png" alt="Ikona"/></td>
			</tr>
		</tbody>
	</table>

</body>
</html>