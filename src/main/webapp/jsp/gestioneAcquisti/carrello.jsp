<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="com.starcinema.starcinema.model.mo.Composizione" %>
<%@ page import="java.util.List" %>


<%
    boolean loggedOn = true;
    Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    String menuActiveLink = "Carrello";

    Utente utente = (Utente) request.getAttribute("utente");
    List<Composizione> composizioni = (List<Composizione>) request.getAttribute("composizioni");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
    <%@include file="/include/htmlHead.inc"%>
    <script language="javascript">




    </script>
</head>
<body>
<%@include file="/include/headeracq.inc"%>
  <main>

      <% if (composizioni != null) { %>
      <% for (int i = 0; i < composizioni.size(); i++) { %>
      <%= composizioni.get(i).getPosto().getNum_posto() %>
      <% } %>
      <% } %>
      <% if (composizioni == null) { %>
        non ci sono elementi nel carrello :(
      <% } %>
  </main>
</body>
</html>
