<%@page import="java.util.Map"%>
<%@page import="org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.Aerodrom"%>
<%@page import="java.util.List"%>
<%@page import="org.foi.nwtis.omilermat.projekt.ws.WsKorisnici.endpoint.Korisnik"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pregled aerodroma</title>
<style type="text/css">
	body {
	  font-family: Arial, sans-serif;
	  background-color: #483D8B;
	}
	
	h2 {
	  margin: 20px 0 30px;
	  text-align: center;
	  color: #FFFFFF;
	}
	
	form {
	  max-width: 600px;
	  margin: 0 auto;
	  background-color: #fff;
	  padding: 20px;
	  border-radius: 10px;
	  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
	}

	
 	input[type="submit"] {
 	  display: block;
 	  margin: 20px auto;
 	  padding: 10px 20px;
 	  background-color: #A9A9A9;
 	  color: #fff;
 	  border: none;
 	  border-radius: 5px;
 	  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
 	  font-size: 16px; 
 	  cursor: pointer;
 	  transition: all 0.3s ease;
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
	
	.labele {
	  margin-bottom: 20px;
	}
	
	.labele label {
	  display: block;
	  margin-bottom: 5px;
	  color: #333;
	  font-weight: bold;
	}
	
	.labele input[type="text"],
	.labele input[type="password"] {
	  width: 100%;
	  padding: 10px;
	  border-radius: 5px;
	  border: 2px solid #A9A9A9;
	  font-size: 16px;
	  box-sizing: border-box;
	}
	
	.gumb {
	  display: block;
	  margin: 20px auto;
	  padding: 10px 20px;
	  background-color: #A9A9A9;
	  color: #fff;
	  border: none;
	  border-radius: 5px;
	  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
	  font-size: 16px;
	  cursor: pointer;
	  transition: all 0.3s ease;
	}
	
	.gumb:hover {
	  background-color: #808080;
	}
	
	table {
	  border-collapse: collapse;
	  margin: 0 auto;
	  width: 80%;
	  background-color: #FFFFFF;
	  border-radius: 10px;
	  overflow: hidden;
	  box-shadow: 0 0 20px rgba(0,0,0,0.1);
	}
	
	th, td {
	  padding: 12px 15px;
	  text-align: left;
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
	
		.izgled {
	  max-width: 600px;
	  margin: 0 auto;
	  background-color: #fff;
	  padding: 20px;
	  border-radius: 10px;
	  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
	  margin-top: 20px;
	}
	
	.izgled .labele {
	  margin-bottom: 20px;
	}
	
	.izgled .labele label {
	  display: block;
	  margin-bottom: 5px;
	  color: #333;
	  font-weight: bold;
	}
	
	.izgled .labele input[type="text"] {
	  width: 100%;
	  padding: 10px;
	  border-radius: 5px;
	  border: 2px solid #A9A9A9;
	  font-size: 16px;
	  box-sizing: border-box;
	}
	
	.izgled button[type="submit"] {
	  display: block;
	  margin: 20px auto;
	  padding: 10px 20px;
	  background-color: #A9A9A9;
	  color: #fff;
	  border: none;
	  border-radius: 5px;
	  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
	  font-size: 16px;
	  cursor: pointer;
	  transition: all 0.3s ease;
	}
	
	.izgled h4 {
	  margin: 20px 0;
	  color: #483D8B;
	  text-align: center;
	  font-size: 24px;
	}
	
</style>
</head>
<body>
	<header><%="Ime i prezime autora: " + request.getAttribute("autorIme") + " "
    + request.getAttribute("autorPrezime")%><br><%="Predmet: " + request.getAttribute("predmetNaziv")%><br>
    <%="Godina: " + request.getAttribute("godina")%><br>
    <%="Verzija aplikacije: " + request.getAttribute("verzijaAplikacije")%></header>
    
	<div class="poveznica">
	  <a href="${pageContext.servletContext.contextPath}" target="_self">Početna stranica</a><br><br>
	  <a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/aktivnosti">Prethodna stranica</a>
	</div>
	
	<h2>Pregled aerodroma za koje se preuzimaju podaci o letovima</h2>
	
	<%
	List<Aerodrom> sviAerodromi = (List<Aerodrom>) request.getAttribute("aerodromi");
	List<String> statusi = (List<String>) request.getAttribute("statusPreuzimanja");
	%>
	<table>
	  <thead>
	    <tr>
	      <th>Icao</th>
	      <th>Naziv</th>
	      <th>Drzava</th>
	      <th>Geografska širina</th>
	      <th>Geografska dužina</th>
	      <th>Status (Aktivan/Pauza)</th>
	      <th>Aktiviraj</th>
	      <th>Pauziraj</th>
	    </tr>
	  </thead>
	  <tbody>
	    <%
	    for (int i = 0; i < sviAerodromi.size(); i++) {
	      Aerodrom aerodrom = sviAerodromi.get(i);
	      String status = statusi.get(i);
	      String statusOznaka = status.equals("TRUE") ? "Aktivan" : "Pauziran";
	    %>
	    <tr>
	      <td><%= aerodrom.getIcao() %></td>
	      <td><%= aerodrom.getNaziv() %></td>
	      <td><%= aerodrom.getDrzava() %></td>
	      <td><%= aerodrom.getLokacija().getLatitude() %></td>
	      <td><%= aerodrom.getLokacija().getLongitude() %></td>
	      <td><%= statusOznaka %></td>
	      <td><a href="<%= request.getContextPath() %>/mvc/aerodromi/aktiviraj?icao=<%= aerodrom.getIcao() %>">Aktiviraj</a></td>
	      <td><a href="<%= request.getContextPath() %>/mvc/aerodromi/pauziraj?icao=<%= aerodrom.getIcao() %>">Pauziraj</a></td>
	    </tr>
	    <%
	    }
	    %>
	  </tbody>
	</table>

	
</body>
</html>