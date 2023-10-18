<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="com.starcinema.starcinema.model.mo.Film" %>

<%
  boolean loggedOn = true;
  Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Sceltaposti";

  Utente utente = (Utente) request.getAttribute("utente");
  Film film = (Film) request.getAttribute("film");
%>

<html>
<head>
    <title>Scelta posti</title>
    <%@include file="/include/htmlHead.inc"%>
  <script language="javascript">



  </script>
</head>
<body>
<%@include file="/include/headeracq.inc"%>
  <main>

    posti
  </main>
</body>
</html>
