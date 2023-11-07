<%@page session="false"%>
<%@ page import="java.util.List" %>
<%@ page import="com.starcinema.starcinema.model.mo.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<%
  String formattedDate=null;
  String formattedTime=null;

  boolean loggedOn = true;
  Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Sceltaposti";

  Utente utente = (Utente) request.getAttribute("utente");
  Film film = (Film) request.getAttribute("film");
  Proiezione proiezione = (Proiezione) request.getAttribute("proiezione");
  List<Composizione> composizioni = (List<Composizione>) request.getAttribute("composizioni");
  Sala sala = (Sala) request.getAttribute("sala");
  Biglietto biglietto = (Biglietto) request.getAttribute("biglietto");
  Abbonamento abbonamento = (Abbonamento) request.getAttribute("abbonamento");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Scelta posti</title>
    <%@include file="/include/htmlHead.inc"%>

  <style>
    #checkboxContainer {
      background-color: black;
      display: flex;
      flex-direction: column;
      align-items: center;
      height: 350px; /* Imposta un'altezza fissa in pixel per il riquadro */
      max-width: 500px;
      margin: 0 auto;
      text-align: center;
      position: relative;
    }

    /* Stile per i checkbox */
    .checkbox-label input[type="checkbox"] {
      margin-top: 10px; /* Modifica il valore 10px a seconda dell'aspetto desiderato */
    }

    /* Stile per l'immagine SCHERMO */
    #schermo {
      align: center;
      top: 100%; /* Posiziona l'immagine al centro verticalmente */
      left: 80%; /* Posiziona l'immagine al centro orizzontalmente */
      transform: translate(-0%, -50%); /* Centra l'immagine in base alle dimensioni */
      max-width: 80%; /* Imposta la larghezza massima dell'immagine */
      max-height: 50%; /* Imposta l'altezza massima dell'immagine */
    }

  </style>

  <script language="javascript">

    function checkSelections() {
      var selectedCheckboxes = document.querySelectorAll('input[type="checkbox"]:checked');
      if (selectedCheckboxes.length > 5) {
        alert("Puoi selezionare al massimo 5 posti.");
        return false;
      }
      if (selectedCheckboxes.length < 1) {
        alert("Seleziona almeno un posto.");
        return false;
      }
      return true;
    }

  </script>
</head>
<body>
<%@include file="/include/headeracq.inc"%>
  <main>
    <%
      if(proiezione != null) {
        Date dataPro = proiezione.getData_pro();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = dateFormat.format(dataPro);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        formattedTime = timeFormat.format(proiezione.getOra_pro());
      }
    %>


    <%if (sala != null) { %>
    Sala n. <%=sala.getNum_sala()%>
    <%}%>
    Seleziona i posti (Massimo 5 selezioni)
    <br><br>

  <section id="postiFormSection">
    <div id="checkboxContainer">
    <form name="postiForm" action="Dispatcher" method="post" onsubmit="return checkSelections();">

      <% int d = 0; %>
      <%for (int i = 0; i < composizioni.size(); i++) {%>
      <% d++; %>
      <label class="checkbox-label" title="<%= composizioni.get(i).getPosto().getNum_posto() %>">
        <input type="checkbox" name="selectedposti" value="<%= composizioni.get(i).getPosto().getNum_posto() %>"
          <% if (composizioni.get(i).isOccupato()) { %>
               disabled="disabled"
          <% } %>
          <!-- Vai a capo dopo ogni 20 checkbox -->
          <% if ((i + 1) % 20 == 0) { %><br/><% } %>
        <!-- Aggiungi uno spazio tra il quarto e il quinto checkbox -->
          <% if (d==4) { %>&nbsp;&nbsp;<% } %>
          <% if (d==20) { d=0; } %>
        <!-- Aggiungi uno spazio tra il sedicesimo e il diciasettesimo checkbox -->
          <% if (d==16) { %>&nbsp;&nbsp;<% } %>
          <% } %>
        <br><br>
        <center title="SCHERMO"><img id="schermo" src="images/schermo_cinema.png"></center>
        <center title="SCHERMO">SCHERMO</center>
    </div>

        <a> <input type="submit" class="button" value="Conferma selezione"/></a>
        <input type="hidden" name="num_sala" value="<%=sala.getNum_sala()%>">
        <input type="hidden" name="selectedcodfilm" value="<%=film.getCod_film()%>">
        <input type="hidden" name="cod_pro" value="<%=proiezione.getCod_pro()%>">
        <input type="hidden" name="formattedDate" value="<%=formattedDate%>"/>
        <input type="hidden" name="formattedTime" value="<%=formattedTime%>"/>
        <input type="hidden" name="controllerAction" value="GestioneAcquisti.sceltaposti"/>
      </form>

    </section>

  </main>
</body>
</html>
