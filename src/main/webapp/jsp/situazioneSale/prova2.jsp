<%@page session="false"%>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.starcinema.starcinema.model.mo.*" %>

<%
  boolean loggedOn = true;
  Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Situazionesale";

  Utente utente = (Utente) request.getAttribute("utente");
  Integer num_sala = (Integer) request.getAttribute("num_sala");
  List<Composizione> composizioni = (List<Composizione>) request.getAttribute("composizioni");
  List<Sala> sale = (List<Sala>) request.getAttribute("sale");
%>

<html>
<head>
  <title>Area Utente</title>
  <%@include file="/include/htmlHead.inc"%>
  <script language="javascript">



  </script>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>


  <section id="finddata" class="clearfix">
    <form name="finddataForm" action="Dispatcher" method="post">

      <label for="Num_salaMenu">Inserisci numero sala</label>
      <select id="Num_salaMenu" name="num_sala">
        <option>Seleziona una sala</option>
        <%if (sale != null) {%>
        <%for (int c = 0; c < sale.size(); c++) {%>
        <option value="<%=sale.get(c).getNum_sala()%>"><%=sale.get(c).getNum_sala()%></option>
        <%}%><%}%>
      </select><br>


      <label for="data_pro">Cerca per data di calendario</label>
      <input type="date" id="data_pro" name="data_pro">

      <label for="ora_pro">Cerca per ora di calendario</label>
      <input type="time" id="ora_pro" name="ora_pro">




      <input type="submit" value="Cerca">
      <input type="hidden" name="controllerAction" value="SituazioneSale.cercaSala">
    </form>
  </section>


  <%if (num_sala != null) { %>
  Sala n. <%=num_sala%>
  <%}%>

  <br><br>

  <%if (composizioni != null) { %>
  <%for (int i = 0; i < composizioni.size(); i++) {%>
  <label class="checkbox-label" title="<%= composizioni.get(i).getPosto().getNum_posto() %>">
    <input type="checkbox" name="selectedposti" value="<%= composizioni.get(i).getPosto().getNum_posto() %>"
            <% if (composizioni.get(i).isOccupato()) { %>
           disabled="disabled"
  <% } %>
  <% } %>
  <% } %>

</main>
</body>
</html>
