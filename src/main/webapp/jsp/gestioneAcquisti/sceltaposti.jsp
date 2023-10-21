<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="com.starcinema.starcinema.model.mo.Film" %>
<%@ page import="com.starcinema.starcinema.model.mo.Composizione" %>
<%@ page import="java.util.List" %>
<%@ page import="com.starcinema.starcinema.model.mo.Sala" %>
<%@ page import="com.starcinema.starcinema.model.mo.Posto" %>

<%
  boolean loggedOn = true;
  Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Sceltaposti";

  Utente utente = (Utente) request.getAttribute("utente");
  Film film = (Film) request.getAttribute("film");
  List<Composizione> composizioni = (List<Composizione>) request.getAttribute("composizioni");
  Sala sala = (Sala) request.getAttribute("sala");
%>

<html>
<head>
    <title>Scelta posti</title>
    <%@include file="/include/htmlHead.inc"%>
  <script language="javascript">

    function checkSelections() {
      var selectedCheckboxes = document.querySelectorAll('input[type="checkbox"]:checked');
      if (selectedCheckboxes.length > 5) {
        alert("Puoi selezionare al massimo 5 posti.");
        return false;
      }
      return true;
    }
  </script>
</head>
<body>
<%@include file="/include/headeracq.inc"%>
  <main>
    Sala n. <%=sala.getNum_sala()%>
    Seleziona i posti (Massimo 5 selezioni)
    <br><br>

    <form action="process_selection.jsp" method="post" onsubmit="return checkSelections();">
      <%for (int i = 0; i < composizioni.size(); i++) {%>
      <label class="checkbox-label" title="<%= composizioni.get(i).getPosto().getNum_posto() %>">
      <input type="checkbox" name="posto" value="<%= composizioni.get(i).getPosto().getNum_posto() %>">
      </label>
      <%}%>
      <br><br>
      <input type="submit" value="Conferma selezione">
    </form>
  </main>
</body>
</html>
