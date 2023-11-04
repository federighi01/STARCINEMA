<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>

<%
  boolean loggedOn = true;
  Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "ModCompletata";

  Utente utente = (Utente) request.getAttribute("utente");
%>

<!DOCTYPE html>
<html>
<head>
  <title>Modifica completata</title>
  <%@include file="/include/htmlHead.inc"%>
</head>
<body>
<main>
  <section id="ModCompletataFormSection">
    <form name="ModCompletataForm" action="Dispatcher" method="post">
      <p>Modifica effettuata con successo!</p><br>
      <a href="Dispatcher?controllerAction=HomeManagement.view">
        <input <%=menuActiveLink.equals("Home")?"class=\"active\"":""%>
                type="submit" class="button" value="Torna alla HOME"/>
      </a>
    </form>
  </section>
</main>
</body>
</html>
