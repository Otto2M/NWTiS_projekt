<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prgled aktivnosti - aerodromi</title>
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
	  display: flex;
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
	
	a, div {
	  text-align: center;
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
	
</style>
</head>
<body>
	<h1>Aktivnosti vezane uz aerodrome</h1>
	<div>
	<a href="${pageContext.servletContext.contextPath}" target="_self"  class="nazad">Početna stranica</a><br><br>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/svi">Pogled 5.5.1. - pregled svih aerodroma uz filtriranje</a><br>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/polasci">Pogled 5.5.3. - pregled aerodroma za koje se za preuzimaju podaci o polascima</a><br>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/traziUdaljenostDvaAerodromaDrzava">Pogled 5.5.4. - pregled udaljenosti između dva aerodroma unutar država preko kojih se leti i ukupna udaljenost</a><br>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/traziUdaljenostDvaAerodroma">Pogled 5.5.5. - pregled udaljenosti između dva aerodroma</a><br>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/traziIcaoOdDo">Pogled 5.5.6</a><br>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/traziDrzavaKm">Pogled 5.5.7. - pregled aerodroma i udaljenosti do polaznog aerodroma unutar zadane države koje su manje od zadane udaljenosti</a><br>
	</div>
</body>
</html>