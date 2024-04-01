<%@page import="org.foi.nwtis.podaci.Dnevnik"%>
<%@page import="org.foi.nwtis.Konfiguracija"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dnevnik</title>
<style type="text/css">
	body {
	  font-family: Arial, sans-serif;
	  background-color: #F7F7F7;
	}
	
	h1 {
	  margin: 20px 0 30px;
	  text-align: center;
	  color: #555555;
	}
	
	div {
	  max-width: 600px;
	  margin: 0 auto;
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
	  border-bottom: 2px solid #ECECEC;
	}
	
	th {
	  background-color: #F7F7F7;
	  font-weight: bold;
	  color: #555555;
	}
	
	tr:hover {
	  background-color: #F7F7F7;
	}
	
	.izgled {
	  display: flex;
	  justify-content: space-between;
	  align-items: center;
	  margin-top: 20px;
	}
	.izgled form button {
	  background-color: #483D8B;
	  color: white;
	  padding: 10px;
	  border: none;
	  border-radius: 5px;
	  cursor: pointer;
	  transition: background-color 0.3s ease-in-out;
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
	
 	select { 
 	  display: block; 
 	  padding: 10px; 
 	  margin-bottom: 10px; 
 	  background-color: #E0E0E0; 
 	  color: #333333; 
 	  border: none; 
 	  border-radius: 10px; 
 	  width: 200px; 
 	  font-size: 16px; 
 	  line-height: 1.5; 
 	  box-sizing: border-box; 
 	} 
	
 	select:hover { 
 	  background-color: #9C9C9C; 
 	  color: #FFFFFF; 
 	} 
	
 	select:focus { 
 	  outline: none; 
 	  background-color: #9C9C9C; 
 	  color: #FFFFFF; 
 	} 
	
	.nazad {
	  display: block;
	  padding: 10px;
	  margin-bottom: 10px;
	  background-color: #333;
	  color: #fff;
	  text-decoration: none;
	  border-radius: 10px;
	  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	  transition: all 0.3s ease;
	}
	
	.nazad:hover {
	  background-color: #A9A9A9;
	}
	
	.filteri {
	  max-width: 600px;
	  margin: 0 auto;
	  background-color: #fff;
	  padding: 20px;
	  border-radius: 10px;
	  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
	  margin-top: 20px;
	}
	
	.filteri form {
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	}
	
	.filteri .labele {
	  margin-bottom: 20px;
	}
	
	.filteri .labele label {
	  display: block;
	  margin-bottom: 5px;
	  color: #333;
	  font-weight: bold;
	}
	
	.filteri .labele select {
	  width: 100%;
	  padding: 10px;
	  border-radius: 5px;
	  border: 2px solid #A9A9A9;
	  font-size: 16px;
	  box-sizing: border-box;
	  margin-bottom: 10px;
	}
	
	.filteri button[type="submit"] {
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
	

</style>
</head>
<body>

	<header><%="Ime i prezime autora: " + request.getAttribute("autorIme") + " "
    + request.getAttribute("autorPrezime")%><br><%="Predmet: " + request.getAttribute("predmetNaziv")%><br>
		<%="Godina: " + request.getAttribute("godina")%><br>
		<%="Verzija aplikacije: " + request.getAttribute("verzijaAplikacije")%></header>
		
	<h1>Pregled zapisa iz dnevnika</h1>
	
	<div>
	<a href="${pageContext.servletContext.contextPath}" target="_self" class="nazad">Početna stranica</a>
	</div>
	<br>

	<br>
	
	<div class=filteri>
	<form method="GET">
	    <select name="vrsta">
	    	<option></option>
	        <option value="AP2">AP2</option>
	        <option value="AP4">AP4</option>
	        <option value="AP5">AP5</option>
	    </select>
	    <button type="submit">Filtriraj</button>
	</form>
	</div>
	<br>
	
	<%
	int odBroja = (int) request.getAttribute("odBroja");
	int broj = (int) request.getAttribute("broj");
	String vrsta = (String) request.getAttribute("vrsta");
	%>

	<table>
		<thead>
			<tr>
				<th>Radnja</th>
				<th>Tip metode</th>
				<th>Vrsta</th>
				<th>Datum i vrijeme zapisa</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<Dnevnik> dnevnikZapisi = (List<Dnevnik>) request.getAttribute("dnevnik");
			for (Dnevnik dnevnik : dnevnikZapisi) {
			%>
			<tr>
				<td><%=dnevnik.getRadnja()%></td>
				<td><%=dnevnik.getTipMetode()%></td>
				<td><%=dnevnik.getVrsta()%></td>
				<td><%=dnevnik.getDatumVrijeme()%></td>
			</tr>
			<%
			}
			%>

		</tbody>
	</table>

	<div class="izgled">
		<%
		if(vrsta == null) {  
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
			<input type="hidden" name="vrsta" value="<%=vrsta%>">
			<button type="submit">Sljedeća stranica</button>
		</form>
			   <form method="GET">
			<input type="hidden" name="odBroja" value="<%=1%>">
			<input type="hidden" name="broj" value="<%=broj%>">
			<input type="hidden" name="vrsta" value="<%=vrsta%>">
			<button type="submit">Početak</button>
	   </form>

		<form method="GET">
			<input type="hidden" name="odBroja" value="<%=odBroja - 1%>"> 
			<input type="hidden" name="broj" value="<%=broj%>">
			<input type="hidden" name="vrsta" value="<%=vrsta%>">
			<%
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