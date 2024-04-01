<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Komande</title>
<style type="text/css">
	body {
	  font-family: Arial, sans-serif;
	  background-color: #483D8B;
	}
	
	h1 {
	  margin: 20px 0 30px;
	  text-align: center;
	  color: #FFFFFF;
	}
	
	a {
	  display: block;
	  padding: 10px;
	  margin-bottom: 10px;
	  background-color: #fff;
	  color: #333;
	  text-decoration: none;
	  border-radius: 10px;
	  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
	  transition: all 0.3s ease;
	}
	
	a:hover {
	  background-color: #A9A9A9;
	}
	
	div {
	  max-width: 600px;
	  margin: 0 auto;
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
	
	header {
	  text-align: center;
	  margin-bottom: 20px;
	  background-color: #fff;
	  color: #333;
	  padding: 10px;
	  border-radius: 10px;
	  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	}
		
	button {
	  background-color: white;
	  color: #333;
	  padding: 15px 30px;
	  text-align: center;
	  text-decoration: none;
	  display: block;
	  font-size: 16px;
	  margin: 10px auto;
	  cursor: pointer;
	  border-radius: 10px;
	  width: 200px; 
	}
	
	button:hover {
	  background-color: #A9A9A9;
	}
	
	p {
	  padding: 20px;
	  margin: 0 auto;
	  max-width: 800px;
	  background-color: #fff;
	  color: #333;
	  border-radius: 10px;
	  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
	  text-align: center;
	  font-size: 18px;
	}
	
</style>
</head>
<body>
	<header><%="Ime i prezime autora: " + request.getAttribute("autorIme") + " "
    + request.getAttribute("autorPrezime")%><br><%="Predmet: " + request.getAttribute("predmetNaziv")%><br>
    <%="Godina: " + request.getAttribute("godina")%><br>
    <%="Verzija aplikacije: " + request.getAttribute("verzijaAplikacije")%></header>
    
	<h1>Upravljanje komandama</h1>
	
	<div>
	<a href="${pageContext.servletContext.contextPath}" target="_self"  class="nazad">Početna stranica</a><br><br>

	<div class="izgled">
	
		<form method="GET" action="${pageContext.servletContext.contextPath}/mvc/nadzor/">
			<input type="hidden">
			<button type="submit">STATUS</button>
		</form>
	
		<form method="GET" action="${pageContext.servletContext.contextPath}/mvc/nadzor/komanda">
			<input type="hidden" name="komanda" value="INIT">
			<button type="submit">INIT</button>
		</form>
		
		<form method="GET" action="${pageContext.servletContext.contextPath}/mvc/nadzor/komanda">
			<input type="hidden" name="komanda" value="PAUZA">
			<button type="submit">PAUZA</button>
		</form>
		
		<form method="GET" action="${pageContext.servletContext.contextPath}/mvc/nadzor/info">
			<input type="hidden" name="vrsta" value="DA">
			<button type="submit">INFO DA</button>
		</form>
				
		<form method="GET" action="${pageContext.servletContext.contextPath}/mvc/nadzor/info">
			<input type="hidden" name="vrsta" value="NE">
			<button type="submit">INFO NE</button>
		</form>
		
		<form method="GET" action="${pageContext.servletContext.contextPath}/mvc/nadzor/komanda">
			<input type="hidden" name="komanda" value="KRAJ">
			<button type="submit">KRAJ</button>
		</form>

	</div>
	</div>
	
	<p>
	    Poruka:
	    <% if (request.getAttribute("status") == null) { %>
	        Poslužitelj je ugašen!
	    <% } else { %>
	        <%= request.getAttribute("status") %><br>
	    <% } %>
	</p>
	
</body>
</html>