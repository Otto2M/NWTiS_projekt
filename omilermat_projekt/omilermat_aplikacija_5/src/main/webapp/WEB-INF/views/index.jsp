
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Početna</title>
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
	
	header {
	  text-align: center;
	  margin-bottom: 20px;
	  background-color: #fff;
	  color: #333;
	  padding: 10px;
	  border-radius: 10px;
	  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	}
	
</style>
</head>
<body>	
	<header>Ime i prezime autora: Otto Miler Matulin<br>Predmet: NWTiS<br>
	Godina: 2023.<br>Verzija aplikacije: 1.23</header>
		
	<h1>Početni izbornik</h1>
	
	<div>
	<a href="${pageContext.servletContext.contextPath}/mvc/korisnici/aktivnosti">Pregled aktivnosti vezanih uz korisnika</a><br>
	<a href="${pageContext.servletContext.contextPath}/mvc/nadzor">Komande za upravljanje poslužiteljem</a><br>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/aktivnosti">Pregled aktivnosti vezanih uz aerodrome</a><br>
<%-- 	<a href="${pageContext.servletContext.contextPath}/mvc/letovi/">Pregled aktivnosti vezanih uz letove</a><br> --%>
	<a href="${pageContext.servletContext.contextPath}/mvc/dnevnik">Pregled zapisa dnevnika</a><br>
	</div>
	
</body>
</html>