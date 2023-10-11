<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="com.starcinema.starcinema.model.mo.Film" %>

<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "SchedaFilm";

    Film film = (Film) request.getAttribute("film");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@include file="/include/htmlHead.inc"%>
</head>
<body>

    <main>
        <h1>Scheda Film</h1>
        <p>ID del Film: <%= film.getCod_film()%></p>
        <p>Titolo del Film: <%= film.getTitolo() %></p>
    </main>
</body>
</html>
