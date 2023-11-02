<%@page session="false"%>
<%@ page import="com.starcinema.starcinema.model.mo.Utente" %>
<%@ page import="com.starcinema.starcinema.model.mo.Sala" %>

<%
  boolean loggedOn = true;
  Utente loggedUtente = (Utente) request.getAttribute("loggedUtente");
  String applicationMessage = (String) request.getAttribute("applicationMessage");
  String menuActiveLink = "Nuovospettacolo";

  Utente utente = (Utente) request.getAttribute("utente");
  Long selectedcodfilm = (Long) request.getAttribute("selectedcodfilm");
%>

<html>
<head>
  <title>Nuovo spettacolo</title>
  <%@include file="/include/htmlHead.inc"%>
  <script language="javascript">

    function submitIns() {
      var f;
      f = document.insForm;
      f.controllerAction.value = "HomeManagement.newspect";
    }

    function submitInspro(cod_film) {
      var numSala = document.getElementById("num_sala").value;
      var dataPro = document.getElementById("data_pro").value;
      var oraPro = document.getElementById("ora_pro").value;
      document.addproForm.selectedcodfilm.value = cod_film;
      document.addproForm.num_sala.value = numSala;
      document.addproForm.data_pro.value = dataPro;
      document.addproForm.ora_pro.value = oraPro;
      document.addproForm.submit();
    }

    function goback() {
      document.backForm.submit();
    }

    function mainOnLoadHandler() {
      document.insForm.addEventListener("submit", submitIns());

      document.insForm.backButton.addEventListener("click", goback);
    }

  </script>
</head>
<body>
<%@include file="/include/header.inc"%>
<main>

  <!-- Se viene cliccato nella barra di navigazione "Nuovo spettacolo" -->
  <%if (selectedcodfilm == null) {%>
  <section id="insFormSection">
    <form name="insForm" action="Dispatcher" method="post">

      <div class="field clearfix">
        <label for="titolo">Titolo</label>
        <input type="text" id="titolo" name="titolo"
               value=""
               required size="20" maxlength="50"/>
      </div><br>
      <div class="field clearfix">
        <label for="regista">Regista</label>
        <input type="text" id="regista" name="regista"
               value=""
               required size="20" maxlength="50"/>
      </div><br>
      <div class="field clearfix">
        <label for="cast">Cast</label>
        <input type="text" id="cast" name="cast"
               value=""
               required size="20" maxlength="50"/>
      </div><br>
      <div class="field clearfix">
        <label for="genere">Genere</label>
        <input type="text" id="genere" name="genere"
               value=""
               required size="20" maxlength="50"/>
      </div><br>

      <div class="field clearfix">
        <label for="durata">Durata</label>
        <input type="text" id="durata" name="durata"
               value=""
               required size="20" maxlength="50"/>
      </div><br>
      <div class="field clearfix">
        <label for="nazione">Nazione</label>
        <input type="text" id="nazione" name="nazione"
               value=""
               required size="20" maxlength="50"/>
      </div><br>
      <div class="field clearfix">
        <label for="anno">Anno</label>
        <input type="text" id="anno" name="anno"
               value=""
               required size="20" maxlength="50"/>
      </div><br>
      <div class="field clearfix">
        <label for="descrizione">Descrizione</label>
        <input type="text" id="descrizione" name="descrizione"
               value=""
               required size="20" maxlength="50"/>
      </div><br>
      <div class="field clearfix">
        <label for="trailer">Trailer</label>
        <input type="text" id="trailer" name="trailer"
               value=""
               required size="20" maxlength="50"/>
      </div><br>

      <div class="field clearfix">
        <label>&#160;</label>
        <input type="submit" class="button" value="Invia"/>
        <input type="button" name="backButton" class="button" value="Annulla"/>
      </div>
      <input type="hidden" name="controllerAction"/>
    </form>

  </section>
  <%}%>

  <!-- Quando viene premuto il bottone "aggiungi proiezioni" nella home -->
  <%if (selectedcodfilm != null) {%>
  <section id="insproFormSection">
    <form name="insproForm" action="Dispatcher" method="post">


      <div class="field clearfix">
        <label for="num_sala">Numero sala</label>
        <input type="text" id="num_sala" name="num_sala"
               value=""
               required size="20" maxlength="50"/>
      </div><br>
      <div class="field clearfix">
        <label for="data_pro">Data proiezione</label>
        <input type="date" id="data_pro" name="data_pro"
               value=""
               required size="20" maxlength="50"/>
      </div><br>
      <div class="field clearfix">
        <label for="ora_pro">Ora proiezione</label>
        <input type="time" id="ora_pro" name="ora_pro"
               value=""
               required size="20" maxlength="50"/>
      </div><br>

      <div class="field clearfix">
        <label>&#160;</label>
        <input type="button" id="newproButton" name="newproButton"
               class="button" value="Invia" onclick="submitInspro(<%=selectedcodfilm%>)">
        <input type="button" name="backButton" class="button" value="Annulla" onclick="goback()"/>
      </div>
      <input type="hidden" name="controllerAction"/>
    </form>

  </section>
  <%}%>

  <form name="backForm" method="post" action="Dispatcher">
    <input type="hidden" name="controllerAction" value="HomeManagement.view"/>
  </form>

  <form name="addproForm" method="post" action="Dispatcher">
    <input type="hidden" name="selectedcodfilm"/>
    <input type="hidden" name="num_sala"/>
    <input type="hidden" name="data_pro"/>
    <input type="hidden" name="ora_pro"/>
    <input type="hidden" name="controllerAction" value="HomeManagement.newpro"/>
  </form>


</main>
</body>
</html>