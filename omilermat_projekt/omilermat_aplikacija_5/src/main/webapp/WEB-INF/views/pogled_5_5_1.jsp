<%@page import="org.foi.nwtis.omilermat.projekt.ws.WsAerodromi.endpoint.Aerodrom"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pogled 5.5.1.</title>
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
	
	.labele input[type="text"],
	.labele input[type="password"] {
	  width: 100%;
	  padding: 10px;
	  border-radius: 5px;
	  border: 2px solid #A9A9A9;
	  font-size: 16px;
	  box-sizing: border-box;
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

	.izgled2 {
	  display: flex;
	  justify-content: space-between;
	  align-items: center;
	  margin-top: 20px;
	}
	.izgled2 form button {
	  background-color: #483D8B;
	  color: white;
	  padding: 10px;
	  border: none;
	  border-radius: 5px;
	  cursor: pointer;
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
	
	<h2>Pregled svih aerodroma</h2>
	
	<div class="izgled">
		<h4>Filtriranje aerodroma</h4>
		<form method="GET">
			<div class="labele">
			  <label for="traziNaziv">Naziv aerodroma</label>
			  <input name="traziNaziv" type="text">
			</div>
			<div class="labele">
			  <label for="traziDrzavu">Oznaka države</label>
			  <input name="traziDrzavu" type="text">
			</div>
			<button type="submit">Traži</button>
		</form>
	</div>
	
	<%
	int odBroja = (int) request.getAttribute("odBroja");
	int broj = (int) request.getAttribute("broj");
	String traziNaziv = (String) request.getAttribute("traziNaziv");
	String traziDrzavu = (String) request.getAttribute("traziDrzavu");
	%>
	<table>
		<thead>
			<tr>
				<th>Icao</th>
				<th>Naziv</th>
				<th>Drzava</th>
				<th>Geografska širina</th>
				<th>Geografska dužina</th>
				<th>Preuzimanje letova</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<Aerodrom> sviAerodromi = (List<Aerodrom>) request.getAttribute("sviAerodromi");
			for (Aerodrom aerodrom : sviAerodromi) {
			%>
			<tr>
				<td><a
					href="<%=request.getContextPath()%>/mvc/aerodromi/icaoPodaci?icao=<%=aerodrom.getIcao()%>"><%=aerodrom.getIcao()%></a></td>
				<td><%=aerodrom.getNaziv()%></td>
				<td><%=aerodrom.getDrzava()%></td>
				<td><%=aerodrom.getLokacija().getLatitude()%></td>
				<td><%=aerodrom.getLokacija().getLongitude()%></td>
				<td><a
					href="<%=request.getContextPath()%>/mvc/aerodromi/dodajLetZaPreuzimanje?icao=<%=aerodrom.getIcao()%>">Dodaj aerodrom</a></td>
			</tr>
			<%
			}
			%>

		</tbody>
	</table>
	
	<div class=izgled2>
		<%
		if (traziNaziv == null && traziDrzavu == null) {  
		%>
		<form method="GET">
			<input type="hidden" name="odBroja" value="<%=odBroja + 1%>"> 
			<input type="hidden" name="broj" value="<%=broj%>">
			<button type="submit">Sljedeća stranica</button>
		</form>
		
		<form method="GET">
			<input type="hidden" name="odBroja" value="<%=1%>">
			<input type="hidden" name="broj" value="<%=broj%>">
			<button type="submit">Početak</button>
	   </form>
	
		<form method="GET">
			<input type="hidden" name="odBroja" value="<%=odBroja - 1%>"> 
			<input type="hidden" name="broj" value="<%=broj%>">
			<%
				if (odBroja > 1) {
			%>
			<button type="submit">Prethodna stranica</button>
			<%
				}
			%>
		</form>
		<% 
		} else {
		  %>
		<form method="GET">
			<input type="hidden" name="odBroja" value="<%=odBroja + 1%>"> 
			<input type="hidden" name="broj" value="<%=broj%>">
			<%
				if (traziNaziv != null) {
			%>
			<input type="hidden" name="traziNaziv" value="<%=traziNaziv%>">
			<%
				} if (traziDrzavu != null) {
			%>
			<input type="hidden" name="traziDrzavu" value="<%=traziDrzavu%>">
			<%
				}
			%>
			<button type="submit">Sljedeća stranica</button>
		</form>
	   <form method="GET">
			<input type="hidden" name="odBroja" value="<%=1%>">
			<input type="hidden" name="broj" value="<%=broj%>">
			<%
				if (traziNaziv != null) {
			%>
			<input type="hidden" name="traziNaziv" value="<%=traziNaziv%>">
			<%
				} if (traziDrzavu != null) {
			%>
			<input type="hidden" name="traziDrzavu" value="<%=traziDrzavu%>">
			<%
				}
			%>
			<button type="submit">Početak</button>
	   </form>
	
		<form method="GET">
			<input type="hidden" name="odBroja" value="<%=odBroja - 1%>"> 
			<input type="hidden" name="broj" value="<%=broj%>">
			<%
				if (traziNaziv != null) {
			%>
			<input type="hidden" name="traziNaziv" value="<%=traziNaziv%>">
			<%
				} if (traziDrzavu != null) {
			%>
			<input type="hidden" name="traziDrzavu" value="<%=traziDrzavu%>">
			<%
				}
				if (odBroja > 1) {
			%>
			<button type="submit">Prethodna stranica</button>
			<%
				}
			%>
		</form>
		<% } %>
	</div>

	
	
</body>
</html>