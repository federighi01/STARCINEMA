<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="com.starcinema.starcinema.model.mo.Film" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.starcinema.starcinema.model.mo.Proiezione" %>
<%@ page import="java.util.Date" %>

<%  int i = 0;

  String formattedTime;
  String data_pro=null;

  boolean loggedOn = true;
  Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Situazionesale";

  Utente utente = (Utente) request.getAttribute("utente");
  List<Film> films = (List<Film>) request.getAttribute("films");
  String formattedDate = (String) request.getAttribute("formattedDate");
%>

<html>
<head>
  <title>Area Utente</title>
  <%@include file="/include/htmlHead.inc"%>
  <script language="javascript">

    function menuData(selectedcodfilm){
      var selectedDateElement = document.getElementById("dataProMenu");
      var formattedDate = selectedDateElement.value;
      if (formattedDate != null) {
        document.menuDataForm.selectedcodfilm.value = selectedcodfilm;
        document.menuDataForm.formattedDate.value = formattedDate;
        document.menuDataForm.submit();
      }
    }

  </script>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>


  <section id="datiacqFormSection">
    <form name="datiacqForm" action="Dispatcher" method="post">
      <!-- Menu a tendina per data_pro -->
      <label for="dataProMenu">Seleziona Data di Proiezione:</label>
      <select id="dataProMenu" name="formattedDate" onchange="menuData(<%=films.get(i).getCod_film()%>)">
        <%if (formattedDate != null){%>
        <option value="<%=formattedDate%>"><%=formattedDate%></option><%} else {%>
        <option>Seleziona una data</option>

        <%for (i = 0; i < films.size(); i++) {%>

        <%
          Date lastDataPro = null; // Memorizza l'ultima data_pro stampata
        %>
        <% for (int c = 0; c < films.get(i).getProiezioni().length; c++) {
          Proiezione proiezione = films.get(i).getProiezioni(c);
          Date dataPro = proiezione.getData_pro();

          // Controlla se la data_pro è diversa dall'ultima data_pro stampata
          if (lastDataPro == null || !lastDataPro.equals(dataPro)) {
            // Memorizza la nuova data_pro
            lastDataPro = dataPro;
        %>
        <%
          SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
          if (dataPro != null) {
            data_pro = dateFormat.format(dataPro);
          }
        %>
        <option value="<%= data_pro %>"><%= data_pro %></option>
        <%}%>
        <%}%>
        <%}%>
        <%}%>
      </select>

      <!-- Menu a tendina per ora_pro -->
      <label for="oraProMenu">Seleziona Ora di Proiezione:</label>
      <select id="oraProMenu">
        <%for (i = 0; i < films.size(); i++) {%>
        <% if (films.get(i).getProiezioni() != null) { %>
        <% for (int c = 0; c < films.get(i).getProiezioni().length; c++) {
          Proiezione proiezione = films.get(i).getProiezioni(c);

          SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
          formattedTime = timeFormat.format(proiezione.getOra_pro());
        %>
        <option value="<%= formattedTime %>"><%= formattedTime %></option>
        <%}%><%}%><%}%>
      </select>
    </form>
  </section>


  <form name="menuDataForm" method="post" action="Dispatcher">
    <input type="hidden" name="selectedcodfilm"/>
    <input type="hidden" name="formattedDate"/>
    <input type="hidden" name="controllerAction" value="SituazioneSale.menuData"/>
  </form>


</main>
</body>
</html>
