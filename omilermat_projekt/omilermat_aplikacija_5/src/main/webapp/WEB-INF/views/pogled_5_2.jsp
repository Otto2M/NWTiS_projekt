<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pregled aktivnosti - korisnik</title>
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
	
	.poruka {	
		background-color: #f2f2f2;
	  padding: 10px;
	  margin-bottom: 20px;
	}
	
	p {
	  font-size: 16px;
	  color: #333333;
	  margin: 0;
	}
	
	.obavijest {
	  color: #ff0000;
	  font-weight: bold;
	}
	
	.prijavljen {
		color: #32CD32;
	  font-weight: bold;
	}
	
	.poruka {
	  background-color: #F8F8F8;
	  color: #32CD32;
	  padding: 20px;
	  margin-top: 50px;
	  margin-bottom: 30px;
	  border-radius: 10px;
	  text-align: center;
	}
	
	.poruka p {
	  margin-bottom: 10px;
	}
		
</style>
</head>
<body>
	<h1>Aktivnosti vezane uz korisnike</h1>
	<% String korIme = (String) request.getAttribute("korIme"); %>
	<div>
	<a href="${pageContext.servletContext.contextPath}" target="_self"  class="nazad">Početna stranica</a><br><br>
	<a href="${pageContext.servletContext.contextPath}/mvc/korisnici/registracija">Registracija</a><br>
	<% if(korIme == null) { %>
		<a href="${pageContext.servletContext.contextPath}/mvc/korisnici/prijava">Prijava</a><br>
	<% } %>
	<% if(korIme != null) { %>
		<a href="${pageContext.servletContext.contextPath}/mvc/korisnici/svi">Pregled svih korisnika</a><br>
		<a href="${pageContext.servletContext.contextPath}/mvc/korisnici/odjava">Odjava</a><br>
	<% } %>
	
	</div>
	
	<div class="poruka">
	  <% if (korIme == null) { %>
	    <p class="obavijest">Korisnik nije prijavljen!</p>
	  <% } else { %>
	    <p class="prijavljen">Korisničko ime prijavljenog korisnika: <%= korIme %></p>
	  <% } %>
	</div>

</body>
</html>