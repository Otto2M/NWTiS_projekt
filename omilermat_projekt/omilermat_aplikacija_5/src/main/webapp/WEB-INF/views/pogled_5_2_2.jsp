<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prijava</title>
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
	<header><%="Ime i prezime autora: " + request.getAttribute("autorIme") + " "
    + request.getAttribute("autorPrezime")%><br><%="Predmet: " + request.getAttribute("predmetNaziv")%><br>
    <%="Godina: " + request.getAttribute("godina")%><br>
    <%="Verzija aplikacije: " + request.getAttribute("verzijaAplikacije")%></header>
    
	<div class="poveznica">
	  <a href="${pageContext.servletContext.contextPath}" target="_self">Početna stranica</a><br><br>
	  <a href="${pageContext.servletContext.contextPath}/mvc/korisnici/aktivnosti">Prethodna stranica</a>
	</div>
	
	<h2>Prijava korisnika</h2>
	
	<form method="GET" action="${pageContext.servletContext.contextPath}/mvc/korisnici/prijavljen">
	<div class="labele">
	  <label for="korIme">Korisničko ime</label>
	  <input name="korIme" type="text">
	</div>
	<div class="labele">
	  <label for="lozinka">Lozinka</label>
	  <input name="lozinka" type="password">
	</div>
	<input type="submit" value="Prijava">
	</form>
</body>
</html>