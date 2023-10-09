<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>

<%
    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "AreaRiservata";

    Utente utente = (Utente) request.getAttribute("utente");
%>

<html>
<head>
    <title>Area Utente</title>
    <%@include file="/include/htmlHead.inc"%>
</head>
<body>
<%@include file="/include/header.inc"%>
    <main>
        <%if (loggedUtente != null && loggedUtente.getTipo().equals("amministratore")) { %>
        Benvenuto amministratore
        <br><br>
        <section id="newFilmButtonSection">
            <input type="button" id="newFilmButton" name="newFilmButton"
                   class="button" value="Inserisci nuovo spettacolo"/>
        </section>
        <br><br>
        <section id="saleButtonSection">
            <input type="button" id="saleButton" name="saleButton"
                   class="button" value="Visualizza sale"/>
        </section>
        <br><br>
        <section id="commentiButtonSection">
            <input type="button" id="commentiButton" name="commentiButton"
                   class="button" value="Visualizza commenti degli utenti"/>
        </section>
        <br><br>
        <section id="dateButtonSection">
            <input type="button" id="dateButton" name="dateButton"
                   class="button" value="Visualizza date di proiezione dei film"/>
        </section>
        <%} else if(loggedUtente != null && loggedUtente.getTipo().equals("utente")) { %>
        <br><br>
        <section id="modacqButtonSection">
            <input type="button" id="modacqButton" name="modacqButton"
                   class="button" value="Modifica acquisti"/>
        </section>
        <%}%>

    </main>
</body>
</html>
