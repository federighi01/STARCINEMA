<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>

<%
    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "AcqCompletato";

    Utente utente = (Utente) request.getAttribute("utente");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Acquisto completato</title>
    <%@include file="/include/htmlHead.inc"%>
</head>
<body>
<main>
    <section id="AcqCompletatoFormSection">
        <form name="AcqCompletatoForm" action="Dispatcher" method="post">
            <p>Acquisto effettuato con successo!</p><br>
            <a href="Dispatcher?controllerAction=HomeManagement.view">
                <input <%=menuActiveLink.equals("Home")?"class=\"active\"":""%>
                        type="submit" class="button" value="Torna alla HOME"/>
            </a>
        </form>
    </section>
</main>
</body>
</html>
